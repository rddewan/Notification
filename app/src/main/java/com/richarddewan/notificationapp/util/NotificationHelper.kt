package com.richarddewan.notificationapp.util


import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.MediaMetadata
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import androidx.core.graphics.drawable.IconCompat
import com.richarddewan.notificationapp.MainActivity
import com.richarddewan.notificationapp.R
import com.richarddewan.notificationapp.receiver.AddToCartReceiver
import com.richarddewan.notificationapp.receiver.CustomNotificationReceiver
import com.richarddewan.notificationapp.receiver.MessageReceiver
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_ACTION_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_ACTION_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_BIG_PICTURE_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_BIG_PICTURE_TEXT_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_BIG_TEXT_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_BIG_TEXT_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_CONTENT_INTENT_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_CONTENT_INTENT_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_CUSTOM_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_CUSTOM_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_CUSTOM_SOUND_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_CUSTOM_SOUND_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_DEFAULT_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_DEFAULT_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_DOWNLOADING_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_DOWNLOADING_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_HIGH_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_HIGH_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_INBOX_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_INBOX_TEXT_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_LOW_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_LOW_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_MEDIA_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_MEDIA_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_MESSAGE_CHANNEL_ID
import com.richarddewan.notificationapp.util.AppConstant.NOTIFICATION_MESSAGE_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


object NotificationHelper {

    val messageList = mutableListOf<AppMessage>(
        AppMessage("hi",125548, Person.Builder().setName("David").build()),
        AppMessage("hello",125548, null),
        AppMessage("how are you",125548, Person.Builder().setName("David").build()),
        AppMessage("i am doing good",125548, null),
        AppMessage("how about you?",125548, Person.Builder().setName("David").build()),
        AppMessage("i am too good",125548, null),
        AppMessage("are your free?",125548, Person.Builder().setName("David").build()),
        AppMessage("yes anything?",125548, null),
    )

    fun defaultNotification(context: Context, title: String, msg: String) {
        val notificationManager = NotificationManagerCompat.from(context)

        val defaultNotification =
            NotificationCompat.Builder(context, NOTIFICATION_DEFAULT_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT) // required for api level < 26
                .build()

        notificationManager.notify(NOTIFICATION_DEFAULT_ID, defaultNotification)
    }

    fun highNotification(context: Context, title: String, msg: String) {
        val notificationManager = NotificationManagerCompat.from(context)

        val highNotification =
            NotificationCompat.Builder(context, NOTIFICATION_HIGH_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_HIGH) // required for api level < 26
                .build()

        notificationManager.notify(NOTIFICATION_HIGH_ID, highNotification)
    }

    fun lowNotification(context: Context, title: String, msg: String) {
        val notificationManager = NotificationManagerCompat.from(context)

        val lowNotification =
            NotificationCompat.Builder(context, NOTIFICATION_LOW_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_LOW) // required for api level < 26
                .setAutoCancel(true)
                .build()

        notificationManager.notify(NOTIFICATION_LOW_ID, lowNotification)
    }

