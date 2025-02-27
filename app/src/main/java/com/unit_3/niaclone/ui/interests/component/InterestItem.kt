package com.unit_3.niaclone.ui.interests.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Check
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
    name: String,
    isInterest: Boolean,
    onItemClick: (String) -> Unit,
    onToggleClick: () -> Unit
) {

    ListItem(
        leadingContent = {
            InterestIcon()
        },
        headlineContent = {
            Text(text = name)
        },
        trailingContent = {
            ToggleIcon(isInterest, onToggleClick)
        },
        modifier = Modifier
            .clickable { onItemClick(name) }
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
private fun ToggleIcon(isInterest: Boolean, onClick: () -> Unit) {
    Icon(
        imageVector = if (isInterest) Icons.Outlined.Check else Icons.Outlined.Add,
        contentDescription = "Not Selected",
        modifier = Modifier
            .padding(end = 16.dp)
            .clickable { onClick() }
    )
}