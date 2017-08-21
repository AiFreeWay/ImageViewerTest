package testapplication.imageviewer.presentation.screens.main.presenters

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Bundle
import ru.terrakok.cicerone.Router
import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.R
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.domain.interactors.absctractions.IImagesInteractor
import testapplication.imageviewer.domain.models.Image
import testapplication.imageviewer.presentation.screens.base.BasePresenter
import testapplication.imageviewer.presentation.screens.main.presenters.abstractions.IFmtFavoriteImagesPresenter
import testapplication.imageviewer.presentation.screens.main.views.abstractions.IFavoriteImagesView
import testapplication.imageviewer.presentation.screens.main.views.fragments.ImageViewerFragment
import javax.inject.Inject

/**
 * Created by root on 16.08.17.
 */
class FmtFavoriteImagesPresenter : BasePresenter<IFavoriteImagesView>(), IFmtFavoriteImagesPresenter {

    @Inject
    lateinit var mImagesInteractor: IImagesInteractor
    @Inject
    lateinit var mRouter: Router

    init {
        if (BuildConfig.DEBUG) Logger.logDebug("created PRESENTER FmtFavoriteImagesPresenter")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart() {
        mView.getMainView().setToolbarTitle(R.string.favorites)
        addDissposable(mImagesInteractor.getFavoriteImages()
                .doOnSubscribe { mView.getMainView().startProgress() }
                .subscribe(this::doOnGetImages, this::doOnError))
        mView.getMainView().enableHomeToolbarButton()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {
        mDisposables.clear()
        mView.getMainView().disableHomeToolbarButton()
    }

    override fun attachView(view: IFavoriteImagesView) {
        super.attachView(view)
        if (BuildConfig.DEBUG) Logger.logDebug("attach PRESENTER FmtFavoriteImagesPresenter")
        inject()
    }

    override fun doOnError(error: Throwable) {
        mView.getMainView().stopProgress()
        Logger.logError(error)
    }

    override fun showSavedImageScreen(image: Image) {
        val bundle = Bundle()
        bundle.putSerializable(ImageViewerFragment.IMAGE_KEY, image)
        mRouter.navigateTo(ImageViewerFragment.FRAGMENT_KEY, bundle)
    }

    private fun inject() {
        mView.getMainView()
                .getParentComponent()
                .inject(this)
    }

    private fun doOnGetImages(images: List<Image>) {
        mView.getMainView().stopProgress()
        mView.loadImages(images)
    }
}