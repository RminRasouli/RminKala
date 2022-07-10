package ir.project.rminkala.ui.Review

import android.graphics.Color
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.project.rminkala.data.model.review.ReviewItem
import ir.project.rminkala.databinding.ReviewItermBinding

class ReviewAdapter :
    ListAdapter<ReviewItem, ReviewAdapter.ReviewViewHolder>(ReviewComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding =
            ReviewItermBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)


    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }

    }

    class ReviewViewHolder(private val binding: ReviewItermBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(reviewItem: ReviewItem) {
            binding.apply {

                reviewerName.text = reviewItem.reviewer
                reviewDescription.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(reviewItem.review, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    Html.fromHtml(reviewItem.review)
                }
                startReview.text = reviewItem.rating.toString()

                if (reviewItem.rating >= 2) {
                    reviewForOther.text = "خرید این کالا را پیشنهاد میدهم"
                    reviewForOther.setTextColor(Color.GREEN)
                } else {
                    reviewForOther.text = "خرید این کالا را پیشنهاد نمیدهم"
                    reviewForOther.setTextColor(Color.RED)
                }

            }
        }
    }

    class ReviewComparator : DiffUtil.ItemCallback<ReviewItem>() {
        override fun areItemsTheSame(oldItem: ReviewItem, newItem: ReviewItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ReviewItem, newItem: ReviewItem) =
            oldItem == newItem
    }
}
