package testapplication.imageviewer.presentation.screens.main.presenters

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import ru.terrakok.cicerone.Router
import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.R
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.domain.interactors.absctractions.IImagesInteractor
import testapplication.imageviewer.domain.models.Image
import testapplication.imageviewer.presentation.screens.base.BasePresenter
import testapplication.imageviewer.presentation.screens.main.presenters.abstractions.IFmtImageViewerPresenter
import testapplication.imageviewer.presentation.screens.main.views.abstractions.IImageViewerView
import testapplication.imageviewer.presentation.screens.main.views.fragments.FavoriteImagesFragment
import javax.inject.Inject

/**
 * Created by root on 14.08.17.
 */
class FmtImageViewerPresenter : BasePresenter<IImageViewerView>(), IFmtImageViewerPresenter {

    @Inject
    lateinit var mRouter: Router
    @Inject
    lateinit var mImagesInteractor: IImagesInteractor

    private var mImageFromArguments: Image? = null

    init {
        if (BuildConfig.DEBUG) Logger.logDebug("created PRESENTER FmtImageViewerPresenter")
    }

    override fun doOnError(error: Throwable) {
        stopProgress()
        Logger.logError(error)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        if (isBeImageFromArgumetns()) {
            initViewIsSaved()
            doOnGetNewestImage(mImageFromArguments!!)
        } else {
            initViewIsNewest()
            addDissposable(mImagesInteractor.getNewestImage()
                    .doOnSubscribe { startProgress() }
                    .subscribe(this::doOnGetNewestImage, this::doOnError))
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {
        mDisposables.clear()
    }

    override fun attachView(view: IImageViewerView) {
        super.attachView(view)
        if (BuildConfig.DEBUG) Logger.logDebug("attach PRESENTER FmtImageViewerPresenter")
        inject()
    }

    override fun changeStateFavorite(image: Image) {
        addDissposable(mImagesInteractor.changeFavoriteFavorite(image)
                .doOnSubscribe { startProgress() }
                .subscribe(this::doOnChangeStateFavorite, this::doOnError))
    }

    override fun openFavoriteListScreen() {
        mRouter.navigateTo(FavoriteImagesFragment.FRAGMENT_KEY)
    }

    override fun setImageFromArguments(image: Image?) {
        mImageFromArguments = image
    }

    override fun isBeImageFromArgumetns(): Boolean {
        return mImageFromArguments != null
    }

    private fun doOnChangeStateFavorite(image: Image) {
        stopProgress()
        mView.changeFavoriteState(image)
    }

    private fun doOnGetNewestImage(image: Image) {
        Logger.testLog("doOnGetNewestImage")
        stopProgress()
        mView.loadNewestImage(image)
    }

    private fun inject() {
        mView.getMainView()
                .getParentComponent()
                .inject(this)
    }

    private fun startProgress() {
        mView.getMainView().startProgress()
        mView.lockButton()
    }

    private fun stopProgress() {
        mView.getMainView().stopProgress()
        mView.unlockButton()
    }

    private fun initViewIsNewest() {
        mView.getMainView().disableHomeToolbarButton()
        mView.getMainView().setToolbarTitle(R.string.newest_image_title)
    }

    private fun initViewIsSaved() {
        mView.getMainView().enableHomeToolbarButton()
        mView.getMainView().setToolbarTitle(R.string.saved_image_title)
    }
}