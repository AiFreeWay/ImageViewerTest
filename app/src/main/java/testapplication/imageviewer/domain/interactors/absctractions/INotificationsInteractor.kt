package testapplication.imageviewer.domain.interactors.absctractions

import testapplication.imageviewer.domain.models.Image

/**
 * Created by root on 18.08.17.
 */
interface INotificationsInteractor {

    fun notifySystemAsNewImageBecome(image: Image)
    fun accessUpdateUi()
    fun deniedUpdateUi()
}