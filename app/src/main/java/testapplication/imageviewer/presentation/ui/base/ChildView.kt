package testapplication.imageviewer.presentation.ui.base

import testapplication.imageviewer.presentation.ui.main.views.abstractions.MainView

/**
 * Created by root on 14.08.17.
 */
interface ChildView : BaseView {

    fun getMainView(): MainView
}