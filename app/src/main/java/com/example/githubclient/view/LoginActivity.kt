package com.example.githubclient.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.common.ext.otherwise
import com.example.common.ext.yes
import com.example.githubclient.R
import com.example.githubclient.presenter.LoginPresenter
import com.example.mvp.impl.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity :BaseActivity<LoginPresenter>(){

//    private val signInButton by lazy{findViewById<Button>(R.id.login)}
//    private val username by lazy{findViewById<EditText>(R.id.username)}
//    private val password by lazy{findViewById<EditText>(R.id.password)}
//    private val loginProgress by lazy{findViewById<ProgressBar>(R.id.loading)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginBtn.setOnClickListener{
            presenter.checkUserName(username.text.toString())
                .yes{
                    presenter.checkPasswd(password.text.toString())
                        .yes{
                            presenter.doLogin(username.text.toString(),password.text.toString() )
                        }
                        .otherwise {
                            showTips(password,"密码不合法")
                        }
                }
                .otherwise {
                    showTips(username,"用户名不合法")
                }
        }
    }

    private fun showTips(view:View? = null, tips:String){
        Toast.makeText(this,tips, Toast.LENGTH_SHORT).show()
    }

    fun onLoginStart(){
        showTips(tips = "登录开始")
    }

    fun onLoginError(e:Throwable){
        e.printStackTrace()
        showTips(tips = "登录失败")
    }


    fun onLoginSuccess(){
        showTips(tips = "登录成功")
    }

    fun onDataInit(name:String, passwd:String){
        username.setText(name)
        password.setText(passwd)

    }

    private fun showProgress(show:Boolean){
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)
        loadingProgress.animate()
            .setDuration(shortAnimTime.toLong())
            .alpha((if(show) 1 else 0).toFloat())
            .setListener(object: AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                    super.onAnimationEnd(animation)
                    loadingProgress.visibility = if(show) View.VISIBLE else View.GONE
                }
            })
    }
}