package com.example.baisecomposelearn.wanandroid

import androidx.paging.PagingSource
import androidx.paging.PagingState

class ProjectPagingSource(private val repository: Repository) : PagingSource<Int, Project>() {
    override fun getRefreshKey(state: PagingState<Int, Project>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Project> {
        return try {
            val nextPage = params.key ?: 1
            val response = repository.getProjects(nextPage, 294)
            LoadResult.Page(
                data = response.data.datas,
                prevKey = if (nextPage==1) null else nextPage -1,
                nextKey = if (nextPage<response.data.pageCount) nextPage + 1 else null
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}