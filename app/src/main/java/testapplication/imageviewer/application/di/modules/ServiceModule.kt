package testapplication.imageviewer.application.di.modules

import dagger.Module
import dagger.Provides
import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.application.di.scopes.PerService
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.domain.interactors.ImagesInteractor
import testapplication.imageviewer.domain.interactors.NotificationsInteractor
import testapplication.imageviewer.domain.interactors.absctractions.IImagesInteractor
import testapplication.imageviewer.domain.interactors.absctractions.INotificationsInteractor

/**
 * Created by root on 18.08.17.
 */
@Module
class ServiceModule() {

    init {
        if (BuildConfig.DEBUG) Logger.logDebug("created MODULE ServiceModule")
    }

    @Provides
    @PerService
    fun provideImagesInteractor(interactor: ImagesInteractor): IImagesInteractor = interactor

    @Provides
    @PerService
    fun provideNotificationInteractor(interactor: NotificationsInteractor): INotificationsInteractor = interactor
}