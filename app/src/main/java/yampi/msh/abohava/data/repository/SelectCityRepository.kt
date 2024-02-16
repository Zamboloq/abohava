package yampi.msh.abohava.data.repository

import android.util.Log
import yampi.msh.abohava.data.ds.db.AbohavaDatabase
import yampi.msh.abohava.data.ds.db.model.SelectedCity
import yampi.msh.abohava.domain.repository.OnSelectedCityRepository
import javax.inject.Inject

class SelectCityRepository @Inject constructor(val database: AbohavaDatabase) : OnSelectedCityRepository {
    override suspend fun selectCity(city: String) {
        val selectCityResult = database.selectedCityDao().insertOrUpdate(city = SelectedCity(city = city))
        Log.d("SelectCityRepository", "selectCityResult: $selectCityResult")
    }

    override suspend fun getSelectedCity(): String {
        return database.selectedCityDao().getSelectedCity()
    }
}