package ir.project.rminkala.ui.Home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import dagger.hilt.android.AndroidEntryPoint
import ir.project.rminkala.R
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.databinding.FragmentHomeBinding
import ir.project.rminkala.ui.Category.CategoryAdapter
import ir.project.rminkala.ui.Category.CategoryViwModel
import ir.project.rminkala.ui.Detail.ProductClickHandler
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home)  {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val categoryViwModel: CategoryViwModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        val homeAdapter = HomeAdapter()
        val categoryAdapter = CategoryAdapter()
        slideImage()
        binding.apply {
            recViewMealsPopular.apply {
                adapter = homeAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.HORIZONTAL,
                    false
                )
                setHasFixedSize(true)
            }
        }
        homeViewModel.product.observe(viewLifecycleOwner) {
            homeAdapter.submitList(it)
        }

        binding.apply {
            recyclerView.apply {
                adapter = categoryAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.HORIZONTAL,
                    false
                )
                setHasFixedSize(true)
            }
        }
        categoryViwModel.category.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
        }
    }
    private fun slideImage(){
        val imageSlider = requireView().findViewById<ImageSlider>(R.id.slide_image)
        val imageList = ArrayList<SlideModel>()
        homeViewModel.slidePhoto.observe(viewLifecycleOwner){
          for (position in 1 .. 5) {
              imageList.add(SlideModel(it.images[position].src))
          }

            imageSlider.setImageList(imageList, ScaleTypes.FIT)
        }

    }




}