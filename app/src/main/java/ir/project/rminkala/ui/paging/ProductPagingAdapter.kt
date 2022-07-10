package ir.project.rminkala.ui.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.databinding.ProductItemBinding
import ir.project.rminkala.util.FaNum
import javax.inject.Inject


class ProductPagingAdapter @Inject constructor() :
    PagingDataAdapter<Product, ProductPagingAdapter.MyViewHolder>(PDiffCall()) {

    class MyViewHolder(
        private val binding: ProductItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun mBind(product: Product) = binding.apply {
            tvProductPriceOff.isInvisible = true
            tvProductName.text = product.name
            tvProductPrice.text = FaNum.convert(product.price)
            if (product.average_rating == "0.00") {
                starProduct.text = "بدون نظر"
            } else {
                starProduct.text = product.average_rating
            }
            if (product.images.isNotEmpty()) {
                val image = product.images[0].src
                Glide.with(itemView)
                    .load(image)
                    .error("https://icon-library.com/images/break-icon/break-icon-13.jpg")
                    .into(imgProduct)
            }

            if (product.sale_price != "") {
                tvProductPriceOff.isInvisible = false
                tvProductPriceOff.text = FaNum.convert(product.regular_price)
                tvProductPriceOff.paint.isStrikeThruText = true
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: MyViewHolder, position: Int
    ) {
        getItem(position)?.let { holder.mBind(it) }
    }

}

class PDiffCall : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}