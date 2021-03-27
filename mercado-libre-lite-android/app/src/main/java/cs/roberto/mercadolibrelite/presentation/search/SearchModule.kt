package cs.roberto.mercadolibrelite.presentation.search

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/* */
val searchModule: Module = module {

    /* */
    viewModel {
        SearchViewModel()
    }

}