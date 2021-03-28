package cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.model.dto.ItemReferenceDto

/** */
@JsonClass(generateAdapter = true)
internal data class GetItemsReferencesHttpResponse(
    @Json(name = "results") val itemsReferencesDto: List<ItemReferenceDto>,
)
