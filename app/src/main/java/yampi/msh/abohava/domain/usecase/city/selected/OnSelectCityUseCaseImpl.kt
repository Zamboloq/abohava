package yampi.msh.abohava.domain.usecase.city.selected

import yampi.msh.abohava.domain.repository.OnSelectedCityRepository
import javax.inject.Inject

class OnSelectCityUseCaseImpl @Inject constructor(private val onSelectedCityRepository: OnSelectedCityRepository) :
    OnSelectCityUseCase {
    override suspend fun execute(city: String) {
        onSelectedCityRepository.selectCity(city = city)
    }
}