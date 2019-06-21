package com.example.githubclient.network.services

import com.example.githubclient.network.entities.AuthorizationReq
import com.example.githubclient.network.entities.AuthorizationRsp
import com.example.githubclient.network.retrofit
import com.example.githubclient.settings.Configs
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuthApi{

    @PUT("/authorizations/clients/${Configs.Account.clientId}/{fingerPrint}")
    fun createAuthorization(@Body req:AuthorizationReq,@Path("fingerPrint") fingerPrint:String = Configs.Account.fingerPrint)
        :Observable<AuthorizationRsp>

    @DELETE("/authorizations/{id}")
    fun deleteAuthorization(@Path("id") id:Int):Observable<Response<Any?>>

}

object AuthService :AuthApi by retrofit.create(AuthApi::class.java)