package ir.project.rminkala.ui.Home

import android.view.View
import ir.project.rminkala.data.model.product.Product

interface RecyclerViewClickListener {
    fun onRecyclerViewClickListener(view : View, product : Product)

}