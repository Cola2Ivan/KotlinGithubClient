package com.example.githubclient.presenter

import com.example.githubclient.model.account.AccountManager
import com.example.githubclient.view.LoginActivity
import com.example.mvp.impl.BasePresenter

class LoginPresenter: BasePresenter<LoginActivity>() {

    fun doLogin(name:String, password:String){
        AccountManager.username = name
        AccountManager.password = password
        view.onLoginStart()
        AccountManager.login()
            .subscribe({
                view.onLoginSuccess()
            },{
                view.onLoginError(it)
            })
    }

    fun checkUserName(name:String):Boolean{
        return true
    }

    fun checkPasswd(passwd:String):Boolean{
        return true
    }

    override fun onResume() {
        super.onResume()
        view.onDataInit(AccountManager.username,AccountManager.password)
    }
}