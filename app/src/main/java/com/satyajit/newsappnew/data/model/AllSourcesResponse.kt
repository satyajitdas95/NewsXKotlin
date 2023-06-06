package com.satyajit.newsappnew.data.model

import com.google.gson.annotations.SerializedName

data class AllSourcesResponse (

  @SerializedName("status"  ) var status  : String?            = null,
  @SerializedName("sources" ) var sources : ArrayList<Sources> = arrayListOf()

)