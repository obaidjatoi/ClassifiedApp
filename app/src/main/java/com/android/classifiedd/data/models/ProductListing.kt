package com.android.classifiedd.data.models

import java.io.Serializable


data class ProductListing(var created_at : String,
                          var price : String,
                          var name : String,
                          var uid : String,
                          var image_ids : MutableList<String>,
                          var image_urls : MutableList<String>,
                          var image_urls_thumbnails : MutableList<String>) : Serializable

data class Products(var results: List<ProductListing?>?) : Serializable