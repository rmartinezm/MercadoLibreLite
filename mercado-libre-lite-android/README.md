# Mercado Libre Lite
##### Author: Roberto Martínez Medina

Versión Lite de la aplicación Mercado Libre que te permite realizar la búsqueda de productos y ver el detalle de cada uno haciendo uso de [API oficial de Mercado Libre](https://developers.mercadolibre.com.mx/es_ar/items-y-busquedas).
Mercado Libre Lite implementa algunas de las biblioecas y tecnologías más utilizadas en el mundo de desarrollo Anroid. A continuación se listan algunas de ellas:
* Kotlin (v1.4.20) como lenguaje de programación.
* Kotlin Coroutines (v1.3.7).
* Arquitectura Modular dividiendo el proyecto por recursos Feature y Shared.
* Directorio buildSrc para manejo de Dependencias y Versiones.
* Koin (v2.2.2) como service locator.
* Bibliotecas Android Jetpack: Databinding, NavigationComponent y Paging.
* Retrofit como Cliente Http.
* MVVM + Clean Architecture (UseCase, Repository y DataSource)

El proyecto Mercado Libre Lite hace uso de 5 directorios distintores de recursos:
* app: Maneja la responsabilidad de configuración general y la capa de Presentation a través de separación porr features.
* feature-pool: Pool de módulos requeridos por la capa de Feature dentro de application.
* shared: Definen funcionalidad de cara al usuario (definición de flujo basado en UseCases).
* shared-pool: Pool de módulos requeridos por uno o más módulos tipo shared.
* core: Módulos Kotlin que definen interacción global.

### Algunas referencias para mayor y mejor comprensión de conseptos utilizados.
- [Intro to App Modularization](https://proandroiddev.com/intro-to-app-modularization-42411e4c421e)
- [Build a Modular Android App Architecture](https://proandroiddev.com/build-a-modular-android-app-architecture-25342d99de82)
- [Gradle dependency management with Kotlin (buildSrc)](https://proandroiddev.com/gradle-dependency-management-with-kotlin-94eed4df9a28)
- [The Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Why you need Use Cases/Interactors](https://proandroiddev.com/why-you-need-use-cases-interactors-142e8a6fe576)
- [Either](https://arrow-kt.io/docs/apidocs/arrow-core-data/arrow.core/-either/#either)
- [Kotlin Delegation](https://kotlinlang.org/docs/delegation.html)

#### Bibliotecas externas utilizadas
- [Glide](https://github.com/bumptech/glide) como decodificador de imágenes.
- [AndroidImageSlider](https://github.com/smarteist/Android-Image-Slider) como integrador del patrón de diseño Carrousel para mostrar las imágenes del detalle de un producto.
- [LottieFiles](https://github.com/LottieFiles/lottie-android) como integrador de animaciones formato JSON.