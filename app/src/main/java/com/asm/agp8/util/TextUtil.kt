package com.asm.agp8.util

import android.os.Looper
import android.util.Log


object TextUtil {
    fun getOriginTxt(): String {
        return "origin text"
    }


    @JvmStatic
    fun insetBeforeTest() {
        System.out.println("doing.....")
    }

    @JvmStatic
    fun insetAfterTest() {
        System.out.println("doing.....")
    }


    @JvmStatic
    fun checkThread() {
        if (Thread.currentThread() == Looper.getMainLooper().thread) {
            Log.e("Check_UIThread", Log.getStackTraceString(Throwable("在主线程进行耗时操作")))
        }

    }

    var deep = 0

    @JvmStatic
    fun methodIn() {
        deep++
    }

    @JvmStatic
    fun methodOut(startTime: Long, methodName: String) {
        write("${methodName}<$deep-" + (System.currentTimeMillis() - startTime))
        deep--
    }

    fun write(log: String) {
        println(log)
    }

}