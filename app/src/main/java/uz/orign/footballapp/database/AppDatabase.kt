package uz.orign.footballapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.orign.footballapp.database.dao.FbDao
import uz.orign.footballapp.database.entity.Entity

@Database(entities = [Entity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun fbDao(): FbDao

    companion object {
        private var appDatabase: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "my_db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return appDatabase!!
        }
    }
}