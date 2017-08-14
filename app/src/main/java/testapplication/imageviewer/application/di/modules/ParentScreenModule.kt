package testapplication.imageviewer.application.di.modules

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.domain.interactors.ImagesInteractor
import testapplication.imageviewer.domain.interactors.absctractions.IImagesInteractor
import testapplication.imageviewer.presentation.ui.base.ParentView
import testapplication.imageviewer.presentation.utils.FragmentNavigator

/**
 * Created by root on 03.08.17.
 */
@Module
class ParentScreenModule(views: ParentView) {

    private val mCicerone: Cicerone<Router>

    init {
        if (BuildConfig.DEBUG) Logger.testLog("created MODULE ParentScreenModule")

        mCicerone = Cicerone.create()
        mCicerone.navigatorHolder.setNavigator(FragmentNavigator(views.getActivity(),
                views.getSupportFragmentManager(),
                views.getFragmentLayoutId()))
    }

    @Provides
    fun provideFragmentRouter(): Router {
        return mCicerone.router
    }

    @Provides
    fun provideImagesInteractor(interactor: ImagesInteractor): IImagesInteractor = interactor
}