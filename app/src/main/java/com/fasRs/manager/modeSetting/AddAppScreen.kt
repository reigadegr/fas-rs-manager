package com.fasRs.manager.modeSetting

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fasRs.manager.GlobalViewModel
import com.fasRs.manager.LazyColumnScreen
import com.fasRs.manager.SearchBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.bottomsheet.spec.DestinationStyleBottomSheet

@Composable
@Destination<RootGraph>(style = DestinationStyleBottomSheet::class)
fun ColumnScope.AddAppScreen(globalViewModel: GlobalViewModel) {
    val addAppScreenViewModel: AddAppScreenViewModel = viewModel()
    AddAppScreenContent(globalViewModel, addAppScreenViewModel)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
private fun AddAppScreenContent(
    globalViewModel: GlobalViewModel? = null,
    addAppListViewModel: AddAppScreenViewModel? = null,
) {
    val allPackageInfo =
        globalViewModel?.currentAllPackages?.collectAsState()?.value ?: emptyList()
    val showList =
        addAppListViewModel?.currentAppShowList?.collectAsState()?.value ?: allPackageInfo

    LazyColumnScreen {
        stickyHeader {
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

        items(showList.chunked(5)) { infos ->
            Row {
                infos.forEach { info ->
                    AppCard(modifier = Modifier.weight(0.2f), packageInfo = info)
                }
            }
        }
    }
}
