package com.example.myapplication.api_operations.data.models

import com.google.gson.annotations.SerializedName

data class MarsPhoto(val id: String,
    @SerializedName("img_src") val imageSrc: String,
                     )
