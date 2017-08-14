package testapplication.imageviewer

import android.app.Application
import testapplication.imageviewer.application.di.components.DaggerRootComponent
import testapplication.imageviewer.application.di.components.RootComponent
import testapplication.imageviewer.application.di.modules.RootModule

/**
 * Created by root on 14.08.17.
 */
class ImageViewerApp : Application() {

    private lateinit var mRootComponent: RootComponent

    override fun onCreate() {
        super.onCreate()
        mRootComponent = DaggerRootComponent.builder()
                .rootModule(RootModule(this))
                .build()
    }

    fun getRootComponent(): RootComponent = mRootComponent
}