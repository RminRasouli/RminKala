package ir.project.rminkala.data.model.product

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tag(
    val id: Int,
    val name: String,
    val slug: String
):Parcelable