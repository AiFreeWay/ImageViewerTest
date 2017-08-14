package testapplication.imageviewer.presentation.ui.base

import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

/**
 * Created by root on 14.08.17.
 */
interface ParentView : BaseView {

    fun getSupportFragmentManager(): FragmentManager
    fun getFragmentLayoutId(): Int
}