package cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references

/** */
data class GetItemsReferencesParams(
    val query: String,
    val page: Int,
    val pageSize: Int,
)