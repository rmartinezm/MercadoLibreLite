package cs.roberto.mercadolibrelite.app

import android.app.Application
import cs.roberto.mercadolibrelite.app.di.initKoin

/** */
class MercadoLibreLiteApplication : Application() {

    /** */
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

}