package com.example.githubclient.network.services

import com.example.githubclient.network.entities.User
import com.example.githubclient.network.retrofit
import io.reactivex.Observable
import retrofit2.http.GET

interface UserApi {
    @GET("/user")
    fun getAuthenticatedUser():Observable<User>
}

object UserService:UserApi by retrofit.create(UserApi::class.java)