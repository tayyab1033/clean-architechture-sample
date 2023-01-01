package com.example.task_todoapp.remote

import android.util.Log
import javax.inject.Inject

class Test @Inject constructor() {
    fun showTestMessage() {
        Log.d("Tayyab", "showTestMessage: Hey this is me. i am here for testing")
//        System.out.println("Hey this is me. i am here for testing")
    }
}