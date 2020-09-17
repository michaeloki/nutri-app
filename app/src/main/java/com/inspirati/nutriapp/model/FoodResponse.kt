package com.inspirati.nutriapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FoodResponse {
    @SerializedName("results")
    @Expose
    var results:List<RecipeResult> = emptyList()
    @SerializedName("baseUri")
    @Expose
    var baseUri = ""
    @SerializedName("offset")
    @Expose
    var offset:Int = 0

    @SerializedName("number")
    @Expose
    var number:Int = 0

    @SerializedName("totalResults")
    @Expose
    var totalResults:Int = 0

    @SerializedName("processingTimeMs")
    @Expose
    var processingTimeMs:Int = 0

    @SerializedName("expires")
    @Expose
    var expires:Any = 0
}