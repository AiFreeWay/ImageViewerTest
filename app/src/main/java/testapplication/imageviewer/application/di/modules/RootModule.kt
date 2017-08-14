package testapplication.imageviewer.application.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import testapplication.imageviewer.data.network.NetworkController
import testapplication.imageviewer.data.repositories.Repository
import testapplication.imageviewer.data.repositories.abstractions.IRepository

/**
 * Created by root on 03.08.17.
 */
@Module
class RootModule(private val mContext: Context) {

    @Provides
    fun  provideContext(): Context {
        return mContext
    }
    @Provides
    fun  provideNetworkController(): NetworkController {
        return NetworkController()
    }

    @Provides
    fun provideRepository(repository : Repository): IRepository {
        return repository
    }
}