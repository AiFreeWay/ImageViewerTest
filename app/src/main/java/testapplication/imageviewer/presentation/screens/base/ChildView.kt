package testapplication.imageviewer.presentation.screens.base

import testapplication.imageviewer.presentation.screens.main.views.abstractions.MainView

/**
 * Created by root on 14.08.17.
 */
interface ChildView : BaseView {

    fun getMainView(): MainView
}