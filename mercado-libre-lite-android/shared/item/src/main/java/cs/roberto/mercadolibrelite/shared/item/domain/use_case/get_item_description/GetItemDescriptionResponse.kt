package cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_item_description

/** Response emitted at GetItemDescription data flow done. */
data class GetItemDescriptionResponse(
    val description: String,
)