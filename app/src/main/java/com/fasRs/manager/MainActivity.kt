package com.fasRs.manager

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fasRs.manager.root.RootConnection
import com.fasRs.manager.ui.theme.FasrsManagerTheme
import android.util.Log


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val rootConnection = RootConnection(applicationContext)
        val message = rootConnection.sudo().checkConnection()
        Log.d("su check", message)

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
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.382f)
                .align(Alignment.Start)
        ) {
            Title(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(25.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.618f)
                .align(Alignment.End)
        ) {
            MainCards(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(25.dp)
            )
        }
    }
}
