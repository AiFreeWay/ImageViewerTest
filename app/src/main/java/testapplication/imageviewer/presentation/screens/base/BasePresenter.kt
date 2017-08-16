package testapplication.imageviewer.presentation.screens.base

import android.app.Activity
import android.arch.lifecycle.LifecycleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import testapplication.imageviewer.application.ImageViewerApp
import testapplication.imageviewer.application.di.components.RootComponent
import testapplication.imageviewer.application.utils.Logger

/**
 * Created by root on 11.08.17.
 */
abstract class BasePresenter<V : BaseView> : LifecycleObserver {

    protected val mDisposables: CompositeDisposable = CompositeDisposable()
    protected lateinit var mView: V

    open fun attachView(view: V) {
        mView = view
        mView.lifecycle.addObserver(this)
    }

    protected fun addDissposable(disposable: Disposable) {
        mDisposables.add(disposable)
    }

    protected open fun doOnError(error: Throwable) {
        Logger.logError(error)
    }

    protected fun getRootComponent(activity: Activity) : RootComponent {
        return (activity.application as ImageViewerApp).getRootComponent()
    }
}