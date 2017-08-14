package testapplication.imageviewer.domain.interactors.absctractions

import io.reactivex.Observable
import testapplication.imageviewer.domain.models.Image

/**
 * Created by root on 14.08.17.
 */
interface IImagesInteractor {

    fun getNewestImage(): Observable<Image>
    fun addToFavorite(image: Image): Observable<Unit>
    fun removeFromFavorite(image: Image): Observable<Unit>
    fun getFavoriteImages(): Observable<List<Image>>
}