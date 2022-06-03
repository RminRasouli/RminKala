package ir.project.rminkala.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.data.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val response : MutableLiveData<List<Product>>
        get() = _response

    private val _response = MutableLiveData<List<Product>>()
    init {
        viewModelScope.launch {
            val res =  repository.getRemoteAllProduct()
            if (res.isSuccessful){
                _response.value = res.body()
            }
        }
    }
}