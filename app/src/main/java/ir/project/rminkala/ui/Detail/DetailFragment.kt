package ir.project.rminkala.ui.Detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ir.project.rminkala.R
import ir.project.rminkala.data.model.localDataModel.ProductLocalModel
import ir.project.rminkala.databinding.FragmentDetailBinding
import ir.project.rminkala.ui.Home.ProductViewModel
import ir.project.rminkala.ui.Review.ReviewAdapter
import ir.project.rminkala.ui.Review.ReviewViewModel
import ir.project.rminkala.ui.ShoppingCard.ShoppingCardViewModel
import ir.project.rminkala.util.FaNum
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding
    private val productViewModel: ProductViewModel by viewModels()
    private val shoppingCardViewModel: ShoppingCardViewModel by viewModels()
    private val reviewViewModel: ReviewViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        val product = args
        val detailProductApapter = DetailProductApapter()
        val reviewAdapter = ReviewAdapter()

        binding.apply {
            binding.nameProductDetail.text = product.productName
            binding.productPrice.text = FaNum.convert(product.productPrice)
            binding.productDescription.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(product.productDescription, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(product.productDescription)
            }
            slideImage()
        }

        binding.productAdded.setOnClickListener {
            lifecycleScope.launch {
                shoppingCardViewModel.insertProductDataBase(
                    ProductLocalModel(
                        product.productId,
                        product.productName,
                        product.productPrice
                    )
                )
            }
            Snackbar.make(
                requireView(),
                "محصول شما با موفقیت درسبد خرید ثبت شد",
                Snackbar.LENGTH_SHORT
            )
                .setAction("حذف از سبد خرید") {
                    lifecycleScope.launch {
                        shoppingCardViewModel.deletedProductDataBase(
                            ProductLocalModel(
                                product.productId,
                                product.productName,
                                product.productPrice
                            )
                        )
                    }
                }.show()
        }
        binding.addComment.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToAddReviewFragment(
                product.productId ,
                product.productName
            )
            it.findNavController().navigate(
                action
            )
        }
        binding.apply {
            recyclerviewLike.apply {
                adapter = detailProductApapter
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.HORIZONTAL,
                    false
                )
                setHasFixedSize(true)
            }
        }

        binding.apply {
            reviewShow.apply {
                adapter = reviewAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.HORIZONTAL,
                    false
                )
                setHasFixedSize(true)
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                reviewViewModel.review.collect {

                    if (it.isNotEmpty()) {
                        val comments = it.filter {
                            it.product_id.toString() == product.productId
                        }
                        if (comments.isNotEmpty()) {
                            reviewAdapter.submitList(comments)

                            binding.apply {
                                reviewShow.isVisible = true
                                emptyComment.isVisible = false
                            }
                        }

                    }
                }
            }
        }


        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                productViewModel.productListFlow.collect {
                    detailProductApapter.submitList(it)
                }
            }
        }
    }

    private fun slideImage() {
        val countImage = args.productImage.size
        val imageSlider = requireView().findViewById<ImageSlider>(R.id.slide_product_image)
        val imageList = ArrayList<SlideModel>()
        args.productImage.apply {
            for (position in 0 until countImage) {
                imageList.add(SlideModel(this[position].src))
            }
            imageSlider.setImageList(imageList, ScaleTypes.FIT)
        }
    }
}
