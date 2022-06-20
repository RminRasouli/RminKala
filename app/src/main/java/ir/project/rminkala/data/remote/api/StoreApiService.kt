package ir.project.rminkala.data.remote.api


import ir.project.rminkala.data.model.ImageSlaider.PhotoSlider
import ir.project.rminkala.data.model.categoty.CategoryItem
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.util.Constance.CONSUMER_KEY
import ir.project.rminkala.util.Constance.CONSUMER_SECRET
import ir.project.rminkala.util.Constance.END_PONT_CATEGORIES
import ir.project.rminkala.util.Constance.END_PONT_PRODUCT
import ir.project.rminkala.util.Constance.END_PONT_SLIDER_PHOTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StoreApiService {

    //Get All Project in Page 1

    @GET(END_PONT_PRODUCT)
    suspend fun getAllProduct(
        @Query("consumer_key") consumer_Key: String = CONSUMER_KEY,
        @Query("consumer_secret") consumer_Secret: String = CONSUMER_SECRET,
        @Query("per_page") page : Int = 100
    ): Response<List<Product>>

    @GET(END_PONT_CATEGORIES)
    suspend fun getAllCategories(
        @Query("consumer_key") consumer_Key: String = CONSUMER_KEY,
        @Query("consumer_secret") consumer_Secret: String = CONSUMER_SECRET,
    ): Response<List<CategoryItem>>

    @GET(END_PONT_SLIDER_PHOTO)
    suspend fun getSliderPhoto(
        @Query("consumer_key") consumer_Key: String = CONSUMER_KEY,
        @Query("consumer_secret") consumer_Secret: String = CONSUMER_SECRET,
    ): Response<PhotoSlider>

}