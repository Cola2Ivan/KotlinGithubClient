package com.example.mvp.impl

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.mvp.IMvpView
import com.example.mvp.IPresenter
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

abstract class BaseFragment<out P : BasePresenter<BaseFragment<P>>> : Fragment(), IMvpView<P> {

    override val presenter: P

    init {
        presenter = createPresenter()
        presenter.view = this
    }

    fun createPresenter(): P {
        sequence {
            var thisClass: KClass<*> = this@BaseFragment::class
            while (true) {
                yield(thisClass.supertypes)
                thisClass == thisClass.supertypes.firstOrNull()?.jvmErasure ?: break
            }
        }.flatMap {
            it.flatMap { it.arguments }.asSequence()
        }.first {
            it.type?.jvmErasure?.isSubclassOf(IPresenter::class) ?: false
        }.let {
            return it.type!!.jvmErasure.primaryConstructor!!.call() as P
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle){
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }

    override fun onViewStateRestore(savedInstanceState: Bundle?){
        super.onViewStateRestored(savedInstanceState)
        presenter.onViewStateRestore(savedInstanceState)
    }

    override fun onConfigurationChanged(newConfig: Configuration){
        super.onConfigurationChanged(newConfig)
        presenter.onConfigurationChanged(newConfig)
    }

    override fun onDestory(){
        super.onDestroy()
        presenter.onDestory()
    }

    override fun onStart(){
        super.onStart()
        presenter.onStart()
    }

    override fun onStop(){
        super.onStop()
        presenter.onStop()
    }

    override fun onResume(){
        super.onResume()
        presenter.onResume()
    }

    override fun onPause(){
        super.onPause()
        presenter.onPause()
    }


}