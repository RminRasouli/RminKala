package ir.project.rminkala.ui.Home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.project.rminkala.data.model.ImageSlaider.PhotoSlider
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.data.repository.Repository
import ir.project.rminkala.ui.paging.ProductPagingSource
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

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
        getAllProduct()
        viewModelScope.launch {
            val responseSlidePhoto =  repository.getSliderPhoto()
            if (responseSlidePhoto.isSuccessful){
                _lastSlidePhoto.value = responseSlidePhoto.body()
            }
        }
    }

    private fun getAllProduct() {
        viewModelScope.launch {
            val flow = repository.getAllProduct()
            flow.collect {
                _productListFlow.emit(it)
            }
        }
    }

    val productPager = Pager(
        PagingConfig(pageSize = 30)
    ) {
        ProductPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

}