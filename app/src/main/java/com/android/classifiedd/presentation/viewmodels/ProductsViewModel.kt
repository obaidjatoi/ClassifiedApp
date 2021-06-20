package com.android.classifiedd.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.classifiedd.data.models.ProductListing
import com.android.classifiedd.domain.ProductRepoImpl
import com.android.classifiedd.presentation.view.ScreenState
import com.android.classifiedd.utils.hasInternetConnection
import kotlinx.coroutines.launch

class ProductsViewModel(application: Application) : AndroidViewModel(application) {
    var screenState: MutableLiveData<ScreenState> = MutableLiveData()
    var data : ArrayList<ProductListing> = ArrayList()
    var prodImp = ProductRepoImpl()

    fun getProducts() {
        if (!hasInternetConnection(getApplication())){
            screenState.value = ScreenState.NETWORK_ERROR
            return
        }

        screenState.value = ScreenState.DATA_LOADING
        viewModelScope.launch {
            val res = prodImp.getProductsData()
            res?.let { response ->
                data = response.results as ArrayList<ProductListing>
                screenState.value = ScreenState.DATA_LOADED
            } ?: kotlin.run {
                notifyError()
            }
        }
    }

    private fun notifyError() {
        screenState.value = ScreenState.ERROR
    }
}