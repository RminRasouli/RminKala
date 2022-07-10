package ir.project.rminkala.data.local.db

import androidx.room.*
import ir.project.rminkala.data.model.localDataModel.ProductLocalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM Product_ShoppingCard")
    fun getAllProducts(): Flow<List<ProductLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(vararg product: ProductLocalModel)

    @Update
    suspend fun updateProduct(product: ProductLocalModel)

    @Delete
    suspend fun delete(product: ProductLocalModel)

    @Query("DELETE FROM Product_ShoppingCard")
    suspend fun deleteAllProducts()

}























//    @Query("SELECT * FROM Product_ShoppingCard")
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(vararg product: ProductLocalModel)
//
//    fun getAllProducts(): List<ProductLocalModel>
//    @Update
//    fun updateProducts(product: ProductLocalModel)
//    @Delete
//    fun deleteProducts(user: ProductLocalModel)