package ir.project.rminkala.ui.Detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import ir.project.rminkala.R
import ir.project.rminkala.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        val product = args

        binding.apply {
            binding.nameProject.text = product.productName
            binding.priceProduct.text = "${product.productPrice} تومان"
            binding.productDescription.text = product.productDescription

        }
    }

}