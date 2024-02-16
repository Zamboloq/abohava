package yampi.msh.abohava.data.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import yampi.msh.abohava.data.ds.db.AbohavaDatabase
import yampi.msh.abohava.data.ds.db.model.City
import yampi.msh.abohava.domain.repository.OnCityRepository
import javax.inject.Inject

class CityRepository @Inject constructor(private val database: AbohavaDatabase) : OnCityRepository {

    @WorkerThread
    override suspend fun addCity(name: String): Long {
        val city = City(name = name)
        return database.cityDao().insert(city)
    }

    @WorkerThread
    override suspend fun getCities(): Flow<List<String>> = database.cityDao().getCities()
}