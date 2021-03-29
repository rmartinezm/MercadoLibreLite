package cs.roberto.mercadolibrelite.shared_pool.httpclient

import cs.roberto.mercadolibrelite.shared_pool.httpclient.retrofit.RetrofitBuilder
import org.koin.dsl.module
import retrofit2.Retrofit

/* Module in terms or [Koin] that contains the HttpClient creation methods */
val httpClientModule = module {

    /** RETROFIT **/
    single<Retrofit> {
        RetrofitBuilder(
            baseUrl = BuildConfig.API_BASE_URL,
        ).build()
    }

}