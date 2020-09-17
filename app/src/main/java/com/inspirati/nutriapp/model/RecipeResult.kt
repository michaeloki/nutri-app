package com.inspirati.nutriapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RecipeResult {
    @SerializedName("id")
    @Expose
    var id:Int = 0

    @SerializedName("title")
    @Expose
    lateinit var title:String
    @SerializedName("readyInMinutes")
    @Expose
    var readyInMinutes:Int = 0

    @SerializedName("servings")
    @Expose
    var servings:Int = 0

    @SerializedName("sourceUrl")
    @Expose
    lateinit var sourceUrl:String
    @SerializedName("openLicense")
    @Expose
    var openLicense:Int = 0

    @SerializedName("image")
    @Expose
    lateinit var image:String
}