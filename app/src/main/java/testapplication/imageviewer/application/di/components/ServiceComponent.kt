package testapplication.imageviewer.application.di.components

import dagger.Component
import testapplication.imageviewer.application.di.modules.ServiceModule
import testapplication.imageviewer.application.di.scopes.PerService
import testapplication.imageviewer.application.utils.PushNotificationController
import testapplication.imageviewer.data.repositories.abstractions.IRepository
import testapplication.imageviewer.presentation.services.UpdateImageService

/**
 * Created by root on 18.08.17.
 */
@PerService
@Component(modules = arrayOf(ServiceModule::class), dependencies = arrayOf(RootComponent::class))
interface ServiceComponent {

    fun provideRepository() : IRepository
    fun providePushNotificationController() : PushNotificationController

    fun inject(service: UpdateImageService)
}