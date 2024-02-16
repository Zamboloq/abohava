package yampi.msh.abohava.domain.usecase.city

import kotlinx.coroutines.flow.Flow

interface GetCitiesUseCase {
    suspend fun execute(): Flow<List<String>>
}