package testapplication.imageviewer.domain.interactors

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.application.utils.PushNotificationController
import testapplication.imageviewer.data.repositories.abstractions.IRepository
import testapplication.imageviewer.domain.interactors.absctractions.INotificationsInteractor
import testapplication.imageviewer.domain.models.Image
import javax.inject.Inject

class NotificationsInteractor @Inject constructor(private val mRepository: IRepository,
                                                  private val mPushNotificationController: PushNotificationController) : INotificationsInteractor {

    init {
        if (BuildConfig.DEBUG) Logger.logDebug("created INTERACTOR NotificationsInteractor")
    }

    override fun notifySystemAsNewImageBecome(image: Image) {
        mRepository.isCanUpdateUi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ notifySystem(it, image) }, { Logger.logError(it) })
    }

    override fun accessUpdateUi() {
        mRepository.accessUpdateUi()
    }

    override fun deniedUpdateUi() {
        mRepository.deniedUpdateUi()
    }

    private fun notifySystem(isCanNotifyUi: Boolean, image: Image) {
        if (isCanNotifyUi)
            mRepository.updateUiNewImageBecome(image)
        else
            mRepository.isPushOn()
                    .subscribe({ if (it) mPushNotificationController.pushImageUpdate() },
                            { Logger.logError(it) })
    }
}
