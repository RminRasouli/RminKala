package ir.project.rminkala.data.local

import ir.project.rminkala.data.model.localDataModel.ProductLocalModel
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getAllProduct(): Flow<List<ProductLocalModel>>
    suspend fun inserProduct(product: ProductLocalModel)
    suspend fun updateProduct(product: ProductLocalModel)
    suspend fun deletedProduct(product: ProductLocalModel)
}