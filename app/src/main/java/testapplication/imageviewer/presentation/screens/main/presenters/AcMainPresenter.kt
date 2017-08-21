package testapplication.imageviewer.presentation.screens.main.presenters

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.text.TextUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.application.di.components.DaggerParentScreenComponent
import testapplication.imageviewer.application.di.components.ParentScreenComponent
import testapplication.imageviewer.application.di.modules.ParentScreenModule
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.domain.interactors.absctractions.INotificationsInteractor
import testapplication.imageviewer.domain.interactors.absctractions.ISettingsInteractor
import testapplication.imageviewer.presentation.screens.base.BasePresenter
import testapplication.imageviewer.presentation.screens.main.presenters.abstractions.IAcMainPresenter
import testapplication.imageviewer.presentation.screens.main.views.abstractions.MainView
import testapplication.imageviewer.presentation.screens.main.views.fragments.ImageViewerFragment
import testapplication.imageviewer.presentation.utils.FragmentNavigator
import javax.inject.Inject

/**
 * Created by root on 14.08.17.
 */
class AcMainPresenter : BasePresenter<MainView>(), IAcMainPresenter {

    @Inject
    lateinit var mRouter: Router
    @Inject
    lateinit var mNotificationsInteractor: INotificationsInteractor
    @Inject
    lateinit var mSettingsInteractor: ISettingsInteractor

    private lateinit var mParentComponent: ParentScreenComponent

    init {
        if (BuildConfig.DEBUG) Logger.logDebug("created PRESENTER AcMainPresenter")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        getPushOnState()
        mNotificationsInteractor.accessUpdateUi()
        val lastScreenKey = FragmentNavigator.getLastScreenKey()
        if (TextUtils.isEmpty(lastScreenKey))
            mRouter.navigateTo(ImageViewerFragment.FRAGMENT_KEY)
        else
            mRouter.replaceScreen(lastScreenKey, FragmentNavigator.getLastData())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {
        mNotificationsInteractor.deniedUpdateUi()
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

    override fun changePushOnState(state: Boolean) {
        addDissposable(mSettingsInteractor.setPushState(state)
                .subscribe(this::doOnGetPushOnState, { Logger.logError(it) }))
    }

    private fun getPushOnState() {
        addDissposable(mSettingsInteractor.isPushOn()
                .subscribe(this::doOnGetPushOnState, { Logger.logError(it) }))
    }

    private fun doOnGetPushOnState(state: Boolean) {
        mView.setPushOnState(state)
    }

    private fun inject() {
        mParentComponent = DaggerParentScreenComponent.builder()
                .rootComponent(getRootComponent(mView.getActivity()))
                .parentScreenModule(ParentScreenModule(mView))
                .build()
        mParentComponent.inject(this)
    }
}