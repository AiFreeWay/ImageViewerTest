package testapplication.imageviewer.presentation.screens.main.views.abstractions

import testapplication.imageviewer.application.di.components.ParentScreenComponent
import testapplication.imageviewer.presentation.screens.base.ParentView

/**
 * Created by root on 14.08.17.
 */
interface MainView : ParentView {

    fun startProgress()
    fun stopProgress()
    fun enableHomeToolbarButton()
    fun disableHomeToolbarButton()
    fun setToolbarTitle(title: Int)
    fun getParentComponent(): ParentScreenComponent
}