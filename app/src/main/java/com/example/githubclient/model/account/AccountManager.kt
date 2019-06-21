package com.example.githubclient.model.account

import com.example.githubclient.network.entities.AuthorizationReq
import com.example.githubclient.network.entities.AuthorizationRsp
import com.example.githubclient.network.entities.User
import com.example.githubclient.network.services.AuthService
import com.example.githubclient.network.services.UserService
import com.example.githubclient.utils.fromJson
import com.example.githubclient.utils.pref
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

interface OnAccountStateChangeLisener{

    fun onLogin(user:User)

    fun onLogout()


}

object AccountManager {

    var authId by pref(-1)
    var username by pref("")
    var password by pref("")
    var token by pref("")
    private var userJson by pref("")



    var currentUser:User? = null
        get() {
            if(field == null && userJson.isNotEmpty()){
                field = Gson().fromJson<User>(userJson)
            }
            return field
        }
        set(value) {
            if(value == null){
                userJson = ""
            }else {
                userJson = Gson().toJson(value)
            }
            field = value
        }

    var onAccountStateChangeLiseners = ArrayList<OnAccountStateChangeLisener>()

    private fun notifyLogin(user: User){
        onAccountStateChangeLiseners.forEach{
            it.onLogin(user)
        }
    }

    private fun notifyLoginout(){
        onAccountStateChangeLiseners.forEach{
            it.onLogout()
        }
    }


    fun isLoggedIn(): Boolean = token.isNotEmpty()



    fun login() =
        AuthService.createAuthorization(AuthorizationReq())
            .doOnNext {
                if (it.token.isEmpty()) throw AccountException(it)
            }
            .retryWhen {
                it.flatMap {
                    if (it is AccountException) {
                        AuthService.deleteAuthorization(it.authorizationRsp.id)
                    } else {
                        Observable.error(it)
                    }
                }
            }
            .flatMap {
                token = it.token
                authId = it.id
                UserService.getAuthenticatedUser()
            }.map {
                currentUser = it
                notifyLogin(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun logout() =
        AuthService.deleteAuthorization(authId)
            .doOnNext {
                if(it.isSuccessful){
                    authId = -1
                    token = ""
                    currentUser = null
                    notifyLoginout()
                }else {
                    throw HttpException(it)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    class AccountException(val authorizationRsp: AuthorizationRsp) : Exception("Already logged in.")
}