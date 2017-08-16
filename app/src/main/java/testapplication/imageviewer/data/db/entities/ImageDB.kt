package testapplication.imageviewer.data.db.entities

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by root on 15.08.17.
 */
open class ImageDB : RealmObject() {

    @PrimaryKey
    private var uri: String = ""

    open fun getUri(): String = uri

    open fun setUri(uri: String) {
        this.uri = uri
    }
}