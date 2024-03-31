package com.fasRs.manager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun Title(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Box {
            TitleText(modifier = Modifier.padding(25.dp), color = MaterialTheme.colorScheme.primary)
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.primary,
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    // imageVector = Icons.Default.Close,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .padding(25.dp),
                    contentDescription = null
                )

                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        TitleMotto(color = MaterialTheme.colorScheme.onTertiary)
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        contentAlignment = Alignment.TopStart
                    ) {
                        TitleStatus(
                            color = MaterialTheme.colorScheme.onSecondary,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Text(
        text = "fas-rs",
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center,
        modifier = modifier,
        color = color,
        fontSize = TextUnit(8.0f, TextUnitType.Em)
    )
}

@Composable
fun TitleStatus(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Text(
        text = "todo: version",
        style = MaterialTheme.typography.headlineSmall,
        textAlign = TextAlign.Center,
        modifier = modifier,
        color = color,
    )
}

@Composable
fun TitleMotto(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Text(
        text = "todo: status",
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
        modifier = modifier,
        color = color,
    )
}
