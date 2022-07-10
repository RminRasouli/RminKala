package ir.project.rminkala.ui.ShoppingCard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.project.rminkala.data.model.localDataModel.ProductLocalModel
import ir.project.rminkala.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ShoppingCardViewModel"
@HiltViewModel
class ShoppingCardViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    lateinit var AllShoppigCard : LiveData<List<ProductLocalModel>>

    init {
        viewModelScope.launch {
            AllShoppigCard = getAllProductDataBase().asLiveData()
        }
    }


    suspend fun insertProductDataBase(product: ProductLocalModel) {

        repository.insertProductDataBase(product)
    }

    private suspend fun getAllProductDataBase(): Flow<List<ProductLocalModel>> {

        return repository.getAllProductDataBase()
    }

    suspend fun updateProductDataBase(product: ProductLocalModel){
        repository.updateProductDataBase(product)
    }
    suspend fun  deletedProductDataBase(product: ProductLocalModel){
        repository.deletedProductDataBase(product)
    }
}