<p>
<a href="#" onclick="showITLearnPage('softwareusage')">返回</a>&emsp;&emsp;&emsp;
</p>

---

## 最新版Android Studio设置国内镜像代理(解决无法更新的问题)

- <a href="https://blog.csdn.net/yihui8/article/details/108186571" target="_blank">https://blog.csdn.net/yihui8/article/details/108186571 </a>

连接模拟器 mumu连 它

        第一步 : 找到MuMu模拟器的 adb_server.exe文件

        第二步 : 打开MuMu模拟器(如果不运行,第六步执行不了,别忘了)

        第三步 : 点击屏幕左下角"开始"按钮,并输入cmd,打开cmd.exe

        第四部 : 因为我的 adb_server.exe在D盘下,先跳一下盘符,直接输入d:,点击回车即可

        第五步 : 复制 adb_server.exe的全路径,先输入cd+空格 ,再鼠标右键粘贴(cmd中不支持Ctrl+v),回车

        第六步 : 复制 adb_server.exe connect 127.0.0.1:7555,粘贴到D:\...\bin>之后,回车(如果MuMu模拟器没有打开,会显示连接失败)

        第七步 : 点击AS运行,就能搜索到MuMu模拟器了!!!

        作者：MrZac_
        链接：https://www.jianshu.com/p/7df948707294
        来源：简书
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    
    它连 mumu
        输入D:\Program Files\Android\Sdk\platform-tools　　　　（adb所在的路径）
        再输入：adb connect 127.0.0.1:7555
        
        https://www.cnblogs.com/ishai/p/11068899.html

## Android Studio和Gradle使用不同位置JDK的问题

(Android Studioand Gradle are using different locations forthe JDK)

- <a href="https://blog.csdn.net/Zhongtongyi/article/details/104563327" target="_blank">https://blog.csdn.net/Zhongtongyi/article/details/104563327 </a>

## Android中textview居中显示

- <a href="https://www.cnblogs.com/wangguangjian/p/8693215.html" target="_blank">https://www.cnblogs.com/wangguangjian/p/8693215.html </a>

android:gravity="center"    android:textAlignment="center"

        <?xml version="1.0" encoding="utf-8"?>
        <manifest xmlns:android=http://schemas.android.com/apk/res/android
            package="com.xxx.app"
            android:versio  nCode="1"
            android:versionName="1.0" >

            <uses-sdk
                android:minSdkVersion="8"
                android:targetSdkVersion="17" />

            <application
                android:allowBackup="true"
                android:icon="@drawable/ic_launcher"
                android:label="@string/app_name"  #设置应用列表中应用名称
                android:theme="@style/AppTheme" >
                <activity
                    android:name="com.supdo.demos.LoginActivity"
                    android:label="@string/app_name" #设置手机桌面上图标下面的名称
                    android:windowSoftInputMode="adjustResize|stateVisible" >
                    <intent-filter>
                        <action android:name="android.intent.action.MAIN" />
                        <category android:name="android.intent.category.LAUNCHER" />
                    </intent-filter>
                </activity>
            </application>
        </manifest>

## Android 去掉界面标题栏的方法

- <a href="https://www.cnblogs.com/wukong1688/p/10662084.html" target="_blank">[Android] Android 去掉界面标题栏的方法 </a>
- <a href="https://blog.csdn.net/FL1623863129/article/details/72782195" target="_blank">
  Android应用名与activity窗口标题名字如何做到不一样 </a>

- <a href="https://blog.csdn.net/Dreaming_terry/article/details/51184508" target="_blank">android界面不显示标题栏 </a>

  这个首先要区分当前Activity 是继承了 Activity 类 ，还是 AppCompatActivity 类

  情况一:创建的activity默认继承了AppCompatActivity

        方法一)全局设置

            可以在AndroidManifest.xml中作如下配置，这样就没有标题栏了
            <application android:theme="@style/Theme.AppCompat.NoActionBar">

        方法二)针对当前页面单独设置

            getSupportActionBar().hide();
            //去掉标题栏注意这句一定要写在setContentView()方法的后面

  情况二:创建的activity默认继承了Activity

        方法一)全局设置

            <application android:theme="@android:style/Theme.NoTitleBar">
            这样写的话，整个应用都会去掉标题栏，如果只想去掉某一个Activity的标题栏的话，可以把这个属性加到某个activity标签里面

        方法二)针对当前页面单独设置

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            //去掉标题栏注意这句一定要写在setContentView()方法的前面，不然会报错的

## android studio 使用 databinding 基础配置

- <a href="https://blog.csdn.net/sinat_30288471/article/details/83276371" target="_blank">android studio 使用 databinding
  基础配置 </a>

- <a href="https://blog.csdn.net/qq_32209403/article/details/58594483" target="_blank">Android
  MVVM结合DataBinding的简单实用（Android studio）及自己遇到的坑 </a>

- <a href="https://blog.csdn.net/sunweihao2019/article/details/105560068" target="_blank">android studio DataBinding</a>

- <a href="https://blog.csdn.net/qiang_xi/article/details/73849556" target="_blank">DataBinding使用教程（一）：配置与基本使用</a>

- <a href="https://blog.csdn.net/weixin_44613063/article/details/103553014" target="_blank">Android开发之DataBinding的使用 (
  Kotlin) </a>


- <a href="https://blog.csdn.net/qq_36721220/article/details/111689803" target="_blank">Android Studio之Warning记录</a>

          
            
