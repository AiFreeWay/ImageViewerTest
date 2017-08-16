package testapplication.imageviewer.data.repositories.abstractions

import io.reactivex.Observable
import okhttp3.ResponseBody
import testapplication.imageviewer.data.db.entities.ImageDB
import testapplication.imageviewer.domain.models.Image

/**
 * Created by root on 14.08.17.
 */
interface IRepository {

    fun getNewestImage(): Observable<ResponseBody>
    fun changeFavoriteFavorite(image: Image): Observable<Image>
    fun getFavoriteImages(): Observable<List<ImageDB>>
}