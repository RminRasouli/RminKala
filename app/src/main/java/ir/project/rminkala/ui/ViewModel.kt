package ir.project.rminkala.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pourkazemi.mahdi.mymaktabplus.data.localdetabase.data_store.SettingDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.data.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: Repository,
    private val settingDataStore: SettingDataStore
) : ViewModel() {
    val preferences = settingDataStore.preferences
    val response : MutableLiveData<List<Product>>
        get() = _response

    private val _response = MutableLiveData<List<Product>>()
    init {

    }
}