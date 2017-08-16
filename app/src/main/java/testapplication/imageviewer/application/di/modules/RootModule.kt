package testapplication.imageviewer.application.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import io.realm.Realm
import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.data.db.DBController
import testapplication.imageviewer.data.network.NetworkController
import testapplication.imageviewer.data.repositories.Repository
import testapplication.imageviewer.data.repositories.abstractions.IRepository
import javax.inject.Singleton

/**
 * Created by root on 03.08.17.
 */
@Module
class RootModule(private val mContext: Context) {

    init {
        if (BuildConfig.DEBUG) Logger.logDebug("created MODULE RootModule")
    }

    @Provides
    @Singleton
    fun  provideContext(): Context {
        return mContext
    }

    @Provides
    @Singleton
    fun provideDBController(): DBController {
        return DBController(Realm.getInstance(mContext))
    }

    @Provides
    @Singleton
    fun provideNetworkController(): NetworkController {
        return NetworkController()
    }

    @Provides
    @Singleton
    fun provideRepository(repository : Repository): IRepository {
        return repository
    }
}