package testapplication.imageviewer.application

import android.app.Application
import io.realm.Realm
import testapplication.imageviewer.application.di.components.DaggerRootComponent
import testapplication.imageviewer.application.di.components.RootComponent
import testapplication.imageviewer.application.di.modules.RootModule
import io.realm.RealmConfiguration

/**
 * Created by root on 14.08.17.
 */
class ImageViewerApp : Application() {

    private lateinit var mRootComponent: RootComponent

    override fun onCreate() {
        super.onCreate()
        val realmConfiguration = RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
        mRootComponent = DaggerRootComponent.builder()
                .rootModule(RootModule(this))
                .build()
    }

    fun getRootComponent(): RootComponent = mRootComponent
}