package testapplication.imageviewer.presentation.factories

import android.os.Bundle
import android.support.v4.app.Fragment
import testapplication.imageviewer.presentation.screens.main.views.fragments.FavoriteImagesFragment
import testapplication.imageviewer.presentation.screens.main.views.fragments.ImageViewerFragment

/**
 * Created by root on 14.08.17.
 */
class FragmentFactory {

    companion object {

        fun createFragment(screenKey: String?): Fragment =
                createFragment(screenKey, null)

        fun createFragment(screenKey: String?, bundle: Bundle?): Fragment =
                when (screenKey) {
                    ImageViewerFragment.FRAGMENT_KEY -> addBundle(ImageViewerFragment(), bundle)
                    FavoriteImagesFragment.FRAGMENT_KEY -> addBundle(FavoriteImagesFragment(), bundle)
                    else -> throw Exception("Invalid fragment key $screenKey FragmentFactory.createFragment(screenKey: String?)")
                }

        fun addBundle(fragment: Fragment, bundle: Bundle?): Fragment {
            fragment.arguments = bundle
            return fragment
        }
    }
}