package yampi.msh.abohava.data.ds.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import yampi.msh.abohava.data.ds.db.model.City

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: City): Long

    @Query("SELECT * FROM city_table ORDER BY city ASC")
    fun getCities(): Flow<List<String>>

    @Query("DELETE FROM city_table")
    suspend fun deleteAll()

    @Query("DELETE FROM city_table WHERE city = :city")
    suspend fun deleteCity(city: String)
}