package ir.project.rminkala.data.repository


import android.util.Log
import ir.project.rminkala.data.local.LocalDataSource
import ir.project.rminkala.data.model.Customer.CustomerReq.CustomerReqBody
import ir.project.rminkala.data.model.Customer.CustomerRes.CustomerResBody
import ir.project.rminkala.data.model.ImageSlaider.PhotoSlider
import ir.project.rminkala.data.model.categoty.CategoryItem
import ir.project.rminkala.data.model.localDataModel.ProductLocalModel
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.data.model.review.ReviewItem
import ir.project.rminkala.data.model.review.addReview.ReviewReqBody.ReviewReqBody
import ir.project.rminkala.data.model.review.addReview.ReviewResBody.ReviewResBody
import ir.project.rminkala.data.remote.RemoteDataSource
import ir.project.rminkala.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

const val TAG = "Repository"

@Singleton
class Repository
@Inject constructor(
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher,
    private val remoteDataSource: RemoteDataSource,
    private val dataBase: LocalDataSource
) {


    suspend fun getSliderPhoto(): Response<PhotoSlider> {
        return remoteDataSource.getSliderPhoto()
    }

    suspend fun givePagingProduct(page: Int, per_page: Int): Response<List<Product>> {
        val _return = remoteDataSource.givePagingProduct(page, per_page)
        Log.d("product", "givePagingProduct: ${_return.isSuccessful}")
       return _return
    }


    fun getAllReview(): Flow<List<ReviewItem>> = flow {
        val response = remoteDataSource.getAllReview()
        if (response.isSuccessful) {
            emit(response.body() ?: listOf())
        }
    }

    fun getAllCategoryList(): Flow<List<CategoryItem>> = flow {
        val response = remoteDataSource.getCategories()
        if (response.isSuccessful) {
            emit(response.body() ?: listOf())
        }
    }

    fun getAllProduct(): Flow<List<Product>> = flow {
        val response = remoteDataSource.giveAllProduct()
        if (response.isSuccessful) {
            emit(response.body() ?: listOf())
        }
    }

    fun getProductBySearch(search: String): Flow<List<Product>> = flow {
        val response = remoteDataSource.getProductBySearch(search)
        if (response.isSuccessful) {
            emit(response.body() ?: listOf())
        }
    }

    suspend fun createCustomer(body: CustomerReqBody): Response<CustomerResBody> {
        val req = remoteDataSource.createCustomer(body)
        Log.d(
            TAG,
            "createCustomer: Create Customer is Call  successful is ${req.isSuccessful}"
        )

        return req

    }

    suspend fun addReview(body: ReviewReqBody): Response<ReviewResBody> {
        return remoteDataSource.addReview(body)
    }


    // Local DataBase function

    suspend fun insertProductDataBase(product: ProductLocalModel) {

        dataBase.inserProduct(product)
    }

    suspend fun getAllProductDataBase(): Flow<List<ProductLocalModel>> {
        return dataBase.getAllProduct()
    }

    suspend fun updateProductDataBase(product: ProductLocalModel) {
        dataBase.updateProduct(product)
    }

    suspend fun deletedProductDataBase(product: ProductLocalModel) {
        dataBase.deletedProduct(product)
    }


}
