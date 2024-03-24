package com.fas_rs.manager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Title(modifier: Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        TitleText(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterVertically)
                .padding(10.dp),
            color = MaterialTheme.colorScheme.primary
        )

        Divider(modifier = Modifier.fillMaxHeight().width(2.dp), color = MaterialTheme.colorScheme.tertiaryContainer)

        Column(
            modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center,
        ) {
            TitleMotto(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.tertiary,
            )

            Divider(modifier = Modifier.height(2.dp), color = MaterialTheme.colorScheme.tertiaryContainer)

            TitleStatus(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

@Composable
fun TitleText(modifier: Modifier, color: Color) {
    Text(
        text = "fas-rs",
        style = MaterialTheme.typography.displayMedium,
        textAlign = TextAlign.Center,
        modifier = modifier,
        color = color
    )
}

@Composable
fun TitleStatus(modifier: Modifier, color: Color) {
    Text(
        text = "Status: Todo",
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
        modifier = modifier,
        color = color
    )
}

@Composable
fun TitleMotto(modifier: Modifier, color: Color) {
    Text(
        text = "todo: motto",
        style = MaterialTheme.typography.headlineSmall,
        textAlign = TextAlign.Center,
        modifier = modifier,
        color = color
    )
}
