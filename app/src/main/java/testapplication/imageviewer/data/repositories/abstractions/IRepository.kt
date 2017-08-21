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
    fun getLastLoadedImage(): Observable<Image>
    fun putLastLoadedImage(image: Image): Observable<Image>
    fun subscribeOnUpdateImage(onUpdate: (Image) -> Unit)
    fun updateUiNewImageBecome(image: Image)
    fun isPushOn(): Observable<Boolean>
    fun accessUpdateUi()
    fun deniedUpdateUi()
    fun isCanUpdateUi(): Observable<Boolean>
    fun setPushState(state: Boolean): Observable<Boolean>
}