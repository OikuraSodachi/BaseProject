package com.todokanai.baseproject.tools

import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.todokanai.baseproject.R
import com.todokanai.baseproject.abstracts.BaseNotification
import com.todokanai.baseproject.di.MyApplication.Companion.appContext
import com.todokanai.baseproject.myobjects.Constants

/** example of [BaseNotification] instance **/
class Notifications(
    val notificationManager:NotificationManagerCompat
): BaseNotification() {

    companion object{

        /** Makes a sound and appears as a heads-up notification **/
        private const val URGENT = NotificationManager.IMPORTANCE_HIGH

        /** Makes a sound **/
        private const val HIGH = NotificationManager.IMPORTANCE_DEFAULT

        /** Makes no sound **/
        private const val MEDIUM = NotificationManager.IMPORTANCE_LOW

        /** Makes no sound and doesn't appear in the status bar **/
        private const val LOW = NotificationManager.IMPORTANCE_MIN

        /** Makes no sound and doesn't appear in the status bar or shade **/
        private const val NONE = NotificationManager.IMPORTANCE_NONE

        /** the notification's full content shows on the lock screen **/
        private const val PUBLIC = NotificationCompat.VISIBILITY_PUBLIC

        /** no part of the notification shows on the lock screen **/
        private const val SECRET = NotificationCompat.VISIBILITY_SECRET

        /** only basic information, such as the notification's icon and the content title, shows on the lock screen. The notification's full content doesn't show **/
        private const val PRIVATE = NotificationCompat.VISIBILITY_PRIVATE
    }

    val defaultChannel = NotificationChannel(
        Constants.CHANNEL_ID,
        Constants.NOTIFICATION_CHANNEL_NAME,
        NotificationManager.IMPORTANCE_LOW
    ).apply {
        notificationManager.createNotificationChannel(this)
    }

    override fun appIcon(): Int {
        return R.drawable.ic_launcher_foreground
    }

    override fun postNotification(title: String, contentText: String) {
        val temp =builder()
            .setContentTitle(title)
            .setContentText(contentText)
            .setSmallIcon(appIcon())
            .setVisibility(PUBLIC)
            .build()
        notificationManager.notify(1,temp)
    }

    override fun builder(): NotificationCompat.Builder {
        return NotificationCompat.Builder(appContext,defaultChannel.id)
    }
}