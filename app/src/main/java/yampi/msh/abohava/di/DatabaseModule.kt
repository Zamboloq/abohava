package yampi.msh.abohava.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import yampi.msh.abohava.data.ds.db.AbohavaDatabase

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AbohavaDatabase = AbohavaDatabase.getDatabase(context)
}