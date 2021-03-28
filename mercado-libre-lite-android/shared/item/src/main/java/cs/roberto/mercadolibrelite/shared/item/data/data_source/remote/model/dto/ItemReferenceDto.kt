package cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemReference

/** */
@JsonClass(generateAdapter = true)
internal data class ItemReferenceDto(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "thumbnail") val thumbnailUrl: String,
    @Json(name = "price") val price: Float,
    @Json(name = "currency_id") val currency: String,
    @Json(name = "sold_quantity") val soldQuantity: Int,
    @Json(name = "available_quantity") val availableQuantity: Int,
) {

    /** */
    fun toItemReference(): ItemReference =
        ItemReference(
            id = id,
            title = title,
            thumbnailUrl = thumbnailUrl,
            price = price,
            currency = currency,
            soldQuantity = soldQuantity,
            availableQuantity = availableQuantity,
        )

}