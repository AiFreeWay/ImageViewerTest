package testapplication.imageviewer.domain.interactors

import android.text.TextUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.data.db.entities.ImageDB
import testapplication.imageviewer.data.repositories.abstractions.IRepository
import testapplication.imageviewer.domain.interactors.absctractions.IImagesInteractor
import testapplication.imageviewer.domain.mapper.ImageMapper
import testapplication.imageviewer.domain.models.Image
import testapplication.imageviewer.domain.models.Monade
import javax.inject.Inject

/**
 * Created by root on 14.08.17.
 */

class ImagesInteractor @Inject constructor(private val mRepository: IRepository) : IImagesInteractor {

    init {
        if (BuildConfig.DEBUG) Logger.logDebug("created INTERACTOR ImagesInteractor")
    }

    override fun getNewestImage(): Observable<Image> {
        return mRepository.getNewestImage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { ImageMapper.mapImageFromNetwork(it) }
                .flatMap { mRepository.putLastLoadedImage(it) }
                .zipWith(mRepository.getFavoriteImages(), BiFunction { t1, t2 -> checkImageAsFavorite(t1, t2) })

    }

    override fun changeFavoriteFavorite(image: Image): Observable<Image> {
        return mRepository.changeFavoriteFavorite(image)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getFavoriteImages(): Observable<List<Image>> {
        return mRepository.getFavoriteImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { ImageMapper.mapImagesFromDB(it) }
    }

    override fun isBecomeNewestImage(): Observable<Monade<Image>> {
        return mRepository.getNewestImage()
                .map { ImageMapper.mapImageFromNetwork(it) }
                .zipWith(mRepository.getLastLoadedImage(), BiFunction { t1, t2 -> checkImageNeedUpdate(t1, t2) })
    }

    override fun subscribeOnUpdateImage(onUpdate: (Image) -> Unit) {
        mRepository.subscribeOnUpdateImage(onUpdate)
    }

    private fun checkImageAsFavorite(image: Image, favorites: List<ImageDB>): Image {
        image.setFavorite(false)
        favorites.forEach {
            if (TextUtils.equals(it.getUri(), image.getUri())) {
                image.setFavorite(true)
            }
        }
        return image
    }

    private fun checkImageNeedUpdate(image: Image, lastLoaded: Image?): Monade<Image> {
        return Monade(!TextUtils.equals(image.getUri(), lastLoaded?.getUri()), image)
    }
}
