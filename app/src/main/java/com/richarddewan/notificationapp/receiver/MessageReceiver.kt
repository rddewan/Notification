package com.richarddewan.notificationapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.RemoteInput
import com.richarddewan.notificationapp.util.AppMessage
import com.richarddewan.notificationapp.util.NotificationHelper

class MessageReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val remoteInput = RemoteInput.getResultsFromIntent(intent)
        remoteInput?.let {
            val replayText = it.getCharSequence("key_reply")
            replayText?.let { text->
                Toast.makeText(context,text,Toast.LENGTH_LONG).show()
                NotificationHelper.messageList.add(
                    AppMessage(text.toString(),154848,null)
                )

                NotificationHelper.messagingStyleNotification(context)
            }
        }
    }
}