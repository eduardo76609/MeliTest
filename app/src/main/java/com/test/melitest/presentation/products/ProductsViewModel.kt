package com.test.melitest.presentation.products

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.melitest.core.network.RequestResult
import com.test.melitest.domain.products.models.Product
import com.test.melitest.domain.products.models.ProductsResult
import com.test.melitest.domain.products.usecases.SearchProductsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.test.melitest.core.network.ErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped

@HiltViewModel
class ProductsViewModel @Inject constructor(private val searchProductsUseCase: SearchProductsUseCase) :
    ViewModel() {

    private val _searchTextState: MutableState<String> = mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    // ------------------------------------------------------------------------
    private val _mutableProductsStates: MutableState<ProductsStates> =
        mutableStateOf(value = ProductsStates.Initial)
    val mutableProductsStates: State<ProductsStates> = _mutableProductsStates

    fun searchProducts(value: String) {
        viewModelScope.launch {
            _mutableProductsStates.value = ProductsStates.Loading
            when (val result = searchProductsUseCase.execute(value)) {
                is RequestResult.Success -> {
                    result.data.let {
                        _mutableProductsStates.value = ProductsStates.ProductsData(it?.results)
                    }
                }

                is RequestResult.Failure -> {
                    _mutableProductsStates.value = ProductsStates.Error(result.errorData.toString())
                }
            }
        }
    }

    fun setSearchingState(){
        _mutableProductsStates.value = ProductsStates.Searching
    }

    fun setInitialState(){
        _mutableProductsStates.value = ProductsStates.Initial
    }

}

sealed class ProductsStates {
    object Initial : ProductsStates()
    object Searching : ProductsStates()
    object Loading : ProductsStates()
    class ProductsData(val products: List<Product>?) : ProductsStates()
    class Error(val error: String) : ProductsStates()
}