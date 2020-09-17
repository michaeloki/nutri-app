package com.inspirati.nutriapp.repositories

import androidx.lifecycle.LiveData
import com.inspirati.nutriapp.model.RecipeResult
import com.inspirati.nutriapp.network.ApiClient


class RecipeRepository private constructor() {
    private var mApiClient: ApiClient = ApiClient.getInstance()
    private lateinit var mQuery: String
    private var mNumber: Int = 0
    val recipes: LiveData<List<RecipeResult>>
        get() {
            return mApiClient.recipes
        }

    fun searchRecipesApi(query: String, numberOfItems: Int) {
        mQuery = query
        mNumber = numberOfItems
        mApiClient.searchRecipesApi(query, numberOfItems)
    }

    fun searchNextPage() {
        searchRecipesApi(mQuery, mNumber)
    }

    companion object {
        private lateinit var instance: RecipeRepository
        fun getInstance(): RecipeRepository {

            instance = RecipeRepository()

            return instance
        }
    }

}