package testapplication.imageviewer.presentation.screens.main.presenters

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import ru.terrakok.cicerone.Router
import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.application.di.components.DaggerParentScreenComponent
import testapplication.imageviewer.application.di.components.ParentScreenComponent
import testapplication.imageviewer.application.di.modules.ParentScreenModule
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.presentation.screens.base.BasePresenter
import testapplication.imageviewer.presentation.screens.main.presenters.abstractions.IAcMainPresenter
import testapplication.imageviewer.presentation.screens.main.views.abstractions.MainView
import testapplication.imageviewer.presentation.screens.main.views.fragments.ImageViewerFragment
import javax.inject.Inject

/**
 * Created by root on 14.08.17.
 */
class AcMainPresenter : BasePresenter<MainView>(), IAcMainPresenter {

    @Inject
    lateinit var mRouter: Router
    private lateinit var mParentComponent: ParentScreenComponent

    init {
        if (BuildConfig.DEBUG) Logger.logDebug("created PRESENTER AcMainPresenter")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        mRouter.navigateTo(ImageViewerFragment.FRAGMENT_KEY)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {
        mDisposables.clear()
    }

    override fun attachView(view: MainView) {
        super.attachView(view)
        if (BuildConfig.DEBUG) Logger.logDebug("attach PRESENTER AcMainPresenter")
        inject()
    }

    override fun getParentComponent(): ParentScreenComponent = mParentComponent

    override fun navigateBack() {
        mRouter.exit()
    }

    private fun inject() {
        mParentComponent = DaggerParentScreenComponent.builder()
                .rootComponent(getRootComponent(mView.getActivity()))
                .parentScreenModule(ParentScreenModule(mView))
                .build()
        mParentComponent.inject(this)
    }
}