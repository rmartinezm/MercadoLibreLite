package cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import cs.roberto.mercadolibrelite.core.stringktx.empty
import cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.model.dto.ItemReferenceDto
import cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.model.dto.PictureDto
import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemDetails

/** */
@JsonClass(generateAdapter = true)
internal data class GetItemDetailsHttpResponse(
    @Json(name = "id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "secure_thumbnail") val thumbnailUrl: String,
    @Json(name = "price") val price: Float,
    @Json(name = "currency_id") val currency: String,
    @Json(name = "sold_quantity") val soldQuantity: Int,
    @Json(name = "available_quantity") val availableQuantity: Int,
    @Json(name = "permalink") val permalink: String,
    @Json(name = "accepts_mercadopago") val acceptsMercadoPago: Boolean,
    @Json(name = "pictures") val picturesDto: List<PictureDto>,
) {

    /** */
    fun toItemDetails(): ItemDetails =
        ItemDetails(
            id = id,
            title = title,
            thumbnailUrl = thumbnailUrl,
            price = price,
            currency = currency,
            soldQuantity = soldQuantity,
            availableQuantity = availableQuantity,
            permalink = permalink,
            acceptsMercadoPago = acceptsMercadoPago,
            description = String.empty,
            picturesUrls = picturesDto.map { it.url }
        )

}