package testapplication.imageviewer.application.utils

import android.app.Notification
import android.content.Context
import testapplication.imageviewer.BuildConfig
import android.support.v4.app.NotificationCompat
import testapplication.imageviewer.R
import android.app.NotificationManager
import android.content.Intent
import android.app.PendingIntent
import testapplication.imageviewer.presentation.screens.main.views.activities.MainActivity


/**
 * Created by root on 21.08.17.
 */
class PushNotificationController(private val mContext: Context) {

    companion object {
        val NOTIFICATION_SCAN_ID = 1
        val NOTIFICATION_NEW_ID = 2
    }

    private val mNotificationManager: NotificationManager

    init {
        if (BuildConfig.DEBUG) Logger.logDebug("created CONTROLLER PushNotificationController")
        mNotificationManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun pushImageUpdate() {
        val openApplicationIntent = PendingIntent.getActivity(mContext, 0, Intent(mContext, MainActivity::class.java), 0)
        val notification = getNotificationTemplate()
                .setContentText(mContext.getString(R.string.new_image_text))
                .setContentTitle(mContext.getString(R.string.new_image_title))
                .setContentIntent(openApplicationIntent)
                .build()
        push(notification)
    }

    private fun push(notification: Notification) {
        mNotificationManager.notify(NOTIFICATION_NEW_ID, notification)
    }

    private fun getNotificationTemplate(): NotificationCompat.Builder =
        NotificationCompat.Builder(mContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
}