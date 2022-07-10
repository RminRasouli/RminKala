package ir.project.rminkala.ui.Review.AddReview

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.project.rminkala.data.model.review.addReview.ReviewReqBody.ReviewReqBody
import ir.project.rminkala.data.repository.Repository
import javax.inject.Inject

@HiltViewModel
class AddReviewViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private var _reviewId: Int = 0
    val reviewId get() = _reviewId

    suspend fun addReview(
        productId: String,
        reviewerName: String,
        reviewerEmail: String,
        rating: Int,
        review: String
    ) {
        val reviewReqBody = ReviewReqBody(
            productId.toInt(),
            rating,
            review,
            reviewerName,
            reviewerEmail
        )

        val response = repository.addReview(reviewReqBody)
        if(response.isSuccessful){
           val id = response.body()?.id
            if (id != null) {
                _reviewId = id
            }
        }
    }
}