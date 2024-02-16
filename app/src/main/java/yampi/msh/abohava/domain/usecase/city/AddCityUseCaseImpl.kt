package yampi.msh.abohava.domain.usecase.city

import yampi.msh.abohava.domain.repository.OnCityRepository
import javax.inject.Inject

class AddCityUseCaseImpl @Inject constructor(private val onCityRepository: OnCityRepository) : AddCityUseCase {
    override suspend fun execute(city: String): Long = onCityRepository.addCity(name = city)
}