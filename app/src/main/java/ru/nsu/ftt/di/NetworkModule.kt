package ru.nsu.ftt.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.nsu.fit.auth.data.auth.AuthService
import ru.nsu.fit.auth.data.register.RegisterService
import ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.ScheduleService
import ru.nsu.fit.common.ApiSettings
import ru.nsu.fit.common.ApiSettings.Companion.TIME_OUT_DELAY
import ru.nsu.ftt.utils.AuthorizationInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class Authorization

@Qualifier
annotation class NonAuthorization

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideApiSettings(): ApiSettings {
        return ApiSettings()
    }

    @Provides
    @Authorization
    fun provideOkHttpClientAuthorization(
        authInterceptor: AuthorizationInterceptor
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(TIME_OUT_DELAY, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_DELAY, TimeUnit.SECONDS)
            .build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideJsonDecoder(): Json {
        return Json {
            ignoreUnknownKeys = true
            explicitNulls = false
            isLenient = true
            decodeEnumsCaseInsensitive = true
        }
    }


    @Provides
    @Authorization
    fun provideRetrofitAuthorization(
        @Authorization okHttpClient: OkHttpClient,
        apiSettings: ApiSettings,
        json: Json
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        val converterFactory = json.asConverterFactory(contentType)
        return Retrofit.Builder()
            .baseUrl(apiSettings.baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()

    }

    @Provides
    @NonAuthorization
    fun provideOkHttpClientNonAuthorization(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(TIME_OUT_DELAY, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_DELAY, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @NonAuthorization
    fun provideRetrofitNonAuthorization(
        @NonAuthorization okHttpClient: OkHttpClient,
        apiSettings: ApiSettings,
        json: Json
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        val converterFactory = json.asConverterFactory(contentType)
        return Retrofit.Builder()
            .baseUrl(apiSettings.baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideScheduleService(@Authorization retrofit: Retrofit): ScheduleService {
        return retrofit.create(ScheduleService::class.java)
    }

    @Provides
    fun provideAuthService(@NonAuthorization retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    fun provideRegisterService(@NonAuthorization retrofit: Retrofit): RegisterService {
        return retrofit.create(RegisterService::class.java)
    }
}