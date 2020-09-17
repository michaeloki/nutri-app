package com.inspirati.nutriapp

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inspirati.nutriapp.adapter.CustomAdapter
import com.inspirati.nutriapp.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: CustomAdapter
    private lateinit var mSearchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.recipe_list)
        mSearchView = findViewById(R.id.search_view)
        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        initRecyclerView()
        subscribeObservers()
        initSearchView()
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    private fun subscribeObservers() {
        mMainActivityViewModel.recipes.observe(this,
            { recipes ->
                if (recipes != null) {
                    if (mMainActivityViewModel.isViewingRecipes()) {
                        mAdapter.setRecipes(recipes)
                        mMainActivityViewModel.setIsQueryingRecipes(false)
                    }
                }
            })
    }

    private fun initRecyclerView() {
        mAdapter = CustomAdapter()
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(@NonNull recyclerView: RecyclerView, newState: Int) {
                if (!mRecyclerView.canScrollVertically(1)) {
                    mMainActivityViewModel.searchNextPage()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun initSearchView() {
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mMainActivityViewModel.searchRecipesApi(query, 5)
                mSearchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }
}