package ir.project.rminkala.ui.Detail

import ir.project.rminkala.data.model.product.Product

interface ProductClickHandler {
    fun clickedProductItem(product : Product)
}