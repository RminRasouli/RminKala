package ir.project.rminkala.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.project.rminkala.data.local.db.ProductDataBase
import ir.project.rminkala.data.remote.api.StoreApiService
import ir.project.rminkala.data.remote.RemoteDataSource
import ir.project.rminkala.data.remote.RemoteDataSourcePr
import ir.project.rminkala.util.Constance.BASE_URL
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @IoDispatcher
    fun provideDispatchers(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun providesRemoteDataSource(provide: StoreApiService): RemoteDataSource {
        return RemoteDataSourcePr(provide)
    }

    @Singleton
    @Provides
    fun jsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun providesRetroFit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(gsonConverterFactory).build()

    @Singleton
    @Provides
    fun providesService(retrofit: Retrofit): StoreApiService =
        retrofit.create(StoreApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : ProductDataBase =
        Room.databaseBuilder(app, ProductDataBase::class.java, "Product_ShoppingCard")
            .build()
}
