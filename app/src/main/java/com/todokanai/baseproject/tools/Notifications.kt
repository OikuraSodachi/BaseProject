package com.todokanai.baseproject.tools

import android.app.NotificationChannel
import android.content.Context
import com.todokanai.baseproject.abstracts.BaseNotification

class Notifications(
    context: Context,
    notificationChannel: NotificationChannel
): BaseNotification(context,notificationChannel) {

    fun postNotification(
        title:String,
        contentText:String,
        icon:Int,
    ){
        post(
            title = title,
            contentText = contentText,
            icon = icon
        )
    }
}