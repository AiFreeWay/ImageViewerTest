package testapplication.imageviewer.presentation.ui.main.presenters

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import ru.terrakok.cicerone.Router
import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.application.di.components.DaggerParentScreenComponent
import testapplication.imageviewer.application.di.modules.ParentScreenModule
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.presentation.ui.base.BasePresenter
import testapplication.imageviewer.presentation.ui.main.presenters.abstractions.IAcMainPresenter
import testapplication.imageviewer.presentation.ui.main.views.abstractions.MainView
import testapplication.imageviewer.presentation.ui.main.views.fragments.ImageViewerFragment
import javax.inject.Inject

/**
 * Created by root on 14.08.17.
 */
class AcMainPresenter(view: MainView) : BasePresenter<MainView>(view), IAcMainPresenter {

    @Inject
    lateinit var mRouter: Router

    init {
        if (BuildConfig.DEBUG) Logger.testLog("created PRESENTER AcMainPresenter")
        inject()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        mRouter.replaceScreen(ImageViewerFragment.FRAGMENT_KEY)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {
        mDisposables.clear()
    }

    private fun inject() {
        DaggerParentScreenComponent.builder()
                .rootComponent(getRootComponent(mView.getActivity()))
                .parentScreenModule(ParentScreenModule(mView))
                .build()
                .inject(this)
    }
}