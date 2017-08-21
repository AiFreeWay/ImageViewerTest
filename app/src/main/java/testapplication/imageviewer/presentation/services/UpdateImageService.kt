package testapplication.imageviewer.presentation.services

import android.app.IntentService
import android.app.Notification
import android.app.Service
import android.content.Intent
import io.reactivex.Observable

import testapplication.imageviewer.application.utils.Logger
import testapplication.imageviewer.domain.interactors.absctractions.IImagesInteractor
import testapplication.imageviewer.domain.interactors.absctractions.INotificationsInteractor
import testapplication.imageviewer.domain.models.Image
import testapplication.imageviewer.domain.models.Monade
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import android.support.v4.app.NotificationCompat
import testapplication.imageviewer.BuildConfig
import testapplication.imageviewer.R
import testapplication.imageviewer.application.di.components.DaggerRootComponent
import testapplication.imageviewer.application.di.components.DaggerServiceComponent
import testapplication.imageviewer.application.di.modules.RootModule
import testapplication.imageviewer.application.di.modules.ServiceModule


/**
 * Created by root on 18.08.17.
 */

class UpdateImageService : IntentService(NAME) {

    companion object {

        private val NAME = UpdateImageService::class.java.canonicalName
        private val LONG_DELAY_MINUTES = 5
    }

    @Inject
    lateinit var mImagesInteractor: IImagesInteractor
    @Inject
    lateinit var mNotificationsInteractor: INotificationsInteractor

    init {
        if (BuildConfig.DEBUG) Logger.logDebug("created SERVICE UpdateImageService")
    }

    override fun onCreate() {
        super.onCreate()
        val rootComponent = DaggerRootComponent.builder()
                .rootModule(RootModule(this))
                .build()

        DaggerServiceComponent.builder()
                .rootComponent(rootComponent)
                .serviceModule(ServiceModule())
                .build()
                .inject(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        stopForeground(false)
        startScanUpdate()
        return Service.START_STICKY
    }

    override fun onHandleIntent(intent: Intent?) {

    }

    private fun startScanUpdate() {
        Observable.interval(LONG_DELAY_MINUTES.toLong(), TimeUnit.MINUTES)
                .subscribe({ doOnEnterval() })

    }

    private fun doOnEnterval() {
        mImagesInteractor.isBecomeNewestImage()
                .subscribe(this::doOnGetScanReslut, { Logger.logError(it) })
    }

    private fun doOnGetScanReslut(scanResult: Monade<Image>) {
        if (scanResult.getResult())
            mNotificationsInteractor.notifySystemAsNewImageBecome(scanResult.getData())
    }

    private fun getNotification(): Notification =
         NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(getString(R.string.images_scan))
                .setAutoCancel(true)
                 .build()
}