package ir.project.rminkala.ui.Detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import dagger.hilt.android.AndroidEntryPoint
import ir.project.rminkala.R
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.databinding.FragmentDetailBinding
import ir.project.rminkala.ui.Home.ProductAdapter
import ir.project.rminkala.ui.Home.ProductViewModel
import ir.project.rminkala.ui.ShoppingCard.ShoppingCardViewModel
@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding
    private val productViewModel: ProductViewModel by viewModels()
    private val shoppingCardViewModel : ShoppingCardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        val product = args
        val detailProductApapter = DetailProductApapter()

        binding.apply {
            binding.nameProductDetail.text = product.productName
            binding.productPrice.text = product.productPrice
            binding.productDescription.text = product.productDescription
        }

        slideImage()

        binding.productAdded.setOnClickListener {

        }



        binding.apply {
            recyclerDetail.apply {
                adapter = detailProductApapter
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.HORIZONTAL,
                    false
                )
                setHasFixedSize(true)
            }
        }
        productViewModel.product.observe(viewLifecycleOwner) {
            detailProductApapter.submitList(it)
        }


    }
        private fun slideImage() {
            val imageSlider = requireView().findViewById<ImageSlider>(R.id.slide_product_image)
            val imageList = ArrayList<SlideModel>()
            args.productImage.apply {
                for (position in 1..2) {
                    imageList.add(SlideModel(this[position].src))
                }
                imageSlider.setImageList(imageList, ScaleTypes.FIT)
            }
        }
    }
