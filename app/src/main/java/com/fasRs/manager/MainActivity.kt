package com.fasRs.manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Title()
                        MainCards()
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun MainCards(modifier: Modifier = Modifier) {
    Surface(
        modifier =
        modifier
            .fillMaxWidth()
            .padding(25.dp)
            .height(400.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary),
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.onSecondary,
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
            repeat(4) {
                settingCard(modifier = Modifier.weight(0.25f))
            }
        }
    }
}
