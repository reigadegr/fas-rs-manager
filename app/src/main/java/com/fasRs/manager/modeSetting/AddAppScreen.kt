package com.fasRs.manager.modeSetting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.fasRs.manager.AnimationProfile
import com.fasRs.manager.GlobalViewModel
import com.fasRs.manager.LazyScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.ModeSettingScreenDestination
import com.ramcosta.composedestinations.navigation.navigate

@Composable
@Destination<RootGraph>(style = AnimationProfile::class)
fun AddAppScreen(
    navController: NavController,
    globalViewModel: GlobalViewModel,
) {
    val addAppScreenViewModel: AddAppScreenViewModel = viewModel()
    AddAppScreenContent(navController, globalViewModel, addAppScreenViewModel)
}

@Composable
@Preview
fun AddAppScreenContent(
    navController: NavController? = null,
    globalViewModel: GlobalViewModel? = null,
    addAppListViewModel: AddAppScreenViewModel? = null,
) {
    val allPackageInfo =
        globalViewModel?.currentAllPackages?.collectAsState()?.value ?: emptyList()
    val showList =
        addAppListViewModel?.currentAppShowList?.collectAsState()?.value ?: emptyList()

    LazyScreen {
        item {
            Button(
                modifier =
                    Modifier
                        .padding(25.dp)
                        .size(65.dp),
                colors =
                    ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.primary,
                    ),
                onClick = {
                    navController?.navigate(direction = ModeSettingScreenDestination) {
                        launchSingleTop = true
                    }
                },
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        }

        item(allPackageInfo) {
            SearchBar(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp),
                onSearch = { searchName ->
                    addAppListViewModel?.updateAppShowList(allPackageInfo, searchName)
                },
            )
        }

        items(items = showList) { item ->
            AppCard(packageInfo = item)
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
