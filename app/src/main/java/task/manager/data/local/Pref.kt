package task.manager.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class Pref(private val context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences("pref_name", MODE_PRIVATE)

    fun isBoardingShow(): Boolean {
        return pref.getBoolean(BOARDING_SHOW, false)
    }

    fun saveShowBoarding(isShow: Boolean) {
        pref.edit().putBoolean(BOARDING_SHOW, isShow).apply()
    }

    fun getName(): String?{
        return pref.getString(SAVE_NAME, "")
    }
    fun saveName(name: String){
        pref.edit().putString(SAVE_NAME, name).apply()
    }

    companion object {
        private const val BOARDING_SHOW = "onboarding.show"
        private const val SAVE_NAME = "savename.save"
    }

}