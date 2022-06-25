package ir.project.rminkala.ui.Home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.project.rminkala.data.model.ImageSlaider.PhotoSlider
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.data.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val product : MutableLiveData<List<Product>>
        get() = _lastProduct
    private val _lastProduct = MutableLiveData<List<Product>>()


    val slidePhoto :  MutableLiveData<PhotoSlider>
        get() = _lastSlidePhoto

    private val _lastSlidePhoto = MutableLiveData<PhotoSlider>()



    private val _productListFlow: MutableStateFlow<List<Product>> = MutableStateFlow(listOf())
    val productListFlow get() = _productListFlow.asStateFlow()



    init {

        getFlowProductTest()
        viewModelScope.launch {
            val responseProduct =  repository.getRemoteAllProduct()
            if (responseProduct.isSuccessful){
                _lastProduct.value = responseProduct.body()
            }
        }
        viewModelScope.launch {
            val responseSlidePhoto =  repository.getSliderPhoto()
            if (responseSlidePhoto.isSuccessful){
                _lastSlidePhoto.value = responseSlidePhoto.body()
            }
        }
    }

    private fun getFlowProductTest() {
        viewModelScope.launch {
            val flow = repository.getFlowProductTest()
            flow.collect {
                _productListFlow.emit(it)
            }
        }
    }
}