# 10일차

이제 본격적인 Theme과 Dark Mode 그리고 UserDynamicColor를 적용해줄 것이다.

[AndroidDeveloper의 Compose 테마 설정 가이드](https://developer.android.com/codelabs/jetpack-compose-theming?hl=ko#0) 를 참고해보았으나 구체적인 방법을 알 수는 없었다.

또한 기존의 `SettingDialog`의 중복되는 코드가 너무 많다.   
차라리 그려지는 부분을 하나의 템플릿으로 만들고 각각의 설정들의 이름과 상태들만 넘겨줘서 그리는 방식이 좋아보인다.

하지만 이것은 Theme 변경 기능을 완성하고 나서야 손볼 수 있을 것 같다.   
아직 구현 방법도 모르는데 쪼개면 구현이 더 어려워질 것 같다.

## 1. 다크 모드
현재는 
```kotlin
NIACloneTheme {
    NiaApp()
}
```
위와 같이 `NIACloneTheme`에 어떠한 처리도 되지 않고 Default 상태로 처리된다.
```kotlin
@Composable
fun NIACloneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
```
Theme을 까보면 이렇게 되어있다. Theme은 SystemDarkMode를 따르는 것과 `dynamicColor` true가 기본값으로 처리되어 있다.

이것을 기본값을 사용하는 것이 아닌 별도로 관리해주는 상태값을 넘겨주어 앱 내부에서 변경할 수 있도록 처리해주어야 한다.

코틀린에 대한 지식이 부족해서 공부를 할 때 내가 10을 배울 수 있는데도 기반이 없어 2,3만 얻어가는 것 같아 코틀린에 대한 기본지식을 보충하고자 '코틀린 쿡북' 책을 읽은 뒤 다시 진행할 예정