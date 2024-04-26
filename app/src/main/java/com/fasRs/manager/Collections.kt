package com.fasRs.manager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.navigation.navigate

@Composable
fun Background(
    color: Color = MaterialTheme.colorScheme.background,
    content: @Composable () -> Unit = {},
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = color,
    ) {
        content()
    }
}

@Composable
fun LazyColumnScreen(
    color: Color = MaterialTheme.colorScheme.background,
    beforeLazy: @Composable () -> Unit = {},
    content: LazyListScope.() -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = color,
    ) {
        Column {
            beforeLazy()

            LazyColumn(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                contentPadding = PaddingValues(25.dp),
            ) {
                item {
                    Spacer(modifier = Modifier.height(75.dp))
                }

                content()
            }
        }
    }
}

@Composable
@Preview
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {},
) {
    var text by remember {
        mutableStateOf("")
    }

    TextField(
        modifier = modifier,
        value = text,
        onValueChange = { newText ->
            text = newText
            onSearch(newText)
        },
        colors =
            TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
        label = {
            Row(horizontalArrangement = Arrangement.Center) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
        },
        shape = RoundedCornerShape(20.dp),
        singleLine = true,
    )
}

@Composable
@Preview
fun FilterSticker(
    modifier: Modifier = Modifier,
    selected: Boolean = true,
    onClick: () -> Unit = {},
    label: String = "preview",
) {
    FilterChip(
        modifier = modifier,
        selected = selected,
        onClick = {
            onClick()
        },
        leadingIcon = {
            if (selected) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null)
            } else {
                null
            }
        },
        label = {
            Text(text = label)
        },
    )
}

@Composable
@Preview
fun BackButton(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
) {
    IconButton(
        modifier = modifier.size(30.dp),
        onClick = {
            navController?.navigate(direction = NavGraphs.root)
        },
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
        )
    }
}
