package yampi.msh.abohava.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yampi.msh.abohava.data.ds.db.AbohavaDatabase
import yampi.msh.abohava.data.repository.CityRepository
import yampi.msh.abohava.data.repository.SelectCityRepository
import yampi.msh.abohava.domain.repository.OnCityRepository
import yampi.msh.abohava.domain.repository.OnSelectedCityRepository

@Module
@InstallIn(SingletonComponent::class)
class CityRepositoryModule {
    @Provides
    fun provideAddCityRepository(database: AbohavaDatabase): OnCityRepository {
        return CityRepository(database = database)
    }

    @Provides
    fun provideSelectedCityRepository(database: AbohavaDatabase): OnSelectedCityRepository {
        return SelectCityRepository(database = database)
    }
}