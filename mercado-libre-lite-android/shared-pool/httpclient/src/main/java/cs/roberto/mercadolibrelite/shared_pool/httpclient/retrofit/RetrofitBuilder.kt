package cs.roberto.mercadolibrelite.shared_pool.httpclient.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/** [Retrofit] builder */
class RetrofitBuilder(private val baseUrl: String)  {

    /* HttpLogging interceptor at body level */
    private val bodyInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /* JSON converter */
    private val moshiConverterFactory = MoshiConverterFactory.create()

    /* Indies the time out */
    private val timeOut: Long = 100L

    /** Obtains the [Retrofit] instance */
    fun build(): Retrofit =
        Retrofit.Builder()
            .client(buildHttpClient())
            .baseUrl(baseUrl)
            .addConverterFactory(moshiConverterFactory)
            .build()

    /** Obtains the [OkHttpClient] instance */
    private fun buildHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .addInterceptor(bodyInterceptor)
            .build()

}