    fun actionNotification(context: Context, title: String, msg: String) {
        val notificationManager = NotificationManagerCompat.from(context)

        val intent = Intent(context, AddToCartReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        val actionNotification =
            NotificationCompat.Builder(context, NOTIFICATION_ACTION_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_HIGH) // required for api level < 26
                .setCategory(NotificationCompat.CATEGORY_PROMO)
                .addAction(R.drawable.ic_action_add_to_cart, "Add To Cart", pendingIntent)
                .setAutoCancel(true)
                .setColor(Color.GREEN)
                .build()

        notificationManager.notify(NOTIFICATION_ACTION_ID, actionNotification)
    }

    fun contentIntentNotification(context: Context, title: String, msg: String) {
        val notificationManager = NotificationManagerCompat.from(context)

        val intent = Intent(context, AddToCartReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        val contentIntent = Intent(context, MainActivity::class.java).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
            action = Intent.ACTION_MAIN
        }
        val contentPendingIntent = PendingIntent.getActivity(
            context, 0, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification =
            NotificationCompat.Builder(context, NOTIFICATION_CONTENT_INTENT_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_HIGH) // required for api level < 26
                .setCategory(NotificationCompat.CATEGORY_PROMO)
                .setContentIntent(contentPendingIntent)
                .addAction(R.drawable.ic_action_add_to_cart, "Add To Cart", pendingIntent)
                .setOngoing(true)
                .setAutoCancel(false)
                .setColor(Color.GREEN)
                .build()

        notificationManager.notify(NOTIFICATION_CONTENT_INTENT_ID, notification)
    }

    fun customSoundNotification(context: Context, title: String, msg: String) {
        val notificationManager = NotificationManagerCompat.from(context)

        val intent = Intent(context, AddToCartReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        val contentIntent = Intent(context, MainActivity::class.java).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
            action = Intent.ACTION_MAIN
        }
        val contentPendingIntent = PendingIntent.getActivity(
            context, 0, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification =
            NotificationCompat.Builder(context, NOTIFICATION_CUSTOM_SOUND_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_HIGH) // required for api level < 26
                .setCategory(NotificationCompat.CATEGORY_PROMO)
                .setContentIntent(contentPendingIntent)
                .addAction(R.drawable.ic_action_add_to_cart, "Add To Cart", pendingIntent)
                .setVibrate(longArrayOf(100, 100, 100, 100))
                .setSound(getUriFromResourceFile(context, R.raw.custom_notification_cound))
                .setOngoing(true)
                .setAutoCancel(false)
                .setColor(Color.GREEN)
                .build()

        notificationManager.notify(NOTIFICATION_CUSTOM_SOUND_ID, notification)
    }

    fun bigTextStyleNotification(context: Context, title: String, msg: String) {
        val notificationManager = NotificationManagerCompat.from(context)

        val notification =
            NotificationCompat.Builder(context, NOTIFICATION_BIG_TEXT_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .setBigContentTitle("Big Text Style Notification")
                        .setSummaryText("Big Text Summery")
                        .bigText(
                            "A notification is a message that Android displays outside your app's UI to provide the user with reminders, communication from other people, or other timely information from your app. Users can tap the notification to open your app or take an action directly from the notification." +
                                    "This page provides an overview of where notifications appear and the available features. If you want to start building your notifications, instead read Create a Notification." +
                                    "For more information about the design and interaction patterns, see the Notifications design guide. Additionally, see the Android Notifications Sample for a demonstration of best practices in using the Notification.Style API in both mobile and wearable apps."
                        )
                )
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_HIGH) // required for api level < 26
                .setCategory(NotificationCompat.CATEGORY_PROMO)
                .setVibrate(longArrayOf(100, 100, 100, 100))
                .setSound(getUriFromResourceFile(context, R.raw.custom_notification_cound))
                .setOngoing(false)
                .setAutoCancel(true)
                .setColor(Color.RED)
                .build()

        notificationManager.notify(NOTIFICATION_BIG_TEXT_ID, notification)
    }

    fun inboxStyleNotification(context: Context, title: String, msg: String) {
        val notificationManager = NotificationManagerCompat.from(context)

        val notification =
            NotificationCompat.Builder(context, NOTIFICATION_INBOX_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setStyle(
                    NotificationCompat.InboxStyle()
                        .setBigContentTitle("Inbox Style Notification")
                        .setSummaryText("demo@gmail.com")
                        .addLine("Message1. For more information about the design and interaction patterns, see the Notifications design guide.")
                        .addLine("Message2. For more information about the design and interaction patterns, see the Notifications design guide.")
                        .addLine("Message3. For more information about the design and interaction patterns, see the Notifications design guide.")
                        .addLine("Message4. For more information about the design and interaction patterns, see the Notifications design guide.")
                        .addLine("Message5. For more information about the design and interaction patterns, see the Notifications design guide.")
                        .addLine("Message6. For more information about the design and interaction patterns, see the Notifications design guide.")
                        .addLine("Message7. For more information about the design and interaction patterns, see the Notifications design guide.")
                        .addLine("Message8. For more information about the design and interaction patterns, see the Notifications design guide.")
                        .addLine("Message9. For more information about the design and interaction patterns, see the Notifications design guide.")

                )
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_HIGH) // required for api level < 26
                .setCategory(NotificationCompat.CATEGORY_PROMO)
                .setVibrate(longArrayOf(100, 100, 100, 100))
                .setSound(getUriFromResourceFile(context, R.raw.custom_notification_cound))
                .setOngoing(false)
                .setAutoCancel(true)
                .setColor(Color.RED)
                .build()

        notificationManager.notify(NOTIFICATION_INBOX_TEXT_ID, notification)
    }

    fun bigPictureStyleNotification(context: Context, title: String, msg: String, bitmap: Bitmap) {
        val notificationManager = NotificationManagerCompat.from(context)
        val bigPictureBitmap = BitmapFactory.decodeResource(context.resources,R.drawable.j1)
        val largeIconBitmap = BitmapFactory.decodeResource(context.resources,R.drawable.j3)

        val notification =
            NotificationCompat.Builder(context, NOTIFICATION_BIG_PICTURE_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .setBigContentTitle("Big Picture Title")
                        .setSummaryText("Big Picture Summery")
                        .bigPicture(bigPictureBitmap)
                        .bigLargeIcon(bitmap)
                )
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_HIGH) // required for api level < 26
                .setCategory(NotificationCompat.CATEGORY_PROMO)
                .setAutoCancel(true)
                .build()

        notificationManager.notify(NOTIFICATION_BIG_PICTURE_TEXT_ID, notification)
    }

    fun downloadingStyleNotification(context: Context, title: String, msg: String) {
        val notificationManager = NotificationManagerCompat.from(context)
        val maxProgress = 100
        var minProgress = 0

        val notificationBuilder =
            NotificationCompat.Builder(context, NOTIFICATION_DOWNLOADING_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentTitle(title)
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_HIGH) // required for api level < 26
                .setCategory(NotificationCompat.CATEGORY_PROGRESS)
                .setOngoing(true)
                .setProgress(maxProgress,0, true)

        notificationManager.notify(NOTIFICATION_DOWNLOADING_ID, notificationBuilder.build())

        /*CoroutineScope(Dispatchers.Main).launch {
            while (minProgress <= maxProgress) {
                minProgress += 10
                notificationBuilder.setProgress(maxProgress,minProgress,false)
                notificationManager.notify(NOTIFICATION_DOWNLOADING_ID, notificationBuilder.build())
                delay(1000)
            }

            notificationBuilder.setContentTitle("Download Finished")
                .setProgress(0,0,false)
                .setOngoing(false)
            notificationManager.notify(NOTIFICATION_DOWNLOADING_ID, notificationBuilder.build())

        }*/


    }

    fun messagingStyleNotification(context: Context) {
        val notificationManager = NotificationManagerCompat.from(context)
        var messagePendingIntent: PendingIntent? = null

        val contentIntent = Intent(context, MainActivity::class.java).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
            action = Intent.ACTION_MAIN
        }
        val contentPendingIntent = PendingIntent.getActivity(
            context, 0, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        //api check
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val messageReceiver = Intent(context,MessageReceiver::class.java)
            messagePendingIntent = PendingIntent.getBroadcast(
                context,0,messageReceiver,PendingIntent.FLAG_UPDATE_CURRENT)
        }
        else {
            notificationManager.cancel(NOTIFICATION_MESSAGE_ID)
        }

        //build a sender
        val sender = Person.Builder()
            .setName("Richard")
            .setIcon(IconCompat.createWithResource(context,R.drawable.ic_person))
            .build()
        //create a MessagingStyle
        val messageStyle = NotificationCompat.MessagingStyle(sender)
            .setConversationTitle("Private Chat")

        //loop through the message list and add to messageStyle
        messageList.forEach {
            val notificationMessage = NotificationCompat.MessagingStyle.Message(
                it.text,it.timeStamp,it.person
            )
            messageStyle.addMessage(notificationMessage)
        }

        //remote input
        val remoteInput = RemoteInput.Builder("key_reply")
            .setLabel("Please type here...")
            .build()

        //replay action
        val replayAction = NotificationCompat.Action.Builder(
            R.drawable.ic_action_message,"Reply",messagePendingIntent
        ).addRemoteInput(remoteInput)
            .build()

        val notification =
            NotificationCompat.Builder(context, NOTIFICATION_MESSAGE_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setPriority(NotificationCompat.PRIORITY_HIGH) // required for api level < 26
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setStyle(messageStyle)
                .addAction(replayAction)
                .setContentIntent(contentPendingIntent)
                .setAutoCancel(true)
                .setColor(Color.GREEN)
                .build()

        notificationManager.notify(NOTIFICATION_MESSAGE_ID, notification)
    }

    fun mediaStyleNotification(context: Context) {
        val notificationManager = NotificationManagerCompat.from(context)
        //build a media session
        val mediaSession = MediaSessionCompat(context,"MediaNotification")
        mediaSession.setMetadata(
            MediaMetadataCompat.Builder()
                .putString(MediaMetadata.METADATA_KEY_TITLE,"Song Title")
                .putString(MediaMetadata.METADATA_KEY_ARTIST,"Song Artist")
                .build()
        )

        val largeIcon = BitmapFactory.decodeResource(context.resources, R.drawable.j1)

        val notification =
            NotificationCompat.Builder(context, NOTIFICATION_MEDIA_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setLargeIcon(largeIcon)
                .setContentTitle("Track 1")
                .setContentText("Song content..")
                .setPriority(NotificationCompat.PRIORITY_HIGH) // required for api level < 26
                .setCategory(NotificationCompat.CATEGORY_PROGRESS)
                .addAction(R.drawable.ic_action_previous,"Previous",null)
                .addAction(R.drawable.ic_action_next,"Next",null)
                .addAction(R.drawable.ic_action_pause,"Pause",null)
                .addAction(R.drawable.ic_action_stop,"Stop",null)
                .addAction(R.drawable.ic_action_fav,"Favorite",null)
                .setStyle(
                    androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0,1,2)
                        .setMediaSession(mediaSession.sessionToken)
                )
                .setOngoing(true)
                .setAutoCancel(false)
                .build()

        notificationManager.notify(NOTIFICATION_MEDIA_ID, notification)
    }

    fun customStyleNotification(context: Context, title: String, msg: String) {
        val notificationManager = NotificationManagerCompat.from(context)
        //intent
        val intent = Intent(context,CustomNotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context,0,intent,0)

        val collapseView = RemoteViews(context.packageName,R.layout.collapse_notification_view)
        val expandedView = RemoteViews(context.packageName,R.layout.expanded_notifcation_view)

        collapseView.setTextViewText(R.id.txt_collapse_title,title)
        collapseView.setTextViewText(R.id.txt_collapse_info,msg)
        expandedView.setImageViewResource(R.id.expanded_imageView,R.drawable.j3)
        expandedView.setOnClickPendingIntent(R.id.expanded_btnSend, pendingIntent)

        val notification =
            NotificationCompat.Builder(context, NOTIFICATION_CUSTOM_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setCustomHeadsUpContentView(collapseView)
                .setCustomContentView(collapseView)
                .setCustomBigContentView(expandedView)
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                .build()

        notificationManager.notify(NOTIFICATION_CUSTOM_ID, notification)
    }

    fun isNotificationEnabled(context: Context): Boolean {
        val notificationManager = NotificationManagerCompat.from(context)
        return notificationManager.areNotificationsEnabled()
    }

    fun openNotificationSetting(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                this.putExtra(Settings.EXTRA_APP_PACKAGE,context.packageName)
                context.startActivity(this)
            }
        }
        else {
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                this.data = Uri.parse( "package:${context.packageName}")
                context.startActivity(this)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun isNotificationChannelEnabled(context: Context, id: String) : Boolean {
        val notificationManager = NotificationManagerCompat.from(context)

        return notificationManager.getNotificationChannel(id).run {
            this?.importance == NotificationManager.IMPORTANCE_NONE
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun openNotificationChannelSetting(context: Context, channelId:String) {
        val intent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS).apply {
            putExtra(Settings.EXTRA_APP_PACKAGE,context.packageName)
            putExtra(Settings.EXTRA_CHANNEL_ID, channelId)
        }
        context.startActivity(intent)
    }

    fun deleteNotificationChannel(context: Context, channelId: String) {
        val notificationManager  = NotificationManagerCompat.from(context)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.deleteNotificationChannel(channelId)
            Toast.makeText(context,"Notification channel is deleted: $channelId",Toast.LENGTH_LONG).show()
        }
    }

    fun deleteNotificationGroup(context: Context, groupId: String) {
        val notificationManager = NotificationManagerCompat.from(context)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.deleteNotificationChannelGroup(groupId)
            Toast.makeText(context,"Notification channel group is deleted: $groupId",Toast.LENGTH_LONG).show()
        }
    }


    private fun getUriFromResourceFile(context: Context, resourceId: Int): Uri {
        return Uri.parse("android.resource://${context.packageName}/$resourceId")
    }

}