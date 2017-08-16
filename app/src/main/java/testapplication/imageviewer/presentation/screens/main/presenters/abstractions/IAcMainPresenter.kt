package testapplication.imageviewer.presentation.screens.main.presenters.abstractions

import testapplication.imageviewer.application.di.components.ParentScreenComponent
import testapplication.imageviewer.presentation.screens.main.views.abstractions.MainView

/**
 * Created by root on 14.08.17.
 */
interface IAcMainPresenter {

    fun attachView(view: MainView)
    fun navigateBack()
    fun getParentComponent(): ParentScreenComponent
}