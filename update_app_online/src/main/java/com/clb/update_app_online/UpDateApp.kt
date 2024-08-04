package com.clb.update_app_online

import android.util.Log
import com.clb.update_app_online.UpdateChecker.Callback

object UpDateApp {


    private var initStatus = false
    private const val TAG = "UpDateApp"

    /**
     * @param api_key 蒲公英 api key
     */
    fun init(api_key: String, appKey: String) {
        if (api_key.isBlank()){
            Log.e(TAG,"初始化失败：api_key为：${api_key}")
            return
        }
        if (appKey.isBlank()){
            Log.e(TAG,"初始化失败：appKey为：${appKey}")
            return
        }
        UpdateChecker.init(api_key, appKey)
        initStatus = true
    }

    /**
     * 检查更新
     */
    fun checkUpdate(callback: Callback) {
        if (!initStatus) {
            throw RuntimeException("请先初始化")
        }
        UpdateChecker.check(callback)
    }
}