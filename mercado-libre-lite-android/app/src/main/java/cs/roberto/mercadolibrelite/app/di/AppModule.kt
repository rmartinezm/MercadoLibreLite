package cs.roberto.mercadolibrelite.app.di

import cs.roberto.mercadolibrelite.app.MercadoLibreLiteApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Initialize the Koin instance with the modules associated to the project.
 */
fun MercadoLibreLiteApplication.initKoin() {
    val sharedModules = getSharedPoolModules() + getSharedModules()
    val featureModules = getFeatureModules()
    val modules = sharedModules + featureModules
    startKoin {
        androidLogger()
        androidContext(applicationContext)
        modules(modules)
    }
}
