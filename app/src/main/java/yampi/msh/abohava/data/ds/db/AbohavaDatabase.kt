package yampi.msh.abohava.data.ds.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import yampi.msh.abohava.data.ds.db.dao.CityDao
import yampi.msh.abohava.data.ds.db.dao.SelectedCityDao
import yampi.msh.abohava.data.ds.db.model.City
import yampi.msh.abohava.data.ds.db.model.SelectedCity

@Database(entities = arrayOf(City::class, SelectedCity::class), version = 1, exportSchema = false)
abstract class AbohavaDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun selectedCityDao(): SelectedCityDao

    companion object {
        @Volatile
        private var INSTANCE: AbohavaDatabase? = null

        fun getDatabase(context: Context): AbohavaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = AbohavaDatabase::class.java,
                    name = "abohava_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}