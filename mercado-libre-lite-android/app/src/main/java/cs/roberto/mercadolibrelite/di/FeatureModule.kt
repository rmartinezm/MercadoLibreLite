package cs.roberto.mercadolibrelite.di

import cs.roberto.mercadolibrelite.presentation.search.searchModule
import org.koin.core.module.Module

/**
 * Obtains the Modules (in terms of Koin) that provides user-facing interaction.
 */
fun getFeatureModules(): List<Module> = listOf(
    searchModule,
)
