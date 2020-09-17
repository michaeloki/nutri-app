package com.inspirati.nutriapp.network


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.inspirati.nutriapp.AppExecutors
import com.inspirati.nutriapp.model.FoodResponse
import com.inspirati.nutriapp.model.RecipeResult
import com.inspirati.nutriapp.utils.Constants.API_KEY
import com.inspirati.nutriapp.utils.Constants.NETWORK_TIMEOUT
import retrofit2.Call
import java.util.concurrent.TimeUnit

class ApiClient private constructor() {
    private var mRecipes: MutableLiveData<List<RecipeResult>> = MutableLiveData()

    private lateinit var mRetrieveRecipesRunnable: RetrieveRecipesRunnable
    val recipes: LiveData<List<RecipeResult>>
        get() {
            return mRecipes
        }

    fun searchRecipesApi(query: String, numberOfItems: Int) {

        mRetrieveRecipesRunnable = RetrieveRecipesRunnable(query, numberOfItems)
        val handler = AppExecutors.get().networkIO().submit(mRetrieveRecipesRunnable)

        AppExecutors.get().networkIO()
            .schedule({ handler.cancel(true) }, NETWORK_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
    }


    private inner class RetrieveRecipesRunnable(query: String, numberOfItems: Int) : Runnable {
        private val query: String
        private var numberOfItems: Int = 0
        private var cancelRequest: Boolean = false

        init {
            this.query = query
            this.numberOfItems = numberOfItems
            cancelRequest = false
        }

        override fun run() {
            try {
                val response = getRecipes(query, numberOfItems)!!.execute()
                if (cancelRequest) {
                    return
                }
                if (response.code() == 200) {
                    val list = ArrayList((response.body() as FoodResponse).results)
                    mRecipes.postValue(list)
                    val currentRecipes = mRecipes.value
                    currentRecipes!!.plus(list)
                    mRecipes.postValue(currentRecipes)
                } else {
                    mRecipes.postValue(null)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                mRecipes.postValue(null)
            }
        }

        private fun getRecipes(query: String, numberOfItems: Int): Call<FoodResponse?>? {
            return ServiceGenerator.recipeApi.searchRecipe(
                API_KEY,
                query,
                (numberOfItems)
            )
        }
    }

    companion object {
        private lateinit var instance: ApiClient
        fun getInstance(): ApiClient {
            instance = ApiClient()
            return instance
        }
    }
}