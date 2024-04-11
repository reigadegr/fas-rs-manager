package com.fasRs.manager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.fasRs.manager.root.getRoot
import kotlinx.coroutines.delay

enum class State {
    NEED_ROOT,
    NOT_RUNNING,
    RUNNING,
}
