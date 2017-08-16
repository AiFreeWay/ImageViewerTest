package testapplication.imageviewer.presentation.screens.main.presenters.abstractions

import testapplication.imageviewer.domain.models.Image
import testapplication.imageviewer.presentation.screens.main.views.abstractions.IImageViewerView

/**
 * Created by root on 14.08.17.
 */
interface IFmtImageViewerPresenter {

    fun attachView(view: IImageViewerView)
    fun changeStateFavorite(image: Image)
    fun openFavoriteListScreen()
    fun setImageFromArguments(image: Image?)
    fun isBeImageFromArgumetns(): Boolean
}