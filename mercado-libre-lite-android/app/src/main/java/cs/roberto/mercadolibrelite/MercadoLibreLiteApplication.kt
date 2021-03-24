package cs.roberto.mercadolibrelite

import android.app.Application
import cs.roberto.mercadolibrelite.di.initKoin

/** */
class MercadoLibreLiteApplication : Application() {

    /** */
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

}