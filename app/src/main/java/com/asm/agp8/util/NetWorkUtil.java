package com.asm.agp8.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

public class NetWorkUtil {

    private static ConnectivityManager mConnManager;
    public static NetworkInfo mNetworkInfo;
    /**
     * 移动网络
     */
    public static final int WAP = -5;
    /**
     * 成功
     */
    public static final int OK = 1;
    /**
     * 关闭
     */
    public static final int DISABLE = -1;
    /**
     * 失败
     */
    public static final int FAIL = -2;
    /**
     * 未开启移动网络或WLAN
     */
    public static final int NOINFO = -3;
    /**
     * 异常
     */
    public static final int EXCEPTION = -4;

    /**
     * 检测当前是否有可用网络
     *
     * @param context 上下文
     * @return 1：可用； -1：不可用；0：移动网络； -2：连接测试网站失败； -3：无网络信息； -4：异常
     */
    public static int checkNetConnection(Context context) {
        try {
            ConnectivityManager mConnManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            mNetworkInfo = mConnManager.getActiveNetworkInfo();

            if (mNetworkInfo == null) {// 检测是否有可用网络信息
                return NOINFO;
            }
            // Debug.Print("netTypeName()=" + mNetworkInfo.getTypeName() + " netExtraInfo()="
            // + mNetworkInfo.getExtraInfo() + " netisConnected()=" + mNetworkInfo.isConnected());
            if (!mNetworkInfo.isConnected()) {
                return DISABLE;// 网络没有连接
            }
            // 检测是否是中CMWAP网络,先判断是否为空
            if (!TextUtils.isEmpty(mNetworkInfo.getExtraInfo())
                    && mNetworkInfo.getExtraInfo().toLowerCase().indexOf("wap") > 0) {
                return WAP;
            }
            return OK;

        } catch (Exception e) {
            return EXCEPTION;
        }
    }
}
