package com.satyajit.newsappnew.data.model

import com.google.gson.annotations.SerializedName

data class Sources(

    @SerializedName("id"          ) var id          : String = "0",
    @SerializedName("name"        ) var name        : String = "",
    @SerializedName("description" ) var description : String? = "",
    @SerializedName("url"         ) var url         : String? = "",
    @SerializedName("category"    ) var category    : String? = "",
    @SerializedName("language"    ) var language    : String? = "",
    @SerializedName("country"     ) var country     : String? = ""
)
