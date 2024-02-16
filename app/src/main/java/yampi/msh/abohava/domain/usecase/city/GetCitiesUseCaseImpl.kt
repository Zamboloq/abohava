package yampi.msh.abohava.domain.usecase.city

import kotlinx.coroutines.flow.Flow
import yampi.msh.abohava.domain.repository.OnCityRepository
import javax.inject.Inject

class GetCitiesUseCaseImpl @Inject constructor(private val onCityRepository: OnCityRepository) : GetCitiesUseCase {
    override suspend fun execute(): Flow<List<String>> = onCityRepository.getCities()
}