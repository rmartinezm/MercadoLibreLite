/** */
object Koin {

    /** */
    private object Version {

        /* @see [https://github.com/InsertKoinIO/koin/releases/tag/2.2.2] */
        const val androidViewModel: String = "2.2.2"

    }

    /* @see [https://insert-koin.io/docs/reference/koin-android/viewmodel] */
    const val androidViewModel = "org.koin:koin-android-viewmodel:${Version.androidViewModel}"

}