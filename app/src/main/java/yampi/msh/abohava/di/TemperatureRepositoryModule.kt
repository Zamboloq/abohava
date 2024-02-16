package yampi.msh.abohava.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yampi.msh.abohava.data.ds.api.AbohavaApiService
import yampi.msh.abohava.data.repository.CurrentTemperatureRepository
import yampi.msh.abohava.data.repository.ForecastTemperatureRepository
import yampi.msh.abohava.domain.model.ForecastTemperatureModel
import yampi.msh.abohava.domain.model.TemperatureModel
import yampi.msh.abohava.domain.repository.TemperatureRepository

@Module
@InstallIn(SingletonComponent::class)
class TemperatureRepositoryModule {

    @Suppress("FunctionName")
    @Provides
    fun provideCurrentTemperatureRepository(abohavaApiService: AbohavaApiService): TemperatureRepository<TemperatureModel> {
        return CurrentTemperatureRepository<TemperatureModel>(abohavaApiService = abohavaApiService)
    }

    @Suppress("FunctionName")
    @Provides
    fun provideForecastTemperatureRepository(abohavaApiService: AbohavaApiService): TemperatureRepository<ForecastTemperatureModel> {
        return ForecastTemperatureRepository<ForecastTemperatureModel>(abohavaApiService = abohavaApiService)
    }
}