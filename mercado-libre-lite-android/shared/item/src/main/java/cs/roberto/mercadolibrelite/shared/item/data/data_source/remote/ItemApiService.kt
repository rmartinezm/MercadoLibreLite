package cs.roberto.mercadolibrelite.shared.item.data.data_source.remote

import cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.model.GetItemDetailsHttpResponse
import cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.model.GetItemsReferencesHttpResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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
    @GET(URL.GET_ITEM_DESCRIPTION)
    suspend fun getItemDescription(
        @Path("item_id") itemId: String,
    ): Response<Any>

    /** */
    @GET(URL.GET_ITEM_DETAILS)
    suspend fun getItemDetails(
        @Path("item_id") itemId: String,
    ): Response<GetItemDetailsHttpResponse>

    /** */
    private object URL {

        /* */
        const val GET_ITEMS_REFERENCES = "sites/MLM/search"

        /* */
        const val GET_ITEM_DESCRIPTION = "items/{item_id}/descriptions"

        /* */
        const val GET_ITEM_DETAILS = "items/{item_id}"

    }


}