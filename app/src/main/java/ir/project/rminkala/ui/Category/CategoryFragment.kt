package ir.project.rminkala.ui.Category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.project.rminkala.R
import ir.project.rminkala.databinding.FragmentCategoryBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category) {
    private lateinit var binding: FragmentCategoryBinding
    private val categoryViwModel: CategoryViwModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var binding = FragmentCategoryBinding.bind(view)
        val categoryAdapter = CategoryAdapter()

        binding.apply {
            recyclerCategory.apply {
                adapter = categoryAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                categoryViwModel.category.collect {
                    categoryAdapter.submitList(it)
                }
            }
        }
    }
}
