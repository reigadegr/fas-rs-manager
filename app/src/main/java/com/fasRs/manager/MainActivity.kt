package com.fasRs.manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fasRs.manager.ui.theme.FasrsManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FasrsManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Screen()
                }
            }
        }
    }
}

@Preview
@Composable
private fun Screen() {
    LazyColumn(modifier = Modifier.fillMaxHeight().width(100.dp)) {
        item {
            Spacer(modifier = Modifier.height(75.dp))
        }

        item {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth(),
            ) {
                Title(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .padding(25.dp),
                )
            }
        }

        item {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth(),
            ) {
                SettingCards(
                    modifier =
                        Modifier
                            .align(Alignment.BottomCenter)
                            .padding(25.dp),
                )
            }
        }
    }
}
