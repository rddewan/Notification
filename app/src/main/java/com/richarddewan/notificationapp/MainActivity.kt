package com.richarddewan.notificationapp

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.richarddewan.notificationapp.databinding.ActivityMainBinding
import com.richarddewan.notificationapp.util.NotificationHelper


class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    private var bitmap: Bitmap ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        setupGlide()

        buttonClickListener()

    }

    private fun setupGlide(){
        Glide.with(this)
            .asBitmap()
            .load(R.drawable.j3)
            .circleCrop()
            .into(object: CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bitmap = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    bitmap = null
                }


            })
    }

    private fun buttonClickListener(){

       binding.btnDefaultNotification.setOnClickListener {
           val title = binding.txtTitle.text
           val msg = binding.txtMessage.text

           NotificationHelper.defaultNotification(this,title.toString(),msg.toString())
       }

        binding.btnHighNotification.setOnClickListener {
            val title = binding.txtTitle.text
            val msg = binding.txtMessage.text

            NotificationHelper.highNotification(this,title.toString(),msg.toString())
        }

        binding.btnLowNotification.setOnClickListener {
            val title = binding.txtTitle.text
            val msg = binding.txtMessage.text

            NotificationHelper.lowNotification(this,title.toString(),msg.toString())
        }

        binding.btnActionNotification.setOnClickListener {
            val title = binding.txtTitle.text
            val msg = binding.txtMessage.text

            NotificationHelper.actionNotification(this,title.toString(),msg.toString())
        }

        binding.btnContentIntentNotification.setOnClickListener {
            val title = binding.txtTitle.text
            val msg = binding.txtMessage.text

            NotificationHelper.contentIntentNotification(this,title.toString(),msg.toString())
        }

        binding.btnCustomSoundNotification.setOnClickListener {
            val title = binding.txtTitle.text
            val msg = binding.txtMessage.text

            NotificationHelper.customSoundNotification(this,title.toString(),msg.toString())
        }

        binding.btnBigTextStyleNotification.setOnClickListener {
            val title = binding.txtTitle.text
            val msg = binding.txtMessage.text

            NotificationHelper.bigTextStyleNotification(this,title.toString(),msg.toString())
        }

        binding.btnInboxTextStyleNotification.setOnClickListener {
            val title = binding.txtTitle.text
            val msg = binding.txtMessage.text

            NotificationHelper.inboxStyleNotification(this,title.toString(),msg.toString())
        }

        binding.btnbigPictureStyleNotification.setOnClickListener {
            val title = binding.txtTitle.text
            val msg = binding.txtMessage.text

            NotificationHelper.bigPictureStyleNotification(this,title.toString(),msg.toString(),bitmap!!)
        }

        binding.btnDownloadingStyleNotification.setOnClickListener {
            val title = binding.txtTitle.text
            val msg = binding.txtMessage.text

            NotificationHelper.downloadingStyleNotification(this,title.toString(),msg.toString())
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}