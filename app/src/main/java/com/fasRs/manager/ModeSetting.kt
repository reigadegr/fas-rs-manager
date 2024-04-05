package com.fasRs.manager

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph

@Composable
@Preview
@Destination<RootGraph>
fun ModeSetting() {
    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize(),
    ) {
    }
}
