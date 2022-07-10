package ir.project.rminkala.data.model.localDataModel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.project.rminkala.data.model.product.Image
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Product_ShoppingCard")
data class ProductLocalModel(
    @PrimaryKey val id: String,
    val name: String,
    val price: String,
)