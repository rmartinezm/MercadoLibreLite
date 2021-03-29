package cs.roberto.mercadolibrelite.app.feature.products

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/* */
val productsModule: Module = module {

    /* */
    viewModel { (query: String) ->
        ProductsViewModel(query)
    }

}