package cs.roberto.mercadolibrelite.shared_pool.httpclient.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/* */
class RetrofitBuilder(private val baseUrl: String)  {

    /* */
    private val bodyInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    /* */
    private val moshiConverterFactory = MoshiConverterFactory.create()

    /* */
    private val timeOut: Long = 100L

    /** */
    fun build(): Retrofit =
        Retrofit.Builder()
            .client(buildHttpClient())
            .baseUrl(baseUrl)
            .addConverterFactory(moshiConverterFactory)
            .build()

    /** */
    private fun buildHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .addInterceptor(bodyInterceptor)
            .build()

}