package testapplication.imageviewer.domain.interactors.absctractions

import io.reactivex.Observable
import testapplication.imageviewer.domain.models.Image
import testapplication.imageviewer.domain.models.Monade

/**
 * Created by root on 14.08.17.
 */
interface IImagesInteractor {

    fun getNewestImage(): Observable<Image>
    fun isBecomeNewestImage(): Observable<Monade<Image>>
    fun subscribeOnUpdateImage(onUpdate: (Image) -> Unit)
    fun changeFavoriteFavorite(image: Image): Observable<Image>
    fun getFavoriteImages(): Observable<List<Image>>
}