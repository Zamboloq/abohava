package yampi.msh.abohava.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yampi.msh.abohava.domain.repository.OnCityRepository
import yampi.msh.abohava.domain.repository.OnSelectedCityRepository
import yampi.msh.abohava.domain.usecase.city.AddCityUseCase
import yampi.msh.abohava.domain.usecase.city.AddCityUseCaseImpl
import yampi.msh.abohava.domain.usecase.city.GetCitiesUseCase
import yampi.msh.abohava.domain.usecase.city.GetCitiesUseCaseImpl
import yampi.msh.abohava.domain.usecase.city.selected.GetSelectedCityUseCase
import yampi.msh.abohava.domain.usecase.city.selected.GetSelectedCityUseCaseImpl
import yampi.msh.abohava.domain.usecase.city.selected.OnSelectCityUseCase
import yampi.msh.abohava.domain.usecase.city.selected.OnSelectCityUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
class CityUseCaseModule {

    @Provides
    fun provideAddCityUseCase(onCityRepository: OnCityRepository): AddCityUseCase = AddCityUseCaseImpl(onCityRepository)

    @Provides
    fun provideGetCitiesUseCase(onCityRepository: OnCityRepository): GetCitiesUseCase =
        GetCitiesUseCaseImpl(onCityRepository)

    @Provides
    fun provideOnSelectCityUseCase(onSelectedCityRepository: OnSelectedCityRepository): OnSelectCityUseCase =
        OnSelectCityUseCaseImpl(onSelectedCityRepository)

    @Provides
    fun provideGetSelectedCityUseCase(onSelectedCityRepository: OnSelectedCityRepository): GetSelectedCityUseCase =
        GetSelectedCityUseCaseImpl(onSelectedCityRepository)
}