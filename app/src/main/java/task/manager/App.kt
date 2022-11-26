package task.manager

import android.app.Application
import androidx.room.Room
import task.manager.data.local.db.AppDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "task-db-name"
        ).allowMainThreadQueries().build()

    }

    companion object{
        lateinit var db: AppDatabase
    }
}