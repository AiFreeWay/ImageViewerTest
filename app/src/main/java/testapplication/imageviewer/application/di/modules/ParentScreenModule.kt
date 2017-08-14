package testapplication.imageviewer.application.di.modules

import dagger.Module
import dagger.Provides
import testapplication.imageviewer.domain.interactors.ImagesInteractor
import testapplication.imageviewer.domain.interactors.absctractions.IImagesInteractor

/**
 * Created by root on 03.08.17.
 */
@Module
class ParentScreenModule {

    @Provides
    fun provideImagesInteractor(interactor: ImagesInteractor): IImagesInteractor = interactor
}