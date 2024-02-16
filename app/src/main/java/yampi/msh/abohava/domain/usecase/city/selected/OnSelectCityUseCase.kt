package yampi.msh.abohava.domain.usecase.city.selected

interface OnSelectCityUseCase {
    suspend fun execute(city: String)
}