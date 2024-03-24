package com.fas_rs.manager

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable { },
        shape = RoundedCornerShape(20.dp)
    ) {

    }
}