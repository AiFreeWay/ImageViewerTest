package testapplication.imageviewer.presentation.ui.base

import android.app.Activity
import android.arch.lifecycle.LifecycleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import testapplication.imageviewer.ImageViewerApp
import testapplication.imageviewer.application.di.components.RootComponent
import testapplication.imageviewer.application.utils.Logger

/**
 * Created by root on 11.08.17.
 */
abstract class BasePresenter<V : BaseView>(protected val mView: V) : LifecycleObserver {

    protected val mDisposables: CompositeDisposable = CompositeDisposable()

    init {
        mView.lifecycle.addObserver(this)
    }

    protected fun addDissposable(disposable: Disposable) {
        mDisposables.add(disposable)
    }

    protected fun doOnError(error: Throwable) {
        Logger.logError(error)
    }

    protected fun getRootComponent(activity: Activity) : RootComponent {
        return (activity.application as ImageViewerApp).getRootComponent()
    }
}