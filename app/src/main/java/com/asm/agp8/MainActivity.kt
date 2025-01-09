package com.asm.agp8

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.asm.agp8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
//        write("glog存储路径 ")
//        write("注册广播监听")
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

//    private final void test1() {
//        Button button = (Button) _$_findCachedViewById(R.id.btnGetRunningAppProcesses);
//        Object systemService = App.appContext.getSystemService("connectivity");
//        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
//        ?? r0 = (ConnectivityManager) systemService;
//        NetworkInfo activeNetworkInfo = PrivacyUtil.getActiveNetworkInfo();
//        r0.setText(activeNetworkInfo != null ? activeNetworkInfo.getTypeName() : null);
//    }

}