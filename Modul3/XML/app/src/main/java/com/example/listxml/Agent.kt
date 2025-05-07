package com.example.listxml

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Agent (
    val name : String,
    val role : String,
    val desc : String,
    val image : Int,
    val detail : String = "",
): Parcelable