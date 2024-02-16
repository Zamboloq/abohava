package yampi.msh.abohava.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnCityRepository {
    suspend fun addCity(name: String): Long

    suspend fun getCities(): Flow<List<String>>
}