package com.todokanai.baseproject

import android.app.Notification
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

abstract class BaseNotification(
    val context: Context,
    val notificationChannel: NotificationChannel,
) {

    val manager = NotificationManagerCompat.from(context)

    /** @return basic [Notification] instance **/
    open fun basicNotification(
        icon:Int,
        title:String,
        contentText:String,
        onNotificationClick: PendingIntent? = null
    ): Notification {
        val result = NotificationCompat.Builder(context,notificationChannel.id)
            .setContentTitle(title)
            .setSmallIcon(icon)
            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
            .setContentIntent(onNotificationClick)
            .build()
        return result
    }
}