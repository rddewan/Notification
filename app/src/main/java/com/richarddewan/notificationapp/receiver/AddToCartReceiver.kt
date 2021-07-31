package com.richarddewan.notificationapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AddToCartReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        Toast.makeText(context,"Item added to cart successfully!",Toast.LENGTH_LONG).show()
    }
}