package cs.roberto.mercadolibrelite.shared.item.domain.entity

/** Represents the reference of any product */
open class ItemReference(
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    val price: Float,
    val currency: String,
    val soldQuantity: Int,
    val availableQuantity: Int
)