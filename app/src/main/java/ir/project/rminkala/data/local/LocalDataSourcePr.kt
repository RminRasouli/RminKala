package ir.project.rminkala.data.local

import ir.project.rminkala.data.local.db.ProductDao
import ir.project.rminkala.data.local.db.ProductDataBase
import ir.project.rminkala.data.model.localDataModel.ProductLocalModel
import kotlinx.coroutines.flow.Flow

class LocalDataSourcePr(
    private val db : ProductDataBase
) : LocalDataSource {

    private val productDao = db.productDao()

    override suspend fun getAllProduct(): Flow<List<ProductLocalModel>> {
        return productDao.getAllProducts()
    }

    override suspend fun inserProduct(product: ProductLocalModel) {
        productDao.insertProducts(product)
    }

    override suspend fun updateProduct(product: ProductLocalModel) {
        productDao.updateProduct(product)
    }

    override suspend fun deletedProduct(product: ProductLocalModel) {
        productDao.delete(product)
    }


}