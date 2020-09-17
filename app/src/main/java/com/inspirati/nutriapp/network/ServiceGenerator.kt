package com.inspirati.nutriapp.network

import com.inspirati.nutriapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {
    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create())
    private val retrofit = retrofitBuilder.build()
    val recipeApi = retrofit.create(FoodRecipeApi::class.java)
}