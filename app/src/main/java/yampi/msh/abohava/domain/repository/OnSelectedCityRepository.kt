package yampi.msh.abohava.domain.repository

interface OnSelectedCityRepository {

    suspend fun selectCity(city: String)

    suspend fun getSelectedCity(): String
}