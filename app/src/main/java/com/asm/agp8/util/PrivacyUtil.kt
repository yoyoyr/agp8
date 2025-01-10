package com.asm.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Looper
import android.util.Log
import androidx.annotation.Keep
import com.asm.agp8.app.App
import com.asm.agp8.util.NetWorkUtil
import com.asm.agp8.util.TextUtil
import com.asm.asm_annotation.AsmMethodOpcodes
import com.asm.asm_annotation.AsmMethodReplace
import com.asm.asm_annotation.AsmType

/**
 * @author lanxiaobin
 * @date 2021/10/9
 *
 * 1、不要被混淆
 *
 * 2、Kotlin 的方法必须要使用JvmStatic注解，否则Java调用会报错
 *
 *     java.lang.IncompatibleClassChangeError: The method
 *     'java.lang.String com.lanshifu.privacymethodhooker.utils.PrivacyUtil.getString(android.content.ContentResolver, java.lang.String)'
 *     was expected to be of type static but instead was found to be of type virtual
 *     (declaration of 'com.lanshifu.privacymethodhooker.MainActivity' appears in /data/app/com.lanshifu.privacymethodhooker-2/base.apk)
 */
@Keep
object PrivacyUtil {

    @JvmStatic
    @AsmMethodReplace(
        oriClass = ConnectivityManager::class,
        oriAccess = AsmMethodOpcodes.INVOKEVIRTUAL,
        asmType = AsmType.REPLACE
    )
    fun getActiveNetworkInfo(connectivityManager: ConnectivityManager): NetworkInfo? {
        if (NetWorkUtil.mNetworkInfo != null) {
            Log.i("TAG", "有  mNetworkInfo对象")
            return NetWorkUtil.mNetworkInfo
        } else {
            Log.i("TAG", "无  mNetworkInfo对象")
            val mConnManager =
                App.appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            NetWorkUtil.mNetworkInfo = mConnManager.activeNetworkInfo;
            return NetWorkUtil.mNetworkInfo
        }
    }

    @JvmStatic
    @AsmMethodReplace(
        oriClass = TextUtil::class,
        oriAccess = AsmMethodOpcodes.INVOKESTATIC,
        asmType = AsmType.INSERT_BEFORE
    )
    fun insetBeforeTest() {
        System.out.println("before do.....")
    }

    @JvmStatic
    @AsmMethodReplace(
        oriClass = TextUtil::class,
        oriAccess = AsmMethodOpcodes.INVOKESTATIC,
        asmType = AsmType.INSERT_AFTER
    )
    fun insetAfterTest() {
        System.out.println("after do.....")
    }


    fun checkMainThread(str: String) {
//        if (BuildConfig.DEBUG) {
            if (Thread.currentThread() == Looper.getMainLooper().thread) {
                System.out.println("$str 在主线程调用")
            }
//        }
    }
}