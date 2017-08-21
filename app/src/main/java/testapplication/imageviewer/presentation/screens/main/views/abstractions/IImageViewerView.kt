package testapplication.imageviewer.presentation.screens.main.views.abstractions

import testapplication.imageviewer.domain.models.Image
import testapplication.imageviewer.presentation.screens.base.ChildView

/**
 * Created by root on 14.08.17.
 */
interface IImageViewerView : ChildView {

    fun loadNewestImage(image: Image)
    fun changeFavoriteState(image: Image)
    fun lockButton()
    fun unlockButton()
    fun showFavoriteListButton()
    fun hideFavoriteListButton()
}