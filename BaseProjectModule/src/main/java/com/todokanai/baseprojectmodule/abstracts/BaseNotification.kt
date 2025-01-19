package com.todokanai.baseprojectmodule.abstracts

import android.app.Notification
import android.app.NotificationChannel
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

/** Notification 관리를 위한 class
 *
 * @param context Context of notification
 * @param notificationChannel default channel for the notification
 *
 * **/
abstract class BaseNotification(
    val context: Context,
    val notificationChannel: NotificationChannel,
) {

    val manager = NotificationManagerCompat.from(context).apply {
        createNotificationChannels(listOf(notificationChannel))
    }       // creates notification channel when initializing notificationManager instance

    /** [basicNotification]의 visibility **/
    private val basicVisibility = NotificationCompat.VISIBILITY_PRIVATE

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
            .setVisibility(basicVisibility)
            .setContentIntent(onNotificationClick)
            .build()
        return result
    }

    /** post the notification
     * @param title title of the notification
     * @param contentText text to show on the notification
     * @param icon smallIcon to apply
     * @param id id of the notification
     * **/
    fun post(
        title:String,
        contentText:String,
        icon: Int,
        id:Int = 1,  // Todo: NotificationChannel 이 여러개 있을 경우, id값 조정이 필요
    ){
        val noti =
            basicNotification(
                icon,
                title,
                contentText,
            )
        manager.notify(id,noti)
    }
}