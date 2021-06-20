package com.android.classifiedd.domain

import com.android.classifiedd.data.models.Products
import com.android.classifiedd.data.repository.ProductRepo
import com.android.classifiedd.domain.CallBackProductRepo

class ProductRepoImpl : CallBackProductRepo {
    override suspend fun getProductsData(): Products? {
        return ProductRepo.getAllProducts1()
    }
}