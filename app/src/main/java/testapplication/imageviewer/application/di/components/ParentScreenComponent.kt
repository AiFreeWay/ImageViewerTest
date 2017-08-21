package testapplication.imageviewer.application.di.components

import dagger.Component
import testapplication.imageviewer.application.di.modules.ParentScreenModule
import testapplication.imageviewer.application.di.scopes.PerParentScreen
import testapplication.imageviewer.application.utils.PushNotificationController
import testapplication.imageviewer.data.repositories.abstractions.IRepository
import testapplication.imageviewer.presentation.screens.main.presenters.AcMainPresenter
import testapplication.imageviewer.presentation.screens.main.presenters.FmtFavoriteImagesPresenter
import testapplication.imageviewer.presentation.screens.main.presenters.FmtImageViewerPresenter

/**
 * Created by root on 03.08.17.
 */
@PerParentScreen
@Component(modules = arrayOf(ParentScreenModule::class), dependencies = arrayOf(RootComponent::class))
interface ParentScreenComponent {

    fun provideRepository() : IRepository
    fun providePushNotificationController() : PushNotificationController

    fun inject(presenter: AcMainPresenter)
    fun inject(presenter: FmtImageViewerPresenter)
    fun inject(presenter: FmtFavoriteImagesPresenter)
}