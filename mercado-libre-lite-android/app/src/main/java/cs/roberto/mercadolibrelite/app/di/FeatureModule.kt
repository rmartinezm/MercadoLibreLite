package cs.roberto.mercadolibrelite.app.di

import cs.roberto.mercadolibrelite.app.feature.product_details.productDetailsModule
import cs.roberto.mercadolibrelite.app.feature.products.productsModule
import cs.roberto.mercadolibrelite.app.feature.search.searchModule
import org.koin.core.module.Module

/**
 * Obtains the Modules (in terms of Koin) that provides user-facing interaction.
 */
fun getFeatureModules(): List<Module> = listOf(
    searchModule,
    productsModule,
    productDetailsModule,
)
