package testapplication.imageviewer.presentation.ui.main.presenters

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import ru.terrakok.cicerone.Router
import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.application.di.components.DaggerParentScreenComponent
import testapplication.imageviewer.application.di.modules.ParentScreenModule
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.presentation.ui.base.BasePresenter
import testapplication.imageviewer.presentation.ui.main.presenters.abstractions.IFmtImageViewerPresenter
import testapplication.imageviewer.presentation.ui.main.views.abstractions.IImageViewerView
import javax.inject.Inject

/**
 * Created by root on 14.08.17.
 */
class FmtImageViewerPresenter(view: IImageViewerView) : BasePresenter<IImageViewerView>(view), IFmtImageViewerPresenter {

    @Inject
    lateinit var mRouter: Router

    init {
        if (BuildConfig.DEBUG) Logger.testLog("created PRESENTER FmtImageViewerPresenter")
        inject()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {
        mDisposables.clear()
    }

    private fun inject() {
        DaggerParentScreenComponent.builder()
                .rootComponent(getRootComponent(mView.getActivity()))
                .parentScreenModule(ParentScreenModule(mView.getMainView()))
                .build()
                .inject(this)
    }
}