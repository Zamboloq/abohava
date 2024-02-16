package yampi.msh.abohava.domain.usecase.city.selected

import yampi.msh.abohava.domain.repository.OnSelectedCityRepository
import javax.inject.Inject

class GetSelectedCityUseCaseImpl @Inject constructor(private val onSelectedCityRepository: OnSelectedCityRepository) :
    GetSelectedCityUseCase {
    override suspend fun execute(): String = onSelectedCityRepository.getSelectedCity()
}