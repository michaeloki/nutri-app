package com.inspirati.nutriapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.inspirati.nutriapp.R
import com.inspirati.nutriapp.model.RecipeResult

class CustomAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mRecipes: List<RecipeResult>? = null

    private var baseImageUri = "https://spoonacular.com/recipeImages/"

    @NonNull
    override fun onCreateViewHolder(
        @NonNull viewGroup: ViewGroup,
        i: Int
    ): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.list_item_recipe,
            viewGroup, false
        )
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull viewHolder: RecyclerView.ViewHolder, i: Int) {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
        Glide.with(viewHolder.itemView.context)
            .setDefaultRequestOptions(requestOptions)
            .load(baseImageUri + mRecipes!![i].image)
            .into((viewHolder as CustomViewHolder).imageView)

        (viewHolder).title.text = mRecipes!![i].title
        (viewHolder).duration.text = mRecipes!![i].readyInMinutes.toString()
    }

    fun setRecipes(recipes: List<RecipeResult>) {
        mRecipes = recipes
        notifyDataSetChanged()
    }


    inner class CustomViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var title: TextView = itemView.findViewById(R.id.recipe_title)
        internal var duration: TextView = itemView.findViewById(R.id.recipe_duration)
        internal var imageView: ImageView = itemView.findViewById(R.id.recipe_thumbnail)
    }

    override fun getItemCount(): Int {
        if (mRecipes != null) {
            return mRecipes!!.size
        }
        return 0
    }
}