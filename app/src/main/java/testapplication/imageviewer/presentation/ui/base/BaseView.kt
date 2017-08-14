package testapplication.imageviewer.presentation.ui.base

import android.app.Activity
import android.arch.lifecycle.LifecycleRegistryOwner

/**
 * Created by root on 11.08.17.
 */
interface BaseView : LifecycleRegistryOwner {

    fun getActivity(): Activity
}