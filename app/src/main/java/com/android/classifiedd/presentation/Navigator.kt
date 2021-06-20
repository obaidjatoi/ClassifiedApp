package com.android.classifiedd.presentation

import androidx.navigation.NavController
import com.android.classifiedd.data.models.ProductListing
import com.android.classifiedd.presentation.view.ProductListingDirections

fun navigateToDetails(navController : NavController , product : ProductListing) {
    navController.navigate(ProductListingDirections.actionProductListingToProductDetail(product))
}