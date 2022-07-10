package ir.project.rminkala.ui.ShoppingCard

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.project.rminkala.R
import ir.project.rminkala.data.model.localDataModel.ProductLocalModel
import ir.project.rminkala.databinding.FragmentShoppingCardBinding

@AndroidEntryPoint
class ShoppingCardFragment : Fragment(R.layout.fragment_shopping_card) {

    private val shoppingCardViewModel: ShoppingCardViewModel by viewModels()
    private lateinit var binding: FragmentShoppingCardBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentShoppingCardBinding.bind(view)
        val shoppingCardAdapter = ShoppingCardAdapter()


        val countProduct = shoppingCardViewModel.AllShoppigCard.value?.size
        val products = shoppingCardViewModel.AllShoppigCard.value
        var priceAll = 0

        binding.apply {
            shippingCardRecyclerview.apply {
                adapter = shoppingCardAdapter
                layoutManager = LinearLayoutManager(
                    requireContext()
                )
                setHasFixedSize(true)
            }

            textPriceAll.text = countProduct.toString()
        }
        shoppingCardViewModel.AllShoppigCard.observe(viewLifecycleOwner) { result ->
            shoppingCardAdapter.submitList(result)
            binding.emptyShoppingCard.isInvisible = result.isNotEmpty()
        }

    }
}