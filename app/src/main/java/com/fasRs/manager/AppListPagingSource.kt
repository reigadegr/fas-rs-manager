package com.fasRs.manager

import android.content.Context
import androidx.paging.PagingSource
import androidx.paging.PagingState

class AppListPagingSource(context: Context) : PagingSource<Int, PackageInfo>() {
    private val appList = getAllPackages(context)

    override fun getRefreshKey(state: PagingState<Int, PackageInfo>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PackageInfo> {
        TODO("Not yet implemented")
    }
}
