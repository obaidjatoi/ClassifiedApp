package com.android.classifiedd.data.repository


import androidx.lifecycle.MutableLiveData
import com.android.classifiedd.data.models.Products
import com.android.classifiedd.data.network.ProductService

object ProductRepo {
    suspend fun getAllProducts1(): Products? {
        try {
            val responseBody = ProductService.getInstance().getProductListing()
            responseBody?.let {
                return responseBody
            } ?: kotlin.run {
                return null
            }
        } catch (ee: java.lang.Exception) {
            return null
        }
    }
}