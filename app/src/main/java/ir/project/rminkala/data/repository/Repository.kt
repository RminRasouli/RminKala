package ir.project.rminkala.data.repository


import ir.project.rminkala.data.model.ImageSlaider.PhotoSlider
import ir.project.rminkala.data.model.categoty.CategoryItem
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.data.remote.RemoteDataSource
import ir.project.rminkala.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository
@Inject constructor(
    @IoDispatcher
    private val dispatcher: CoroutineDispatcher,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getRemoteAllProduct(): Response<List<Product>> {
        return remoteDataSource.giveAllProduct()
    }

    suspend fun getCategories(): Response<List<CategoryItem>> {
        return remoteDataSource.getCategories()
    }

    suspend fun getSliderPhoto() : Response<PhotoSlider> {
        return remoteDataSource.getSliderPhoto()
    }

}