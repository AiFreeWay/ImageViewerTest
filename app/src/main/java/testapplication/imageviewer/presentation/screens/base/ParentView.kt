package testapplication.imageviewer.presentation.screens.base

import android.support.v4.app.FragmentManager

/**
 * Created by root on 14.08.17.
 */
interface ParentView : BaseView {

    fun getSupportFragmentManager(): FragmentManager
    fun getFragmentLayoutId(): Int
}