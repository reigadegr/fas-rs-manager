package com.fasRs.manager

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
@Preview
fun SettingCards(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
) {
    Surface(
        modifier =
            modifier
                .fillMaxWidth()
                .height(500.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary),
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.onSecondary,
        shadowElevation = 10.dp,
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
            Spacer(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(5.dp),
            )

            ModeSettingCard(modifier = Modifier.weight(0.25f), navController = navController)
            SettingCard(modifier = Modifier.weight(0.25f))
            SettingCard(modifier = Modifier.weight(0.25f))
            SettingCard(modifier = Modifier.weight(0.25f))

            Spacer(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(5.dp),
            )
        }
    }
}

@Composable
@Preview(heightDp = 135)
fun SettingCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    Card(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(10.dp),
        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 5.dp,
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
    ) {
        content()
    }
}
