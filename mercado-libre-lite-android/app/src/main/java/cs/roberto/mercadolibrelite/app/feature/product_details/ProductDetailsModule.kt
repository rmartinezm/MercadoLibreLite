package cs.roberto.mercadolibrelite.app.feature.product_details

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/* */
val productDetailsModule: Module = module {

    /* */
    viewModel { (itemId: String) ->
        ProductDetailsViewModel(
            itemId,
            getItemDetails = get(),
        )
    }

}