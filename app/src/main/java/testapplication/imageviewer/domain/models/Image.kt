package testapplication.imageviewer.domain.models

import java.io.Serializable


/**
 * Created by root on 14.08.17.
 */
data class Image(private var mUri: String, private var mFavorite: Boolean) : Serializable {

    fun getUri(): String = mUri

    fun setUri(uri: String) {
        mUri = uri
    }

    fun isFavorite(): Boolean = mFavorite

    fun setFavorite(favorite: Boolean) {
        mFavorite = favorite
    }
}