package yampi.msh.abohava.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager {
    val DEVICE_ID_KEY = "DEVICE_ID_KEY"
    lateinit var sharedPreference: SharedPreferences
    var editor = sharedPreference.edit()

    private constructor(context: Context) {
        instance = yampi.msh.abohava.util.SharedPreferencesManager(context)
        sharedPreference = context.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
    }


    fun getDeviceId(): String {
        return sharedPreference.getString(DEVICE_ID_KEY, "").orEmpty()
    }

    fun setDeviceId(deviceId: String) {
        editor.putString(DEVICE_ID_KEY, deviceId).apply()
    }


    fun printDeviceId(): String {
        val deviceId = getDeviceId()
        println("Device ID: $deviceId")
        return deviceId
    }

    fun saveDeviceId(deviceId: String) {
        setDeviceId(deviceId)
    }

    companion object {
        lateinit var instance: SharedPreferencesManager
        fun getInstance(context: Context): SharedPreferencesManager {
            if (instance == null) {
                yampi.msh.abohava.util.SharedPreferencesManager(context)
            }
            return instance
        }
    }
}