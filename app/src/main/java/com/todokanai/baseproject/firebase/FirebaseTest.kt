package com.todokanai.baseproject.firebase

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/** FCM messaging based on Firebase Realtime Database **/
class FirebaseTest(database:FirebaseDatabase) {

    private val ref = database.reference

    fun addListener(listener:ValueEventListener){

    }

    fun send(value:Any){
        ref.setValue(value)
    }
}