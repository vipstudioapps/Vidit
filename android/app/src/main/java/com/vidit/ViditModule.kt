package com.vidit

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import android.content.SharedPreferences
import android.content.Context

class ViditModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    private val sharedPreferences: SharedPreferences = 
        reactContext.getSharedPreferences("vidit_prefs", Context.MODE_PRIVATE)

    override fun getName(): String = "ViditModule"

    @ReactMethod
    fun setContactVideo(contactId: String, videoUri: String, promise: Promise) {
        try {
            sharedPreferences.edit().putString("contact_$contactId", videoUri).apply()
            promise.resolve("Video assigned to contact")
        } catch (e: Exception) {
            promise.reject("ERROR", e.message)
        }
    }

    @ReactMethod
    fun getContactVideo(contactId: String, promise: Promise) {
        try {
            val videoUri = sharedPreferences.getString("contact_$contactId", null)
            promise.resolve(videoUri)
        } catch (e: Exception) {
            promise.reject("ERROR", e.message)
        }
    }

    @ReactMethod
    fun setUseVideoAudio(useAudio: Boolean, promise: Promise) {
        try {
            sharedPreferences.edit().putBoolean("use_video_audio", useAudio).apply()
            promise.resolve("Audio setting updated")
        } catch (e: Exception) {
            promise.reject("ERROR", e.message)
        }
    }

    @ReactMethod
    fun getUseVideoAudio(promise: Promise) {
        try {
            val useAudio = sharedPreferences.getBoolean("use_video_audio", true)
            promise.resolve(useAudio)
        } catch (e: Exception) {
            promise.reject("ERROR", e.message)
        }
    }
}