package com.todokanai.baseproject

import android.app.NotificationChannel
import android.content.Context

class Notifications(
    context: Context,
    notificationChannel: NotificationChannel
): BaseNotification(context,notificationChannel) {

    /** post the notification**/
    fun post(contentText:String){
        val id = 1
        val icon = R.drawable.ic_launcher_foreground
        val title = "Title"
        val noti =
            basicNotification(
                icon,
                title,
                contentText,
            )
        manager.notify(id,noti)
    }
}