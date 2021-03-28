package cs.roberto.mercadolibrelite.shared.item

import cs.roberto.mercadolibrelite.shared.item.data.ItemDataSource
import cs.roberto.mercadolibrelite.shared.item.data.ItemRepositoryImpl
import cs.roberto.mercadolibrelite.shared.item.data.data_source.dummy.ItemDataSourceDummy
import cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.ItemApiService
import cs.roberto.mercadolibrelite.shared.item.data.data_source.remote.ItemDataSourceRemote
import cs.roberto.mercadolibrelite.shared.item.domain.ItemRepository
import cs.roberto.mercadolibrelite.shared.item.domain.use_case.get_items_references.GetItemsReferencesUseCase
import cs.roberto.mercadolibrelite.shared.item.presentation.get_items_references.GetItemsReferences
import cs.roberto.mercadolibrelite.shared.item.presentation.get_items_references.GetItemsReferencesImpl
import cs.roberto.mercadolibrelite.shared_pool.httpclient.retrofit.RetrofitBuilder
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

/* */
val itemModule: Module = module {

    /** PRESENTATION **/
    single<GetItemsReferences> {
        GetItemsReferencesImpl(
            getItemsReferencesUseCase = get(),
        )
    }

    /** USE CASES **/
    factory {
        GetItemsReferencesUseCase(
            itemRepository = get(),
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