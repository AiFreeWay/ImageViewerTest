package testapplication.imageviewer.presentation.ui.base

import android.support.v4.app.Fragment
import testapplication.imageviewer.presentation.ui.main.views.abstractions.MainView

/**
 * Created by root on 14.08.17.
 */
abstract class FragmentChildMainView : Fragment() {

    fun getMainView(): MainView = activity as MainView
}