package yampi.msh.abohava.data.ds.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import yampi.msh.abohava.data.ds.db.model.SelectedCity

@Dao
interface SelectedCityDao {
    @Query("SELECT * FROM SELECTED_CITY_TABLE LIMIT 1 OFFSET 0")
    suspend fun getSelectedCity(): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: SelectedCity): Long

    @Update
    suspend fun update(city: SelectedCity): Int

    @Query("DELETE FROM SELECTED_CITY_TABLE")
    suspend fun deleteAll()

    @Transaction
    suspend fun insertOrUpdate(city: SelectedCity): Long {
        deleteAll()
        val id = insert(city)
        return if (id == -1L) {
            update(city)
            city.hashCode().toLong()
        } else {
            id
        }
    }
}