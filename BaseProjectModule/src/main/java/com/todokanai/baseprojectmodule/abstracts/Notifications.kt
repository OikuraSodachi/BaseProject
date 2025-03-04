package com.todokanai.baseprojectmodule.abstracts

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.annotation.DrawableRes

open class Notifications(
    private val notificationManager: NotificationManager,
    private val channel: NotificationChannel,
    @DrawableRes private val smallIcon: Int
){
    /** Must be called on initialization **/
    fun createNotificationChannel() = notificationManager.createNotificationChannel(channel)

    fun displayNotification(context: Context, title:String, contentText:String, notificationId:Int = 1) {
        val notification = Notification.Builder(context, channel.id)
            .setSmallIcon(smallIcon)
            .setContentTitle(title)
            .setContentText(contentText)
            .build()

        notificationManager.notify(notificationId, notification)
    }
}