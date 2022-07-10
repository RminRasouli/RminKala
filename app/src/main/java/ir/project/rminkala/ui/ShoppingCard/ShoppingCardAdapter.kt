package ir.project.rminkala.ui.ShoppingCard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.project.rminkala.data.model.localDataModel.ProductLocalModel
import ir.project.rminkala.databinding.ShippingcardItemBinding


class ShoppingCardAdapter :
    ListAdapter<ProductLocalModel, ShoppingCardAdapter.ProductLocalModelViewHolder>(
        ProductLocalModelComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductLocalModelViewHolder {
        val binding =
            ShippingcardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductLocalModelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductLocalModelViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)

        }
    }

    class ProductLocalModelViewHolder(private val binding: ShippingcardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductLocalModel) {
            binding.apply {
                tvProductName.text = product.name
                tvProductPrice.text = product.price

                deletedProduct.setOnClickListener {

                }
            }
        }
    }

    class ProductLocalModelComparator : DiffUtil.ItemCallback<ProductLocalModel>() {
        override fun areItemsTheSame(oldItem: ProductLocalModel, newItem: ProductLocalModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ProductLocalModel, newItem: ProductLocalModel) =
            oldItem == newItem
    }
}