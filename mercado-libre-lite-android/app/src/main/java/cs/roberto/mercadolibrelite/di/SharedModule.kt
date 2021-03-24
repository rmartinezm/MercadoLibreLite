package cs.roberto.mercadolibrelite.di

import org.koin.core.module.Module

/**
 * Obtains the Modules (in terms of Koin) of tools that are required by one or more shared module.
 */
fun getSharedPoolModules(): List<Module> = listOf()

/**
 * Obtains the Modules (in terms of Koin) that provides user-facing functionality.
 */
fun getSharedModules(): List<Module> = listOf()