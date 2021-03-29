package cs.roberto.mercadolibrelite.shared_pool.httpclient.retrofit

import retrofit2.HttpException
import retrofit2.Response

/** Obtains the body response of any apiCall in body format. */
@Throws(HttpException::class, Exception::class)
suspend fun <T> retrofitApiCall(apiCall: suspend () -> Response<T>) : T {
    val response = apiCall()
    return if(response.isSuccessful)
        response.body()!!
    else throw HttpException(response)
}