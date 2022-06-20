package ir.project.rminkala.ui.Home

import android.os.Bundle
import android.text.TextUtils.replace
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import ir.project.rminkala.R
import ir.project.rminkala.databinding.FragmentHomeBinding
import ir.project.rminkala.ui.Category.CategoryAdapter
import ir.project.rminkala.ui.Category.CategoryViwModel
import ir.project.rminkala.ui.Search.SearchFragment

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home)  {

    private lateinit var binding: FragmentHomeBinding
    private val productViewModel: ProductViewModel by viewModels()
    private val categoryViwModel: CategoryViwModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        val productAdapter = ProductAdapter()
        val categoryAdapter = CategoryAdapter()
        slideImage()
        binding.apply {
            recViewMealsPopular.apply {
                adapter = productAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.HORIZONTAL,
                    false
                )
                setHasFixedSize(true)
            }
        }
        productViewModel.product.observe(viewLifecycleOwner) {
            productAdapter.submitList(it)
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



        binding.searchbar.setOnClickListener{
            parentFragmentManager.commit {
                replace<SearchFragment>(R.id.frag_host)
                addToBackStack(null)
            }
        }
    }
    private fun slideImage(){
        val imageSlider = requireView().findViewById<ImageSlider>(R.id.slide_image)
        val imageList = ArrayList<SlideModel>()
        productViewModel.slidePhoto.observe(viewLifecycleOwner){
          for (position in 1 .. 5) {
              imageList.add(SlideModel(it.images[position].src))
          }

            imageSlider.setImageList(imageList, ScaleTypes.FIT)
        }

    }




}