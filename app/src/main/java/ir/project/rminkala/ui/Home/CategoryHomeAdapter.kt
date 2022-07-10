package ir.project.rminkala.ui.Home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.project.rminkala.data.model.categoty.CategoryItem
import ir.project.rminkala.databinding.CategoryCardItemBinding
import ir.project.rminkala.databinding.CategoryhomeItemBinding
import ir.project.rminkala.ui.Home.HomeFragmentDirections

class CategoryHomeAdapter :
    ListAdapter<CategoryItem, CategoryHomeAdapter.CategoryItemViewHolder>(CategoryItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val binding =
            CategoryhomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class CategoryItemViewHolder(private val binding: CategoryhomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(CategoryItem: CategoryItem) {
            binding.apply {
                Glide.with(itemView)
                    .load(CategoryItem.image.src)
                    .into(imageCategory)
                nameategory.text = CategoryItem.name
            }
        }
    }

    class CategoryItemComparator : DiffUtil.ItemCallback<CategoryItem>() {
        override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem) =
            oldItem == newItem
    }
}

