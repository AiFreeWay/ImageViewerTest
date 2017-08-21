package testapplication.imageviewer.presentation.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import testapplication.imageviewer.presentation.services.UpdateImageService

/**
 * Created by root on 18.08.17.
 */

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.startService(Intent(context, UpdateImageService::class.java))
    }
}