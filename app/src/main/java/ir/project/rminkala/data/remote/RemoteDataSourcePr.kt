package ir.project.rminkala.data.remote

import ir.project.rminkala.data.remote.api.StoreApiService
import ir.project.rminkala.data.model.ImageSlaider.PhotoSlider
import ir.project.rminkala.data.model.categoty.CategoryItem
import ir.project.rminkala.data.model.product.Product
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourcePr
    @Inject constructor(
        private val api : StoreApiService
    ): RemoteDataSource {
    override suspend fun giveAllProduct(): Response<List<Product>> {
        return api.getAllProduct()
    }

    override suspend fun getCategories(): Response<List<CategoryItem>> {
        return api.getAllCategories()
    }

    override suspend fun getSliderPhoto(): Response<PhotoSlider> {
        return api.getSliderPhoto()
    }


}