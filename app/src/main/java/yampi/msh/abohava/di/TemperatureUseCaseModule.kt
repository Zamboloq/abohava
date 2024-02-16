package yampi.msh.abohava.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yampi.msh.abohava.domain.model.ForecastTemperatureModel
import yampi.msh.abohava.domain.model.TemperatureModel
import yampi.msh.abohava.domain.repository.TemperatureRepository
import yampi.msh.abohava.domain.usecase.temperature.GetCurrentTemperatureUseCase
import yampi.msh.abohava.domain.usecase.temperature.GetForecastTemperatureUseCase
import yampi.msh.abohava.domain.usecase.temperature.TemperatureUseCase

@Module
@InstallIn(SingletonComponent::class)
class TemperatureUseCaseModule {
    @Provides
    fun provideGetCurrentTemperatureUseCase(temperatureRepository: TemperatureRepository<TemperatureModel>): TemperatureUseCase<TemperatureModel> {
        return GetCurrentTemperatureUseCase(temperatureRepository = temperatureRepository)
    }

    @Provides
    fun provideGetForecastTemperatureUseCase(temperatureRepository: TemperatureRepository<ForecastTemperatureModel>): TemperatureUseCase<ForecastTemperatureModel> {
        return GetForecastTemperatureUseCase(temperatureRepository = temperatureRepository)
    }
}