package ir.project.rminkala.ui.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.data.repository.Repository
import javax.inject.Inject


class ProductPagingSource @Inject constructor(
    private val repository: Repository
) : PagingSource<Int, Product>(){

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {

        return try {
            val pageNumber = params.key ?: 1
            val response = repository.givePagingProduct(pageNumber,params.loadSize)
            val result = response.body()
            if (response.isSuccessful && result!=null){
                LoadResult.Page(
                    data = result,
                    prevKey = if (pageNumber == 1) null else pageNumber.minus(1),
                    nextKey = if (result.isEmpty()) null else pageNumber.plus(1)
                )
            }else{
                throw Exception("Response not found!")
            }
        }catch (e :Exception){
            LoadResult.Error(e)
        }

    }
}