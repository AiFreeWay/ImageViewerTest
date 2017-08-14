package testapplication.imageviewer.data.repositories.abstractions

import io.reactivex.Observable
import testapplication.imageviewer.domain.models.Image

/**
 * Created by root on 14.08.17.
 */
interface IRepository {

    fun getNewestImage(): Observable<Image>
    fun addToFavorite(image: Image): Observable<Unit>
    fun removeFromFavorite(image: Image): Observable<Unit>
    fun getFavoriteImages(): Observable<List<Image>>
}