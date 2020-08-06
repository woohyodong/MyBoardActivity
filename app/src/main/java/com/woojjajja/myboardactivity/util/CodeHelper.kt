package com.woojjajja.myboardactivity.util

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.util.Patterns
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.orhanobut.logger.Logger
import com.woojjajja.myboardactivity.BuildConfig
import com.woojjajja.myboardactivity.MyApplication
import java.text.SimpleDateFormat
import java.util.*



object CodeHelper {

    private val context = MyApplication.instance.applicationContext


    /**
     * yy년 MM월 dd일 E요일 => 09년 9월 10일 목요일
     * yyyy-MM-dd HH:mm:ss => 2009-09-10 15:49:43
     * yyyy-MM-dd hh:mm:ss a => 2009-09-10 03:49:43 오후
     * 오늘은 올 해의 D번째 날입니다. => 오늘은 올 해의 253번째 날입니다.
     * ex) 이 달의 d번째, 올 해의 w번째, 이 달의 W번째, 이 달의 F번째 E요일
     */
    fun getTimeStamp(format: String) : String {
        return getTimeStamp(format,Date())
    }

    fun getTimeStamp(format: String, date: Date) : String {
        return SimpleDateFormat(format, Locale.KOREA).format(date)
    }


    fun getTime() : Long {
        return System.currentTimeMillis()
    }

    fun isEmailValid(email: String) : Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    /**
     * 웹페이지 링크
     */
    fun gotoWebPage(url: String) {
        var intent: Intent
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        ContextCompat.startActivity(context, intent, null)
    }

    /**
     * 랜던 수
     * println(rand(5,10))
     * 5,6,7,8,9 Int 중 하나 출력
     */
    fun rand(from: Int, to: Int) : Int {
        return Random().nextInt(to - from) + from
    }

    /**
     * 랜던 문자
     * CodeHelper.randText()
     * CodeHelper.randText(0)
     * CodeHelper.randText(20,30)
     */
    fun randText(from: Int = 2, to: Int = 100) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z')
        val length = rand(from,to)
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    /**
     * Preference
     */
    private val PREFERENCE_NAME = "_PREFERENCE"

    private val sharePref: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val sharedEdit : SharedPreferences.Editor = sharePref.edit()

    fun putPref(key: String, value: String?) {
        sharedEdit.putString(key, value)
        sharedEdit.apply()
    }

    fun putPref(key: String, value: Int) {
        sharedEdit.putInt(key, value)
        sharedEdit.apply()
    }

    fun putPref(key: String, value: Boolean) {
        sharedEdit.putBoolean(key, value)
        sharedEdit.apply()
    }


    fun getPrefStr(key: String): String? {
        return sharePref.getString(key, null)
    }

    fun getPrefInt(key: String): Int {
        return sharePref.getInt(key, 0)
    }

    fun getPrefBooleann(key: String): Boolean {
        return sharePref.getBoolean(key, false)
    }


    fun clearSharePref() {
        sharedEdit.clear()
        sharedEdit.apply()
    }
}


object AppLogger {

    fun d(s: String, vararg objects: Any) {
        Logger.d(s, objects)
    }

    fun e(s: String, vararg objects: Any) {
        Logger.e(s, objects)
    }

    fun e(throwable: Throwable, s: String, vararg objects: Any) {
        Logger.e(throwable, s, objects)
    }

    fun i(s: String, vararg objects: Any) {
        Logger.i(s, objects)
    }

    fun w(s: String, vararg objects: Any) {
        Logger.w(s, objects)
    }


    fun toast(s: String){
        if(BuildConfig.DEBUG)
            Toast.makeText(MyApplication.instance.applicationContext,s, Toast.LENGTH_SHORT).show()
    }
}