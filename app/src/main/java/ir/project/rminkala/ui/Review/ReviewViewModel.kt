package ir.project.rminkala.ui.Review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.project.rminkala.data.model.product.Product
import ir.project.rminkala.data.model.review.ReviewItem
import ir.project.rminkala.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _reviewList: MutableStateFlow<List<ReviewItem>> = MutableStateFlow(listOf())
    val review get() = _reviewList.asStateFlow()

    init {
        getReview()
    }

    private fun getReview() {
        viewModelScope.launch {
            val flow = repository.getAllReview()
            flow.collect {
                _reviewList.emit(it)
            }
        }
    }
}