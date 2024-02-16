package yampi.msh.abohava.domain.usecase.city

interface AddCityUseCase {
    suspend fun execute(city: String): Long
}