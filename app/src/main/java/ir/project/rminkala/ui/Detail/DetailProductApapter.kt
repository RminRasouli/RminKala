package ir.project.rminkala.ui.Detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.databinding.ProductItemBinding

class DetailProductApapter :
    ListAdapter<Product, DetailProductApapter.ProductViewHolder>(ProductComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)


    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }

    }

    class ProductViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.apply {
                tvProductName.text = product.name
                tvProductPrice.text = product.price
                starProduct.text = product.average_rating
                if (product.images.isNotEmpty())
                    Glide.with(itemView)
                        .load(product.images[0].src)
                        .into(imgProduct)
            }

            itemView.setOnClickListener {
                val action = DetailFragmentDirections.actionDetailFragmentSelf(
                    product.name,
                    product.id,
                    product.price,
                    product.images.toTypedArray(),
                    product.description,
                    product.rating_count,
                    product.categories.toTypedArray()
                )
                it.findNavController().navigate(
                    action
                )
            }
        }
    }

    class ProductComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Product, newItem: Product) =
            oldItem == newItem
    }
}
