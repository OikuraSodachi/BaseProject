package com.todokanai.baseprojectmodule.abstracts

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat

/** Notification 관리를 위한 class **/
abstract class BaseNotification() {

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

    /** @return basic [Notification] instance **/
    open fun basicNotification(
        context:Context,
        title:String,
        contentText:String,
        notificationChannel: NotificationChannel,
        visibility:Int,
        onNotificationClick: PendingIntent? = null
    ): Notification {
        val result = NotificationCompat.Builder(context,notificationChannel.id)
            .setContentTitle(title)
            .setContentText(contentText)
            .setSmallIcon(appIcon())
            .setVisibility(visibility)
            .setContentIntent(onNotificationClick)
            .build()
        return result
    }

    /** get the smallIcon for the notification **/
    abstract fun appIcon():Int

    abstract fun postNotification(
        title:String,
        contentText:String,
    )

    abstract fun builder():NotificationCompat.Builder
}