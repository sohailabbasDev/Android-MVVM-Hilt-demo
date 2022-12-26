package com.aregyan.github.network

import com.aregyan.github.network.model.NetworkUserDetails
import retrofit2.http.GET
import retrofit2.http.Path

//Single Api interface
interface UserDetailsService {

    //gets the single user API
    @GET("/users/{user}")
    suspend fun getUserDetails(@Path("user") user: String): NetworkUserDetails
}