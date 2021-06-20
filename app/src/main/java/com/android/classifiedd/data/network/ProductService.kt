package com.android.classifiedd.data.network

import com.android.classifiedd.data.models.Products
import retrofit2.http.GET
import retrofit2.http.Url

interface ProductService {
    @GET()
    suspend fun getProductListing(@Url() url: String = ""): Products?

    companion object {
        fun getInstance(): ProductService {
            return RetroClient.ottRetrofit.create(ProductService::class.java)
        }
    }
}