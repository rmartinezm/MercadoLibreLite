package cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/** */
@JsonClass(generateAdapter = true)
internal class PictureDto(
    @Json(name = "secure_url") val url: String,
)