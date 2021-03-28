package cs.roberto.mercadolibrelite.shared.item.domain.entity

/** */
class ItemDetails(
    id: String,
    title: String,
    thumbnailUrl: String,
    price: Float,
    currency: String,
    soldQuantity: Int,
    availableQuantity: Int,
    val permalink: String,
    val acceptsMercadoPago: Boolean,
    var description: String,
    val picturesUrls: List<String>,
) : ItemReference(id, title, thumbnailUrl, price, currency, soldQuantity, availableQuantity)