package com.unit_3.niaclone.ui.interests.component


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Man
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InterestItem(
    name: String
) {

    ListItem(
        leadingContent = {
            InterestIcon()
        },
        headlineContent = {
            Text(text = name)
        },
        trailingContent = {
            ToggleIcon()
        },
        modifier = Modifier
            .clickable {  }
    )

}

@Composable
private fun InterestIcon() {
    Icon(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .size(64.dp)
            .padding(4.dp),
        imageVector = Icons.Outlined.Man,
        contentDescription = null
    )
}

@Composable
private fun ToggleIcon() {
    Icon(
        imageVector = Icons.Outlined.Add,
        contentDescription = "Not Selected"
    )
}