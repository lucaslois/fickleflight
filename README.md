# FickleFlight

## Colaboradores
- Lucas Lois - https://github.com/lucaslois
- Martin Soengas - https://github.com/martinsoengas
- Ivan Gimenez - https://github.com/gimenezi
- Ibrian Giorgio - https://github.com/ibriangithub
- Miguel Tadakuma - https://github.com/mtadakuma

### En el caso que se pida extender la app para otros tipos de funcionalidades, como hoteles, puntos turísticos o paquetes ¿la app es flexible? ¿Qué cambios realizaría?
Si, la app es flexible y podría agregarse una nueva sección como RecyclerView en el Home que tenga un listado de Hoteles, Puntos Turísticos o Paquetes, al cual se accedería a su vista detallada. Simplemente deberíamos agregar las nuevas vistas a los NAV. Incluso podríamos directamente desde la búsqueda de vuelos, sumar una búsqueda para la fecha del vuelo de hoteles para la locación elegida, redireccionar a los puntos turísticos más atractivos del destino seleccionado, etc. 

Por otro lado, si bien no sería tan sencillo como el punto anterior, podríamos modificar la temática entera de la app y reutilizar la mayor parte de sus componentes.

### ¿Qué tipo de arquitectura usaron y por qué? ¿Podría mejorarla?
Se intentó seguir la arquitectura MVVM en la mayoría de la aplicación. Quedaron ciertos puntos de la aplicación (como la RecyclerView de Offers) que referencian los objetos de vista por findViewById y el acceso a la capa de datos desde el Fragment. Se utilizó un Activity principal que contiene todo el flujo de la aplicación, separando cada una de sus pantallas con distintos Fragments.

Como puntos de mejora podemos identificar los siguientes
- Migrar los componentes restantes para que utilicen ViewModels, ViewBinding y LiveData.
- Implementar un Repository Pattern para no acoplar a los ViewModels directamente con los servicios de acceso a datos.
- Utilizar inyección de dependencias para quitarle la responsabilidad a los componentes de resolver sus propias dependencias.
- Implementar persistencia para guardar preferencias de usuario (como el DarkMode) en las shared preferences.

### ¿Qué mejoras detectan que podrían realizarle a la app?
- Pulir el responsive de la app
- Agregar un login de usuario. Así como también una sección para vendedores.
- Mejorar la UI, para lograr una mejor atracción al usuario, con colores e imágenes.
- Podría mejorar el comportamiento y performance de la barra de navegación superior.

### ¿Qué manejo de errores harían? ¿dónde los contemplan a nivel código?
El manejo de errores se harían dentro de la capa de ViewModel, cosa de poder mostrarle al usuario el error correspondiente, por ejemplo renderizando un toast.
En el caso de uso de persistencia para Favoritos, ¿que estrategia sugieren?
Al ser una persistencia sencilla podríamos utilizar Firebase integrando con el SDK para Kotlin.

### Si la tendríamos que convertir a Español y conservar el Inglés, qué estrategia utilizaría para extenderla. Y si necesitamos agregar otros idiomas?
Utilizaremos el Translation Editor, el cual define todos los strings que van a utilizarse en un recurso strings.xml. Esto permite crear archivos específicos para cada idioma. De esta manera

Se desacopla el código del lenguaje, ya que cada componente referencia a un recurso de texto, y no al texto literal.

En caso de querer extender el modelo y agregar más idiomas, bastaría con crear nuevos archivos de idioma y definir el conjunto de strings específico para ese idioma.

