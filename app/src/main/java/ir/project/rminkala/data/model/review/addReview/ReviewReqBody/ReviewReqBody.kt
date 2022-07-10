package ir.project.rminkala.data.model.review.addReview.ReviewReqBody

data class ReviewReqBody(
    val product_id: Int,
    val rating: Int,
    val review: String,
    val reviewer: String,
    val reviewer_email: String
)