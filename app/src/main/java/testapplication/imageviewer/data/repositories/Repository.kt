package testapplication.imageviewer.data.repositories

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import okhttp3.ResponseBody
import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.data.cache.HawkController
import testapplication.imageviewer.data.db.DBController
import testapplication.imageviewer.data.db.entities.ImageDB
import testapplication.imageviewer.data.network.NetworkController
import testapplication.imageviewer.data.repositories.abstractions.IRepository
import testapplication.imageviewer.domain.models.Image
import javax.inject.Inject

/**
 * Created by root on 14.08.17.
 */
class Repository @Inject constructor(private val mNetwokController: NetworkController,
                                     private val mDBController: DBController) : IRepository {

    private val mImageUpdateSubject = PublishSubject.create<Image>()

    init {
        if (BuildConfig.DEBUG) Logger.logDebug("created REPOSITORY Repository")
    }

    override fun getNewestImage(): Observable<ResponseBody> = mNetwokController.getNewestImage()

    override fun changeFavoriteFavorite(image: Image): Observable<Image> = mDBController.changeFavoriteFavorite(image)

    override fun getFavoriteImages(): Observable<List<ImageDB>> = mDBController.getImages()

    override fun getLastLoadedImage(): Observable<Image> = HawkController.getLastLoadedImage()

    override fun putLastLoadedImage(image: Image): Observable<Image> = HawkController.putLastLoadedImage(image)

    override fun updateUiNewImageBecome(image: Image) {
        mImageUpdateSubject.onNext(image)
    }

    override fun subscribeOnUpdateImage(onUpdate: (Image) -> Unit) {
        mImageUpdateSubject.subscribe(onUpdate::invoke, {Logger.logError(it)})
    }

    override fun isCanUpdateUi(): Observable<Boolean> = HawkController.isCanUpdateUi()

    override fun accessUpdateUi() {
        HawkController.accessUpdateUi()
    }

    override fun deniedUpdateUi() {
        HawkController.deniedUpdateUi()
    }

    override fun isPushOn(): Observable<Boolean> = HawkController.isPushOn()

    override fun setPushState(state: Boolean): Observable<Boolean> = HawkController.setPushState(state)
}