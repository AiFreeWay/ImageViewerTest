package testapplication.imageviewer.presentation.screens.main.views.abstractions

import testapplication.imageviewer.domain.models.Image
import testapplication.imageviewer.presentation.screens.base.ChildView

/**
 * Created by root on 16.08.17.
 */
interface IFavoriteImagesView : ChildView {

    fun loadImages(images: List<Image>)
}