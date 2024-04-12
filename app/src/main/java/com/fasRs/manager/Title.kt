package com.fasRs.manager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.fasRs.manager.root.getRoot

@Composable
@Preview
fun Title(
    modifier: Modifier = Modifier,
    currentFasRsState: FasRsState = FasRsState.RUNNING,
) {
    Column(modifier = modifier) {
        Box {
            TitleText(modifier = Modifier, color = MaterialTheme.colorScheme.primary)
        }

        Spacer(modifier = Modifier.height(25.dp))
        val backgroundColor =
            when (currentFasRsState) {
                FasRsState.RUNNING -> MaterialTheme.colorScheme.primary
                else -> MaterialTheme.colorScheme.error
            }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            color = backgroundColor,
            shadowElevation = 3.dp,
        ) {
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(130.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TitleIcon(
                    modifier =
                        Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .padding(25.dp),
                    fasRsState = currentFasRsState,
                )

                Column(
                    modifier = Modifier.fillMaxHeight(),
                ) {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .weight(0.5f),
                        contentAlignment = Alignment.BottomStart,
                    ) {
                        TitleStatus(color = MaterialTheme.colorScheme.onTertiary, fasRsState = currentFasRsState)
                    }

                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .weight(0.5f),
                        contentAlignment = Alignment.TopStart,
                    ) {
                        TitleVersion(
                            color = MaterialTheme.colorScheme.onSecondary,
                            fasRsState = currentFasRsState,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TitleIcon(
    modifier: Modifier = Modifier,
    fasRsState: FasRsState,
) {
    val icon =
        when (fasRsState) {
            FasRsState.RUNNING -> Icons.Filled.CheckCircle
            else -> Icons.Filled.Close
        }

    Icon(
        imageVector = icon,
        modifier = modifier,
        contentDescription = null,
    )
}

@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    color: Color,
) {
    Text(
        text = "fas-rs",
        style = MaterialTheme.typography.displayLarge,
        textAlign = TextAlign.Center,
        modifier = modifier,
        color = color,
        fontSize = TextUnit(10.0f, TextUnitType.Em),
    )
}

@Composable
fun TitleVersion(
    modifier: Modifier = Modifier,
    color: Color,
    fasRsState: FasRsState,
) {
    val unknownVersion = stringResource(id = R.string.title_version_unknown)
    var version by remember {
        mutableStateOf(unknownVersion)
    }

    when (fasRsState) {
        FasRsState.RUNNING -> {
            val context = LocalContext.current
            getRoot(context) { root ->
                version = root.getFasRsVersion()
            }
        }
        else -> version = unknownVersion
    }

    Text(
        text = version,
        style = MaterialTheme.typography.headlineSmall,
        textAlign = TextAlign.Center,
        modifier = modifier,
        color = color,
    )
}

@Composable
fun TitleStatus(
    modifier: Modifier = Modifier,
    color: Color,
    fasRsState: FasRsState,
) {
    val text =
        when (fasRsState) {
            FasRsState.NEED_ROOT -> stringResource(id = R.string.title_status_need_root)
            FasRsState.RUNNING -> stringResource(id = R.string.title_status_running)
            FasRsState.NOT_RUNNING -> stringResource(id = R.string.title_status_not_running)
        }

    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
        modifier = modifier,
        color = color,
    )
}
