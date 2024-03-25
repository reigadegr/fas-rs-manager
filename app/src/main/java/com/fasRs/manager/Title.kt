package com.fasRs.manager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
fun title(modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        titleText(
            modifier =
                Modifier
                    .fillMaxHeight()
                    .wrapContentSize(Alignment.Center)
                    .weight(0.4f)
                    .padding(5.dp),
            color = MaterialTheme.colorScheme.primary,
        )

        Divider(
            modifier =
                Modifier
                    .fillMaxHeight()
                    .width(3.dp),
            color = MaterialTheme.colorScheme.tertiaryContainer,
        )

        Column(
            modifier =
                Modifier
                    .fillMaxHeight()
                    .weight(0.6f),
            verticalArrangement = Arrangement.Center,
        ) {
            titleMotto(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                        .wrapContentSize(Alignment.Center)
                        .align(Alignment.CenterHorizontally)
                        .padding(5.dp),
                color = MaterialTheme.colorScheme.tertiary,
            )

            Divider(
                modifier = Modifier.height(3.dp),
                color = MaterialTheme.colorScheme.tertiaryContainer,
            )

            titleStatus(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .weight(0.5f)
                        .wrapContentSize(Alignment.Center)
                        .align(Alignment.CenterHorizontally)
                        .padding(5.dp),
                color = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

@Composable
fun titleText(
    modifier: Modifier,
    color: Color,
) {
    Text(
        text = "fas-rs",
        style = MaterialTheme.typography.displayMedium,
        textAlign = TextAlign.Center,
        modifier = modifier,
        color = color,
    )
}

@Composable
fun titleStatus(
    modifier: Modifier,
    color: Color,
) {
    Text(
        text = "Status: Todo",
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
        modifier = modifier,
        color = color,
    )
}

@Composable
fun titleMotto(
    modifier: Modifier,
    color: Color,
) {
    Text(
        text = "todo: motto",
        style = MaterialTheme.typography.headlineSmall,
        textAlign = TextAlign.Center,
        modifier = modifier,
        color = color,
    )
}
