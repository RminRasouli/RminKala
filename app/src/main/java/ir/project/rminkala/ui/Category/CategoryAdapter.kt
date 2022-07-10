package ir.project.rminkala.ui.Category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.project.rminkala.data.model.categoty.CategoryItem
import ir.project.rminkala.databinding.CategoryCardItemBinding
import ir.project.rminkala.ui.Home.HomeFragmentDirections

class CategoryAdapter :
    ListAdapter<CategoryItem, CategoryAdapter.CategoryItemViewHolder>(CategoryItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val binding =
            CategoryCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class CategoryItemViewHolder(private val binding: CategoryCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(CategoryItem: CategoryItem) {
            binding.apply {
                Glide.with(itemView)
                    .load(CategoryItem.image.src)
                    .into(imgCategory)
                textCategory.text = CategoryItem.name

                itemView.setOnClickListener {
                    val action = CategoryFragmentDirections.actionCategoryFragmentToSearchFragment(
                      categoryId = CategoryItem.id,
                        ""
                    )
                    it.findNavController().navigate(
                        action
                    )
                }
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

