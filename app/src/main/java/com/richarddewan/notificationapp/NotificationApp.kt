package com.richarddewan.notificationapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_ACTION_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_BIG_PICTURE_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_BIG_TEXT_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_CONTENT_INTENT_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_CUSTOM_SOUND_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_DEFAULT_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_DOWNLOADING_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_HIGH_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_INBOX_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_LOW_CHANNEL_ID


/*
created by Richard Dewan 25/07/2021
*/

class NotificationApp : Application() {

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val audioAttributes = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                .build()

            val defaultNotification = NotificationChannel(
                NOTIFICATION_DEFAULT_CHANNEL_ID,
                "Default Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "This is a default notification channel"
                lightColor = Color.GREEN
            }

            val highNotification = NotificationChannel(
                NOTIFICATION_HIGH_CHANNEL_ID,
                "High Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is a high notification channel"
                lightColor = Color.GREEN
            }

            val lowNotification = NotificationChannel(
                NOTIFICATION_LOW_CHANNEL_ID,
                "Low Notification Channel",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "This is a low notification channel"
                lightColor = Color.GREEN
            }

            val actionNotification = NotificationChannel(
                NOTIFICATION_ACTION_CHANNEL_ID,
                "Action Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is a action notification channel"
                lightColor = Color.GREEN
            }

            val contentIntentNotification = NotificationChannel(
                NOTIFICATION_CONTENT_INTENT_CHANNEL_ID,
                "Content Intent Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is a content intent notification channel"
                lightColor = Color.RED
            }

            val customSoundNotification = NotificationChannel(
                NOTIFICATION_CUSTOM_SOUND_CHANNEL_ID,
                "Custom Sound Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is a custom sound notification channel"
                lightColor = Color.RED
                vibrationPattern = longArrayOf(100,100,100,100)
                setSound(
                    getUriFromResourceFile(
                        applicationContext,
                        R.raw.custom_notification_cound
                    ), audioAttributes
                )
            }

            val bigTextStyleNotification = NotificationChannel(
                NOTIFICATION_BIG_TEXT_CHANNEL_ID,
                "Big Text Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is a big text style notification channel"
                lightColor = Color.RED
                vibrationPattern = longArrayOf(100,100,100,100)
                setSound(
                    getUriFromResourceFile(
                        applicationContext,
                        R.raw.custom_notification_cound
                    ), audioAttributes
                )
            }

            val inboxStyleNotification = NotificationChannel(
                NOTIFICATION_INBOX_CHANNEL_ID,
                "Inbox Style Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is a inbox style notification channel"
                lightColor = Color.RED
                vibrationPattern = longArrayOf(100,100,100,100)
                setSound(
                    getUriFromResourceFile(
                        applicationContext,
                        R.raw.custom_notification_cound
                    ), audioAttributes
                )
            }

            val bigPictureStyleNotification = NotificationChannel(
                NOTIFICATION_BIG_PICTURE_CHANNEL_ID,
                "Big Picture Style Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is a big picture style notification channel"

            }

            val downloadingStyleNotification = NotificationChannel(
                NOTIFICATION_DOWNLOADING_CHANNEL_ID,
                "Downloading Style Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is a downloading  style notification channel"

            }



            val notificationManager = getSystemService(NotificationManager::class.java)

            notificationManager.createNotificationChannels(
                listOf(
                    defaultNotification,
                    highNotification,
                    lowNotification,
                    actionNotification,
                    contentIntentNotification,
                    customSoundNotification,
                    bigTextStyleNotification,
                    inboxStyleNotification,
                    bigPictureStyleNotification,
                    downloadingStyleNotification

                    )
            )

        }

    }

    private fun getUriFromResourceFile(context: Context, resourceId: Int): Uri {
        return Uri.parse("android.resource://$packageName/$resourceId")
    }

}