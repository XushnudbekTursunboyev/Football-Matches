package uz.orign.footballapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.orign.footballapp.database.entity.Entity

@Dao
interface FbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLeague(leagues: List<Entity>)

    @Query("select * from leagues")
    suspend fun getAllLeagues(): List<Entity>
}