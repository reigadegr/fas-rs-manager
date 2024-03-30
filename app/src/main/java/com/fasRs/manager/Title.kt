package com.fasRs.manager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
    Surface(
        modifier = modifier
            .padding(25.dp)
            .height(130.dp),
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.primary,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.382f)
                    .padding(10.dp), contentAlignment = Alignment.Center
            ) {
                TitleText(
                    modifier =
                    Modifier
                        .fillMaxHeight()
                        .wrapContentSize(Alignment.Center),
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.618f),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f),
                    contentAlignment = Alignment.Center
                ) {
                    TitleMotto(
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center),
                        color = MaterialTheme.colorScheme.onTertiary,
                    )
                }

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f),
                    contentAlignment = Alignment.Center
                ) {
                    TitleStatus(
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center),
                        color = MaterialTheme.colorScheme.onSecondary,
                    )
                }
            }
        }
    }
}

@Composable
fun TitleText(
    modifier: Modifier,
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
    modifier: Modifier,
    color: Color,
) {
    Text(
        text = "Status: Todo",
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
        modifier = modifier,
        color = color,
        fontSize = TextUnit(5.0f, TextUnitType.Em)
    )

}

@Composable
fun TitleMotto(
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
