# 应用内更新
[![](https://jitpack.io/v/dxmwl/update_app_online.svg)](https://jitpack.io/#dxmwl/update_app_online)

本项目借助蒲公英分发相关api，封装了一个简单的应用内更新功能，无需让后台开发相关更新接口即可实现应用内更新功能，使用简单，仅需几行代码，即可实现。配合- [小蜜蜂传包（已开源）](https://github.com/dxmwl/new_bee_upload_app) 使用效果更佳

## 开发计划
1. 内置更新弹窗，简单项目无需自行实现或集成三方更新弹窗
2. 如有需要，实现其他分发平台的功能对接

## 使用说明

1. Add it in your root build.gradle at the end of repositories:
	```groovy
        dependencyResolutionManagement {
            repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
            repositories {
                mavenCentral()
                maven { url 'https://jitpack.io' }
            }
        }
    ```

2. Add the dependency

    ```groovy
    dependencies {
        implementation 'com.github.dxmwl:update_app_online:1.0'
    }
    ```

3. 获取蒲公英的api_key和APPKey，[文档地址](https://www.pgyer.com/doc/view/api#appUpdate)

4. 初始化SDK
    ```kotlin
    class MyApp: Application() {
    
        override fun onCreate() {
            super.onCreate()
            //替换为你自己的api_key和APPKey
            UpDateApp.init("ede71b84c7e1009fe6bdee737c7dfaf4","5885ac48608e2a0470266d3980484746")
        }
    }
    ```
   5. 检查版本更新，//注意返回结果后要在主线程中执行更新UI的操作
       ```kotlin
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
       ```

### 相关字段说明
```java
    public static class UpdateInfo {
        /** 蒲公英生成的用于区分历史版本的build号 */
        public Integer buildBuildVersion = 0;
        /** 强制更新版本号（未设置强置更新默认为空） */
        public String forceUpdateVersion = "";
        /** 强制更新的版本编号 */
        public String forceUpdateVersionNo = "";
        /** 是否强制更新 */
        public Boolean needForceUpdate = false;
        /** 应用安装地址 */
        public String downloadURL = "";
        /** 是否有新版本 */
        public Boolean buildHaveNewVersion = false;
        /** 上传包的版本编号，默认为1 (即编译的版本号，一般来说，编译一次会变动一次这个版本号, 在 Android 上叫 Version Code。对于 iOS 来说，是字符串类型；对于 Android 来说是一个整数。例如：1001，28等。) */
        public String buildVersionNo = "";
        /** 版本号, 默认为1.0 (是应用向用户宣传时候用到的标识，例如：1.1、8.2.1等。) */
        public String buildVersion = "";
        /** 应用短链接 */
        public String buildShortcutUrl = "";
        /** 应用更新说明 */
        public String buildUpdateDescription = "";
    }
```

#### 作者的其他项目
- [友你](https://sj.qq.com/appdetail/com.youni.mobile) 友你是一款征婚交友APP,在这里,你可以把你的真实信息登记下来,系统会根据您的信息,为您匹配最合适的TA,友你集交友、恋爱于一身，通过在线匹配，解决陌生人社交破冰难题，打造更真实的恋爱社区。
- [友圈](https://sj.qq.com/appdetail/com.youquan.mobile) 友圈是一款基于圈子交友的社区交友软件，被广大年轻人所青睐，在这里有着你所感兴趣的方方面面，应用内拥有生活、游戏、元宇宙、二次元、娱乐、绘画、设计、文学、时尚等多个领域，上千种兴趣标签，给你丰富的吐槽空间，在这里你可以吐槽生活中的不愉快，也可以针对时事新闻发表自己的观点。
- [一木林（已开源）](https://sj.qq.com/appdetail/com.yimulin.mobile) [开源版本](https://github.com/dxmwl/Yimulin)这是一款多功能工具类应用，因为 一木林 体积十分小巧而功能却又非常的完善强大，使它风评很高。
- [天天省钱（计划开源）](https://sj.qq.com/appdetail/com.ttsq.mobile) 优惠券，优惠劵，优惠，淘宝优惠券，返利优惠券，返利网，拼多多优惠券，饿了么红包，外卖红包优惠劵，淘趣购物返利优惠券，省钱就选天天省钱。 专注于淘宝优惠券的购物APP，超级折扣超级优惠，省钱20%以上。
- [青果短剧（已开源）](https://github.com/dxmwl/qg_android) 这是一个免费观看短剧、短视频的开源项目，供大家免费学习使用
- [小蜜蜂传包（已开源）](https://github.com/dxmwl/new_bee_upload_app) 一键上传Apk到多个应用市场，开源，免费
- [应用内更新（已开源）](https://github.com/dxmwl/update_app_online) 几行代码实现应用内更新功能

#### 联系开发者
欢迎加入开发者交流群，可加我微信:dxmcpjl,加好友备注"应用内更新",否则可能无法添加好友,如果本项目对您的业务有所帮助，欢迎对本项目进行资助，我将对本项目进行持续维护

| ![输入图片说明](pictures/963a20fad5b96ec502acdad875776ac.jpg) | ![输入图片说明](pictures/c703e10d18655356cf05d4ccb7ec34f.jpg) |  ![输入图片说明](pictures/dd1fae18c9c1bf30d50070e951dfe39.jpg) |
|---------------------------------------------------------|---------------------------------------------------|---|

## 写在最后
撸码不易，欢迎点赞对我进行鼓励，点赞越多，优化越快