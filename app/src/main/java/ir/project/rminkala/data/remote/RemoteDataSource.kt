package ir.project.rminkala.data.remote

import ir.project.rminkala.data.model.Customer.CustomerReq.CustomerReqBody
import ir.project.rminkala.data.model.Customer.CustomerRes.CustomerResBody
import ir.project.rminkala.data.model.ImageSlaider.PhotoSlider
import ir.project.rminkala.data.model.categoty.CategoryItem
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.data.model.review.ReviewItem
import ir.project.rminkala.data.model.review.addReview.ReviewReqBody.ReviewReqBody
import ir.project.rminkala.data.model.review.addReview.ReviewResBody.ReviewResBody
import retrofit2.Response

interface RemoteDataSource {
    suspend fun giveAllProduct() : Response<List<Product>>
    suspend fun givePagingProduct(page : Int , per_page : Int): Response<List<Product>>
    suspend fun getCategories() : Response<List<CategoryItem>>
    suspend fun getProductBySearch(search : String) : Response<List<Product>>
    suspend fun getSliderPhoto() : Response<PhotoSlider>
    suspend fun getAllReview() : Response<List<ReviewItem>>
    suspend fun createCustomer(body: CustomerReqBody) : Response<CustomerResBody>
    suspend fun addReview(body: ReviewReqBody) : Response<ReviewResBody>


}