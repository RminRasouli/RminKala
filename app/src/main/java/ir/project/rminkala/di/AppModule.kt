package ir.project.rminkala.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.project.rminkala.data.local.LocalDataSource
import ir.project.rminkala.data.local.LocalDataSourcePr
import ir.project.rminkala.data.local.db.ProductDataBase
import ir.project.rminkala.data.remote.RemoteDataSource
import ir.project.rminkala.data.remote.RemoteDataSourcePr
import ir.project.rminkala.data.remote.api.StoreApiService
import ir.project.rminkala.util.Constance.BASE_URL
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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
    ): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providesService(retrofit: Retrofit): StoreApiService =
        retrofit.create(StoreApiService::class.java)

    @Provides
    fun providesLocalDataSource(provide: ProductDataBase): LocalDataSource {
        return LocalDataSourcePr(provide)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ProductDataBase =
        Room.databaseBuilder(app, ProductDataBase::class.java, "Product_ShoppingCard")
            .build()
}
