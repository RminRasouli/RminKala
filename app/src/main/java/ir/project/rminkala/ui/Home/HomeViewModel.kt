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

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val product : MutableLiveData<List<Product>>
        get() = _lastProduct

    private val _lastProduct = MutableLiveData<List<Product>>()


    val slidePhoto :  MutableLiveData<PhotoSlider>
        get() = _lastSlidePhoto

    private val _lastSlidePhoto = MutableLiveData<PhotoSlider>()

    init {
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
}