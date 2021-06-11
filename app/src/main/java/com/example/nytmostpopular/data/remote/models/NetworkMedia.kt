package com.example.nytmostpopular.data.remote.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NetworkMedia(
    @SerializedName("media-metadata") val media_metadata: List<NetworkMediaMetadata>,
)