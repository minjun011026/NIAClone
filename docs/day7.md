# 7일차
6일차의 navigation 문제를 해결하고자 다양한 방법을 고안하던 중   
1일차에서 포기했던 Type-Safe Navigation을 도입해보고자 했다.   
애초에 `navContoller`가 navigation의 애니메이션 처리와 백 스택 관리를 도와주는 도구이므로 이것을 사용하는 것이 적합하다고 판단했다.   
그렇기에 이왕 `navController`를 사용할 거 보다 좋은 방법을 고안하던 중 이것이 생각나서 이것을 도입해보고자 한다.   

## Type-Safe Navigation
온종일 Type-Safe Navigation을 도입해보고자 영상을 찾아보고 따라해보았으나 아직 레퍼런스가 많지 않고   
기존의 `bottomNavigationBar`를 활용하는 것도 너무 어려웠다.
또한 추가적인 모듈이나 코드가 너무도 많이 생겼다.
영상 강의를 다시 처음부터 보면서 최대한 적용해보았다.   
강의 영상 : https://www.youtube.com/watch?v=AIC_OFQ1r3k&t=204s

가장 일반적인 형태는 다음과 같다.
```kotlin
NavHost(
    navController = navController,
    startDestination = ScreenA
) {
    composable<ScreenA> {
        ...   
    }
    composable<ScreenB> {
        ...
    }
}
```
각 화면을 KClass를 사용해서 이동을 수행해주어야 한다.   
일단 각 화면을 정의해준다
```kotlin
sealed class Dest {
    @Serializable
    object ForYou

    @Serializable
    object Saved

    @Serializable
    object Interests

    @Serializable
    data class InterestDetail(
        val interest : String
    )
}
```
유일하게 이전 화면에서 값을 넘겨받는 것이 `InterestDetail`이므로 `InterestDetail`은 `data class`로 선언해주고 `interest`를 갖는다.   
그리고 `innerPadding`을 사용해주어야 하니 `NavHost`는 `Scaffold`내부에서 선언해준다.   
또한 `NavigationItem`에도 `route`를 추가해줘야 한다.
```kotlin
data class NavigationItem(
    val title : String,
    val selectedIcon : ImageVector,
    val unselectedIcon : ImageVector,
    val route : Any
)
```
최종적으로 이런 형태가 완성된다.
```kotlin
Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            ...
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(item.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(navController.graph.startDestinationId){
                                        saveState = true
                                    }
                                }
                            },
                            ...
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Dest.ForYou
            ) {
                composable<Dest.ForYou> {
                    ForYouView(modifier = Modifier.padding(innerPadding))
                }
                composable<Dest.Saved> {
                    SavedView(modifier = Modifier.padding(innerPadding))
                }
                composable<Dest.Interests> {
                    InterestsView(modifier = Modifier.padding(innerPadding), navController = navController)
                }
                composable<Dest.InterestDetail> {
                    val args = it.toRoute<Dest.InterestDetail>()
                    InterestDetailView(args.interest)
                }
            }
        }
    }
```
`val args = it.toRoute<Dest.InterestDetail>()`이것의 의미는 이전 화면에서 `navController.navigate`로 넘겨준 인자를 받아오는 값으로 타입은 `InterestDetail`이다.   

`InterestView`에서 `onItemClick`부분을 아래와 같이 수정해주었다.
```kotlin
navController.navigate(Dest.InterestDetail(name))
```
위의 작용을 수행해주어야 하므로 다른 View와는 다르게 `InterestView`는 `navController`를 인자로 받아준다.

이렇게 수정해주면 모든 화면이 `navController`를 통해 이동하여 자연스러운 애니메이션이 적용되고 Type-Safe하게 navigation을 구현할 수 있다.   
이전처럼 `screenA/{argument}` 와 같이 사용할 필요가 없어진다.

막상 하고보니 되게 간단했던 것 같다. 위의 영상이 아닌 다른 영상을 봤었는데 그건 별도의 모듈을 생성하고 navigator 객체를 만들어서 구현하는 방법이었다.   
이해도 잘 가지 않았고 그냥 위의 영상을 처음부터 다시 보니 나름 간단히 구현할 수 있었다.

확인해보니 뒤로가기를 눌러서 ForYou로 이동할 때 네비게이션바는 갱신이 안되는 문제가 생겼다.
이것을 개선해보아야할 듯하다.

현재 위치를 받아와서 갱신을 하는 방향으로 구현하려 했는데 문제가 하나 있다.   
기존의 방법들은 String으로 현재 위치를 받아와서 이를 통해 비교연산을 수행하는데  
나는 KClass를 이용한 방식으로 구현을 하여 기존 방식을 사용하기가 어렵다는 것이다.

그래서 현재 destination의 route를 Log로 확인해보니 프로젝트명에 `Dest.화면이름` 이런 방식으로 구성되어 있었다.   
그러면 기존 방식을 조금만 수정을 가해도 구현이 가능할 것 같아 단순 비교에서 `contains`연산으로 수정만 해서 구현해주었다.

```kotlin
val currentBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackEntry?.destination?.route

    var selectedItemIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(currentDestination) {
        selectedItemIndex = items.indexOfFirst { currentDestination?.contains(it.title) == true }
            .takeIf { it != -1 } ?: 0
    }
```

KClass에 보다 특화된 방법이 있는지 더 알아보아야할 듯 하다.