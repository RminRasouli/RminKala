package ir.project.rminkala.ui.Category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.project.rminkala.data.model.categoty.CategoryItem
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryViwModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _category: MutableStateFlow<List<CategoryItem>> = MutableStateFlow(listOf())
    val category get() = _category.asStateFlow()

    init {
        getAllCategoryList()
    }

    private fun getAllCategoryList() {
        viewModelScope.launch {
            val flow = repository.getAllCategoryList()
            flow.collect {
                _category.emit(it)
            }
        }
    }
}