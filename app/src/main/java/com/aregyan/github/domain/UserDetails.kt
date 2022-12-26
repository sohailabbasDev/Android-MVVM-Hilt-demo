package com.aregyan.github.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//This data class represents our data object
@Parcelize
data class UserDetails(
    val user: String? = "",
    val avatar: String? = "",
    val name: String? = "",
    val userSince: String? = "",
    val location: String? = ""
) : Parcelable