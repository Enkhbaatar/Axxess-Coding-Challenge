package com.enkhee.codingchallenge.data.network

import com.enkhee.codingchallenge.data.network.response.GallerySearchResponse
import com.enkhee.codingchallenge.utils.AUTHORIZATION
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CodingChallengeApiService {

    /**
     *Get Gallery list
     **/
    @GET("/3/gallery/search/1")
    fun searchGallery(
        @Query("q") location:String
    ): Observable<GallerySearchResponse>

    companion object {
        /**
         * Return the current {Retrofit} instance.
         * When building, sets the endpoint and a {@link HttpLoggingInterceptor} which adds the API key as query param.
         */
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): CodingChallengeApiService {
            //Create a Interceptor for add Authorization into header
            val headerInterceptor = Interceptor { chain ->
                val newRequest = chain.request().newBuilder()
                newRequest.addHeader("Authorization", AUTHORIZATION)
                return@Interceptor chain.proceed(newRequest.build())
            }

            //Create a Interceptor for Show Log when send request and receive response.
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(logging)
                .build()

            //Create a new instance of the Rest Adapter class
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.imgur.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CodingChallengeApiService::class.java)
        }
    }
}