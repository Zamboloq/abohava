package yampi.msh.abohava.domain.usecase.city.selected


interface GetSelectedCityUseCase {
    suspend fun execute(): String
}