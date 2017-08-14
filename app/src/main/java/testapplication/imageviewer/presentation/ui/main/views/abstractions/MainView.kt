package testapplication.imageviewer.presentation.ui.main.views.abstractions

import testapplication.imageviewer.presentation.ui.base.ParentView

/**
 * Created by root on 14.08.17.
 */
interface MainView : ParentView {

    fun startProgress()
    fun stopProgress()
    fun setToolbarTitle(title: String)
}