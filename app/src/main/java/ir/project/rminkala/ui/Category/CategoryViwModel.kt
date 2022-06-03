package ir.project.rminkala.ui.Category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.project.rminkala.data.model.categoty.CategoryItem
import ir.project.rminkala.data.repository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryViwModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val category : MutableLiveData<List<CategoryItem>>
        get() = _category

    private val _category = MutableLiveData<List<CategoryItem>>()
    init {
        viewModelScope.launch {
            val res =  repository.getCategories()
            if (res.isSuccessful){
                _category.value = res.body()
            }
        }
    }
}