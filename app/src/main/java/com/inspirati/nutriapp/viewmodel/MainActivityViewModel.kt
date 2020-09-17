package com.inspirati.nutriapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.inspirati.nutriapp.model.RecipeResult
import com.inspirati.nutriapp.repositories.RecipeRepository


class MainActivityViewModel : ViewModel() {
    private val mRecipeRepository: RecipeRepository = RecipeRepository.getInstance()
    private var mIsQueryingRecipes: Boolean
    private var mIsViewingRecipes = false
    val recipes: LiveData<List<RecipeResult>>
        get() = mRecipeRepository.recipes

    fun isViewingRecipes(): Boolean {
        return mIsViewingRecipes
    }

    fun setIsQueryingRecipes(mIsQueryingRecipes: Boolean) {
        this.mIsQueryingRecipes = mIsQueryingRecipes
    }

    fun searchRecipesApi(query: String?, pageNumber: Int) {
        mIsViewingRecipes = true
        mRecipeRepository.searchRecipesApi(query!!, pageNumber)
        mIsQueryingRecipes = true
    }


    init {
        mIsQueryingRecipes = false
    }

    fun searchNextPage() {
        if (!mIsQueryingRecipes && mIsViewingRecipes) {
            mRecipeRepository.searchNextPage()
        }
    }
}