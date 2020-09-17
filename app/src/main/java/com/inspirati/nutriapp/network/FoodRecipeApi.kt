package com.inspirati.nutriapp.network

import com.inspirati.nutriapp.model.FoodResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface FoodRecipeApi {
    @GET("recipes/search")
    fun searchRecipe(
        @Query("apiKey") key: String?,
        @Query("query") query: String?,
        @Query("number") page: Int
    ): Call<FoodResponse?>?
}