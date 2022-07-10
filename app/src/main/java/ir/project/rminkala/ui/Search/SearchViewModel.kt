package ir.project.rminkala.ui.Search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _productListFlow: MutableStateFlow<List<Product>> = MutableStateFlow(listOf())
    val productListFlow get() = _productListFlow.asStateFlow()



     fun getProductBySearch(word : String){
        viewModelScope.launch {
            val flow = repository.getProductBySearch(word)
            flow.collect {
                _productListFlow.emit(it)
            }
        }
    }
}