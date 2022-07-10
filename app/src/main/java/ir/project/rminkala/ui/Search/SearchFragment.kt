package ir.project.rminkala.ui.Search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.project.rminkala.R
import ir.project.rminkala.databinding.FragmentSearchBinding
import ir.project.rminkala.ui.Category.CategoryViwModel
import ir.project.rminkala.ui.Home.ProductViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val args: SearchFragmentArgs by navArgs()
    private val searchViewModel: SearchViewModel by viewModels()
    private val productViewModel: ProductViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSearchBinding.bind(view)
        val searchAdapter = SearchAdapter()
        val categoryId = args.categoryId
        val wordSearch = args.wordSearch
        binding.apply {
            recyclerViewSearch.apply {
                adapter = searchAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                if (categoryId != 0){
                    productViewModel.productListFlow.collect {
                        searchAdapter.submitList(it.filter {
                            categoryId == it.categories[0].id
                        })
                    }
            }else{
                searchViewModel.getProductBySearch(wordSearch)
                searchViewModel.productListFlow.collect {
                    searchAdapter.submitList(it)
                }

                }
            }
        }
    }

}




