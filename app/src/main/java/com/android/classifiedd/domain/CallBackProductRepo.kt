package com.android.classifiedd.domain


import com.android.classifiedd.data.models.Products

interface CallBackProductRepo {
    suspend fun getProductsData() : Products?
}