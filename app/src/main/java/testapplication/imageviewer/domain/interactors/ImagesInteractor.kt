package testapplication.imageviewer.domain.interactors

import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.application.di.scopes.PerParentScreen
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.data.repositories.Repository
import testapplication.imageviewer.domain.interactors.absctractions.IImagesInteractor
import javax.inject.Inject

/**
 * Created by root on 14.08.17.
 */
@PerParentScreen
class ImagesInteractor @Inject constructor(val mRepository: Repository) : IImagesInteractor {

    init {
        if (BuildConfig.DEBUG) Logger.testLog("create INTERACTOR ImagesInteractor")
    }
}