package com.richarddewan.notificationapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import com.richarddewan.notificationapp.util.AppConstant

class CustomNotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context,"CustomNotificationReceiver: Its done!",Toast.LENGTH_LONG).show()
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.cancel(AppConstant.NOTIFICATION_CUSTOM_ID)
    }
}