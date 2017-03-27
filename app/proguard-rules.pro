# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/zhujian/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontpreverify
-repackageclasses ''
-allowaccessmodification
-optimizations !code/simplification/arithmetic
-keepattributes *Annotation*,Signature

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService


-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public <init>(android.content.Context, android.util.AttributeSet, int, int);
    public void set*(...);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.content.Context {
   public void *(android.view.View);
   public void *(android.view.MenuItem);
}

-keep class **.R$* {
    public static <fields>;
}

-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

-keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
    native <methods>;
}
-keepclasseswithmembers class * {   # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被混淆
    public void *(android.view.View);
}
-keepclassmembers enum * {     # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
    public static final android.os.Parcelable$Creator *;
}

# 忽略所有支持序列化的class
-keep class * implements java.io.Serializable {*;}

-dontwarn com.squareup.okhttp3.**
-dontwarn java.lang.invoke.*
-dontwarn android.support.**
-dontwarn org.apache.http.**
-dontwarn okio.**
-keep class okio.**
-dontwarn com.tencent.**
-dontwarn com.squareup.**
-dontwarn okhttp3.**
-keep class org.apache.http.** {*;}
-keep class com.squareup.okhttp3.** { *;}

-dontwarn javax.annotation.**
-dontwarn javax.inject.**

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-dontwarn rx.**
-keep class rx.** { *; }

-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

#fastjson
-dontwarn com.alibaba.fastjson.**
-keep class com.alibaba.fastjson.**{*;}
-keep class * implements java.io.Serializable { *; }
-keepattributes Signature
-dontskipnonpubliclibraryclassmembers
-dontskipnonpubliclibraryclasses

#Glide
-keep public class com.jzhu.io.baselibrary.utils.glide.CusGlideModule
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;}

#jp.wasabeef:glide-transformations:2.0.1
-keep class p.co.** { *; }
-dontwarn jp.co.cyberagent.android.gpuimage.**

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#==================gson && protobuf==========================

#Orm混淆建议：
# 1. 给你要持久化的每一个Java（Model）类设置一个表名：即为类添加@Table("table_name")注解。
# 2. 给你要持久化的每一个属性（成员变量）设置一个列名： 即为属性加@Column("column_name")注解。
# 满足1、2则可以将你要持久化的类和者属性随意混淆；
# 反之，则需要将你要持久化的类和属性keep住，不可混淆。
#https://github.com/litesuits/android-lite-orm

#另外，枚举和注解、签名要keep：
# 使用注解
-keep class com.jzhu.io.data.entities.** { *; }
-keep class com.jzhu.io.data.** { *; }
-keepattributes Signature

#bga
-dontwarn cn.bingoogolapple.**
-keep class cn.bingoogolapple.** { *; }

# nineoldandroids
-keep public class com.nineoldandroids.** {*;}

-keep public class com.jzhu.io.gank.R$*{
public static final int *;
}

#router
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

-ignorewarnings

#-libraryjars class_path 应用的依赖包，如android-support-v4
# -keep [,modifier,...] class_specification 这里的keep就是保持的意思，意味着不混淆某些类
# -keepclassmembers [,modifier,...] class_specification 同样的保持，不混淆类的成员
# -keepclasseswithmembers [,modifier,...] class_specification 不混淆类及其成员
#-keepnames class_specification 不混淆类及其成员名
# -keepclassmembernames class_specification 不混淆类的成员名
# -keepclasseswithmembernames class_specification 不混淆类及其成员名
# -assumenosideeffects class_specification 假设调用不产生任何影响，在proguard代码优化时会将该调用remove掉。如system.out.println和Log.v等等
# -dontwarn [class_filter] 不提示warnning
