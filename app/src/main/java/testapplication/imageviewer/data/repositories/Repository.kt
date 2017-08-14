package testapplication.imageviewer.data.repositories

import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.data.network.NetworkController
import testapplication.imageviewer.data.repositories.abstractions.IRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by root on 14.08.17.
 */
@Singleton
class Repository @Inject constructor(private val mNetwokController: NetworkController) : IRepository {

    init {
        if (BuildConfig.DEBUG) Logger.testLog("create Repository")
    }
}