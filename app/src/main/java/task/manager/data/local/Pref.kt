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

    fun getAge(): String?{
        return pref.getString(SAVE_AGE, "7")
    }
    fun saveAge(age: String){
        pref.edit().putString(SAVE_AGE, age).apply()
    }

    fun getImage(): String? {return pref.getString(SAVE_IMAGE, "")}


    fun saveImage(image: String) = pref.edit().putString(SAVE_IMAGE, image)


    companion object {
        private const val BOARDING_SHOW = "onboarding.show"
        private const val SAVE_NAME = "savename.save"
        private const val SAVE_AGE = "save.age"
        private const val SAVE_IMAGE = "save.image"
    }

}