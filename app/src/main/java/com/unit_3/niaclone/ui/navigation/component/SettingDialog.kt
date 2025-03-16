package com.unit_3.niaclone.ui.navigation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unit_3.niaclone.ui.theme.NIACloneTheme

@Composable
fun SettingDialog(
    onDismissRequest : () -> Unit
) {
    AlertDialog(
        icon = {},
        title = {
            Column {
                Text(
                    text = "Setting",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                )
                HorizontalDivider()
            }
        },
        text = {
            DialogDetail()
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("OK")
            }
        }
    )
}

@Composable
fun DialogDetail() {
    Column {
        ThemeSetting()
        DarkModeSetting()
        HorizontalDivider()
    }
}

@Composable
fun ThemeSetting() {
    val themeOptions = listOf("Default", "Android")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(themeOptions[0]) }
    val title = "Theme"
    Text(
        text = "Theme",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Left,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    )

    Column(
        modifier = Modifier.selectableGroup()
    ) {
        themeOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(38.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }

    AnimatedVisibility(
        visible = selectedOption == themeOptions[0],
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        UseDynamicColor()
    }

}

@Composable
fun DarkModeSetting() {
    val themeOptions = listOf("System Default", "Light", "Dark")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(themeOptions[0]) }

    Text(
        text = "Dark mode preference",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    )

    Column(
        modifier = Modifier.selectableGroup()
    ) {
        themeOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}

@Composable
fun UseDynamicColor() {
    val themeOptions = listOf("Yes", "No")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(themeOptions[0]) }
    Column {
        Text(
            text = "Use Dynamic Color",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        Column(
            modifier = Modifier.selectableGroup()
        ) {
            themeOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = null
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDialog() {
    SettingDialog({})
}