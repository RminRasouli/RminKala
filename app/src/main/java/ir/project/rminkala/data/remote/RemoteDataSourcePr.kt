package ir.project.rminkala.data.remote

import ir.project.rminkala.data.model.Customer.CustomerReq.CustomerReqBody
import ir.project.rminkala.data.model.Customer.CustomerRes.CustomerResBody
import ir.project.rminkala.data.remote.api.StoreApiService
import ir.project.rminkala.data.model.ImageSlaider.PhotoSlider
import ir.project.rminkala.data.model.categoty.CategoryItem
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.data.model.review.ReviewItem
import ir.project.rminkala.data.model.review.addReview.ReviewReqBody.ReviewReqBody
import ir.project.rminkala.data.model.review.addReview.ReviewResBody.ReviewResBody
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourcePr
    @Inject constructor(
        private val api : StoreApiService
    ): RemoteDataSource {
    override suspend fun giveAllProduct(): Response<List<Product>> {
        return api.getAllProduct()
    }

    override suspend fun givePagingProduct(page : Int , per_page : Int): Response<List<Product>> {
       return api.getPagingProduct(page = page , per_page = per_page)
    }

    override suspend fun getCategories(): Response<List<CategoryItem>> {
        return api.getAllCategories()
    }

    override suspend fun getProductBySearch(search: String): Response<List<Product>> {
        return api.getBySearch(search = search)
    }

    override suspend fun getSliderPhoto(): Response<PhotoSlider> {
        return api.getSliderPhoto()
    }

    override suspend fun getAllReview(): Response<List<ReviewItem>> {
        return api.getAllReviews()
    }

    override suspend fun createCustomer(body: CustomerReqBody): Response<CustomerResBody> {
        return api.createCustomer(body)
    }

    override suspend fun addReview(body: ReviewReqBody): Response<ReviewResBody> {
        return api.addReview(body)
    }




}