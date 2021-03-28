package cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references

import cs.roberto.mercadolibrelite.shared.item.domain.entity.ItemReference

/** */
data class GetItemsReferencesResponse(
    val itemsReferences: List<ItemReference>
)
