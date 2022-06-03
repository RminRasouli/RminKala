package ir.project.rminkala.data.remote

import ir.project.rminkala.data.model.ImageSlaider.PhotoSlider
import ir.project.rminkala.data.model.categoty.CategoryItem
import ir.project.rminkala.data.model.product.Product
import retrofit2.Response

interface RemoteDataSource {
    suspend fun giveAllProduct() : Response<List<Product>>
    suspend fun getCategories() : Response<List<CategoryItem>>
    suspend fun getSliderPhoto() : Response<PhotoSlider>
}