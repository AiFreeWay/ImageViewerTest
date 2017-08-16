package testapplication.imageviewer.presentation.screens.main.presenters.abstractions

import testapplication.imageviewer.domain.models.Image
import testapplication.imageviewer.presentation.screens.main.views.abstractions.IFavoriteImagesView

/**
 * Created by root on 16.08.17.
 */
interface IFmtFavoriteImagesPresenter {

    fun attachView(view: IFavoriteImagesView)
    fun showSavedImageScreen(image: Image)
}