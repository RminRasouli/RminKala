package ir.project.rminkala.data.model.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val average_rating: String,
   val categories: List<Category>,
    val date_modified: String,
    val description: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val price: String,
    val rating_count: String,
    val regular_price: String,
    val tags: List<Tag>,
) : Parcelable