package testapplication.imageviewer.presentation.utils

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

import ru.terrakok.cicerone.android.SupportFragmentNavigator
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.presentation.factories.FragmentFactory

/**
 * Created by root on 14.08.17.
 */
class FragmentNavigator(private val mActvity: Activity, fragmentManager: FragmentManager, fragmentLayoutId: Int) : SupportFragmentNavigator(fragmentManager, fragmentLayoutId) {

    companion object {
        private var sLastScreenKey: String? = null
        private var sLastData: Bundle? = null

        fun getLastScreenKey(): String? = sLastScreenKey
        fun getLastData(): Bundle? = sLastData
    }

    override fun createFragment(screenKey: String?, data: Any?): Fragment {
        sLastScreenKey = screenKey
        if (data is Bundle) {
            sLastData = data
            return FragmentFactory.createFragment(screenKey, data)
        }
        return FragmentFactory.createFragment(screenKey)
    }

    override fun exit() = mActvity.finish()

    override fun showSystemMessage(message: String?) = Logger.logDebug(message)
}