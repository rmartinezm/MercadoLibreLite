package cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class ItemDescriptionDto(
    @Json(name = "plain_text") val description: String,
)
