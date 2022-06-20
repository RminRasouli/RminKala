package ir.project.rminkala.data.local

import ir.project.rminkala.data.local.db.ProductDao
import ir.project.rminkala.data.local.db.ProductDataBase
import ir.project.rminkala.data.model.localDataModel.ProductLocalModel

class LocalDataSourcePr(
    private val local : ProductDao
) : LocalDataSource {
//    override suspend fun addProjectUser(product : ProductLocalModel) {
//         local.insertAll(product)
//    }
//
//    override suspend fun removeProductUser(product: ProductLocalModel) {
//         local.deleteProducts(product)
//    }
//
//    override suspend fun getProductDataBase(): List<ProductLocalModel> {
//        return local.getAllProducts()
//    }
}