package cs.roberto.mercadolibrelite.shared.item.data.data_source.remote

import cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.model.GetItemsReferencesHttpResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/** */
internal interface ItemApiService {

    /** */
    @GET(URL.GET_ITEMS_REFERENCES)
    suspend fun getItemsReferences(
        @Query("q") query: String,
        @Query("offset") page: Int,
        @Query("limit") pageSize: Int,
    ): Response<GetItemsReferencesHttpResponse>

    /** */
    private object URL {

        /* */
        const val GET_ITEMS_REFERENCES = "search"

    }


}