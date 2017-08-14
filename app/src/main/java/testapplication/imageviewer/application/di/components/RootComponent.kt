package testapplication.imageviewer.application.di.components

import android.content.Context
import dagger.Component
import testapplication.imageviewer.application.di.modules.RootModule
import testapplication.imageviewer.data.network.NetworkController
import testapplication.imageviewer.data.repositories.abstractions.IRepository
import javax.inject.Singleton

/**
 * Created by root on 03.08.17.
 */
@Singleton
@Component(modules = arrayOf(RootModule::class))
interface RootComponent {

    fun provideContext() : Context
    fun provideRepository() : IRepository
    fun provideNetworkController() : NetworkController
}