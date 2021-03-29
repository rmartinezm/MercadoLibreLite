package cs.roberto.mercadolibrelite.shared.item

import cs.roberto.mercadolibrelite.shared.item.data.ItemDataSource
import cs.roberto.mercadolibrelite.shared.item.data.ItemRepositoryImpl
import cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.ItemApiService
import cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.ItemDataSourceRemote
import cs.roberto.mercadolibrelite.shared.item.domain.ItemRepository
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_item_description.GetItemDescriptionUseCase
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_details.GetItemDetailsUseCase
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesUseCase
import cs.roberto.mercadolibrelite.shared.item.presentation.get_item_details.GetItemDetails
import cs.roberto.mercadolibrelite.shared.item.presentation.get_item_details.GetItemDetailsImpl
import cs.roberto.mercadolibrelite.shared.item.presentation.get_items_references.GetItemsReferences
import cs.roberto.mercadolibrelite.shared.item.presentation.get_items_references.GetItemsReferencesImpl
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

/* Module in terms or [Koin] that contains the Item data flow classes creation methods */
val itemModule: Module = module {

    /** PRESENTATION **/
    single<GetItemsReferences> {
        GetItemsReferencesImpl(
            getItemsReferencesUseCase = get(),
        )
    }
    factory<GetItemDetails> {
        GetItemDetailsImpl(
            getItemDetailsUseCase = get(),
        )
    }

    /** USE CASES **/
    factory {
        GetItemsReferencesUseCase(
            itemRepository = get(),
        )
    }
    factory {
        GetItemDescriptionUseCase(
            itemRepository = get(),
        )
    }
    factory {
        GetItemDetailsUseCase(
            itemRepository = get(),
            getItemDescriptionUseCase = get(),
        )
    }

    /** REPOSITORY **/
    single<ItemRepository> {
        ItemRepositoryImpl(
            itemDataSource = get(),
        )
    }

    /** DATA SOURCE **/
    single<ItemDataSource> {
        ItemDataSourceRemote(
            itemApiService = get(),
        )
    }

    /** API SERVICE **/
    single<ItemApiService> {
        get<Retrofit>().create(ItemApiService::class.java)
    }

}