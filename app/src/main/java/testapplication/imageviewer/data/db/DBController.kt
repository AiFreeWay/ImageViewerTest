package testapplication.imageviewer.data.db

import io.reactivex.Observable
import io.realm.Realm
import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.data.db.entities.ImageDB
import testapplication.imageviewer.domain.models.Image
import java.util.*


/**
 * Created by root on 14.08.17.
 */
class DBController(private val mRealm: Realm?) {

    companion object {
        private val UNIVERSAL_ID_KEY: String = "id"
        private val URI_KEY: String = "uri"
    }

    constructor(): this(null)

    init {
        if (BuildConfig.DEBUG) Logger.logDebug("created CONTROLLER DBController")
    }

    fun changeFavoriteFavorite(image: Image): Observable<Image> {
        val returnObservable =
                if (image.isFavorite())
                    Observable.just(removeFromFavorite(image))
                else
                    Observable.just(addToFavorite(image))

        return returnObservable.map {
            image.setFavorite(!image.isFavorite())
            return@map image }
    }

    fun getImages(): Observable<List<ImageDB>> {
        if (mRealm == null)
            return Observable.just(Collections.emptyList())
        return Observable.just(mRealm.where(ImageDB::class.java)
                .findAll())
    }

    private fun addToFavorite(image: Image) {
        if (mRealm == null)
            return

        mRealm.beginTransaction()
        val imageDB = mRealm.createObject(ImageDB::class.java)
        imageDB.setUri(image.getUri())
        mRealm.commitTransaction()
    }

    private fun removeFromFavorite(image: Image) {
        if (mRealm == null)
            return

        mRealm.beginTransaction()
        mRealm.where(ImageDB::class.java)
                .equalTo(URI_KEY, image.getUri())
                .findAll()
                .clear()
        mRealm.commitTransaction()
    }
}