package ir.project.rminkala.ui.Home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.mancj.materialsearchbar.MaterialSearchBar
import dagger.hilt.android.AndroidEntryPoint
import ir.project.rminkala.R
import ir.project.rminkala.databinding.FragmentHomeBinding
import ir.project.rminkala.ui.Category.CategoryAdapter
import ir.project.rminkala.ui.Category.CategoryViwModel
import ir.project.rminkala.ui.paging.ProductPagingAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    @Inject
    lateinit var productPagingAdapter : ProductPagingAdapter

    private lateinit var binding: FragmentHomeBinding
    private val productViewModel: ProductViewModel by viewModels()
    private val categoryViwModel: CategoryViwModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        val productAdapter = ProductAdapter()
        val categoryAdapter = CategoryAdapter()
        val categoryHomeAdapter = CategoryHomeAdapter()
        val searchbar = binding.searchBarOffic
        Log.d("product", "main Start: ")

        try {
            slideImage()
            binding.apply {
                recyclerView.apply {
                    adapter = categoryHomeAdapter
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

            binding.apply {
                recViewMealsPopular.apply {
                    adapter = productPagingAdapter
                    layoutManager = LinearLayoutManager(
                        requireContext(), LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    setHasFixedSize(true)
                }
            }

//            lifecycleScope.launch {
//                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                    productViewModel.productListFlow.collect {
//                        productAdapter.submitList(it.filter {
//                            it.average_rating != "0.00"
//                        })
//
//                    }
//                }
//            }

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    launch {
                        productViewModel.productPager.collect {
                            productPagingAdapter.submitData(it)
                            Log.d("product", it.toString())
                        }
                    }
                }
                launch {
                    productPagingAdapter.loadStateFlow.collect{
                        when(it.refresh){
                            is LoadState.Loading -> {
                                Log.d("TAG", "onViewCreated: Loading")
                                binding.stateView.onLoading()
                            }
                            is LoadState.Error -> {
                                binding.stateView.onFail()
                                binding.stateView.clickRequest {
                                    productPagingAdapter.retry()
                                }
                            }
                            is LoadState.NotLoading -> {
                                Log.d("TAG", "onViewCreated: NotLoading")
                                binding.stateView.onSuccess()
                            }
                        }
                    }
                }

            }



        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Cant Update ", Toast.LENGTH_SHORT).show()
        }

        searchbar.setOnSearchActionListener(object : MaterialSearchBar.OnSearchActionListener {
            override fun onSearchStateChanged(enabled: Boolean) {
                Log.d("home", "onSearchConfirmed:  $enabled ")
            }

            override fun onSearchConfirmed(text: CharSequence) {
                val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment(
                    categoryId = 0,
                    wordSearch = text.toString()
                )
                Navigation.findNavController(view).navigate(
                    action
                )
            }

            override fun onButtonClicked(buttonCode: Int) {
                when (buttonCode) {
                    MaterialSearchBar.BUTTON_NAVIGATION -> {}
                    MaterialSearchBar.BUTTON_SPEECH -> {}
                    MaterialSearchBar.BUTTON_BACK -> searchbar.closeSearch()
                }
            }
        })

    }

    private fun slideImage() {
        val imageSlider = requireView().findViewById<ImageSlider>(R.id.slide_image)
        val imageList = ArrayList<SlideModel>()
        productViewModel.slidePhoto.observe(viewLifecycleOwner) {
            for (position in 0..4) {
                imageList.add(SlideModel(it.images[position].src))
            }
            imageSlider.setImageList(imageList, ScaleTypes.FIT)
        }
    }
}


