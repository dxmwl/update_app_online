package com.clb.update_app_online

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.clb.update_app_online.UpdateChecker.Callback

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_check_update).setOnClickListener {
            UpDateApp.checkUpdate(object : Callback {
                override fun result(updateInfo: UpdateChecker.UpdateInfo?) {
                    //注意这里要在主线程中执行更新UI的操作
                    runOnUiThread {
                        AlertDialog.Builder(this@MainActivity)
                            .setTitle("版本更新")
                            .setMessage(
                                "是否强制更新：${updateInfo?.needForceUpdate}\n" +
                                        "应用安装地址：${updateInfo?.downloadURL}\n" +
                                        "版本号：${updateInfo?.buildVersion}\n" +
                                        "应用更新说明：${updateInfo?.buildUpdateDescription}"
                            )
                            .show()
                    }
                }

                override fun error(message: String?) {
                    Log.e(TAG, "error: $message")
                    //注意这里要在主线程中执行更新UI的操作
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "错误信息：${message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            })
        }
    }
}