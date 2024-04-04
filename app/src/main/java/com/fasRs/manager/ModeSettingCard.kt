package com.fasRs.manager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fasRs.manager.root.getRoot
import kotlinx.coroutines.delay

@Composable
@Preview(heightDp = 130)
@Preview(heightDp = 130, locale = "zh")
fun ModeSettingCard(modifier: Modifier = Modifier) {
    SettingCard(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            var runningMode by remember {
                mutableStateOf(Mode.Unknown)
            }

            val context = LocalContext.current
            LaunchedEffect(Unit) {
                while (true) {
                    getRoot(context) { root ->
                        val newMode = root.fasRsMode.trim()
                        runningMode = Mode.parse(newMode)
                    }

                    delay(1000)
                }
            }

            val displayMode =
                when (runningMode) {
                    Mode.Powersave -> stringResource(id = R.string.setting_mode_powersave_mode)
                    Mode.Balance -> stringResource(id = R.string.setting_mode_balance_mode)
                    Mode.Performance -> stringResource(id = R.string.setting_mode_performance_mode)
                    Mode.Fast -> stringResource(id = R.string.setting_mode_fast_mode)
                    Mode.Unknown -> stringResource(id = R.string.setting_mode_unknown_mode)
                }

            Text(
                modifier = Modifier.padding(25.dp),
                text = "${stringResource(id = R.string.settings_mode)}: $displayMode",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center,
            )

            Icon(
                modifier = Modifier.padding(25.dp).size(30.dp),
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    }
}