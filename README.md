# Activos Fijos ASD #

Proyecto practico que contiene una API RESTFul básica para administrar los activos fijos.

### Requerimientos ###

* OpenJDK 17.x LTS
* Apacha Maven 3.3.x o superior

### Introducción ###

Los servicios son desarrollados utilizando Spring Boot + Maven. Las pruebas unitarias deben ser realizadas utilizando JUnit5 y mockito. Por favor referirse a las páginas de ayuda y demás documentación del proyecto Spring Boot y del proyecto Apache Maven.

Para el despliegue de la aplicación sea por docker o por otro medio, tener en cuenta la configuración de las variables de ambiente:

LOGGING_LEVEL_ORG_APACHE_KAFKA= Dirección IP del servicio de base de datos.

### Configuraciones de ambientes y entorno de desarrollo ###

El proyecto viene configurado con dos tipos de ambientes un ambiente de desarrollo y un ambiente productivo, ambos poseen configuraciones separadas en los siguientes archivos de propiedades los cuales se encuentran en la carpeta /src/resources:

-	application-dev.properties:
-	application.prod.properties:

Para desarrollo se debe editar el archivo application-dev.properties y configurar las URL de los entornos de desarrollo como por ejemplo la conexión a la base de datos.

En caso de que sea necesario agregar una nueva propiedad de configuración se debe adicionar a los archivos application-dev.properties y application-prod.properties. Para el caso del application-prod es necesario tener en cuenta que las configuraciones se realizar como variables de entorno para que estas sean configuradas en el servidor.

### Despliegue para Pruebas Funcionales ###

Como requisito para desplegar los componentes es necesario tener instalado Docker y Docker-Compose

**Componente Base Datos**

El componente de servicios Api RestFul hace uso de una base de datos relacional, que para su funcionamiento se requiere montar
un contenedor que se configurado y parametrizado a partir de una imagen de PostgreSQL v-13; y que contiene la base de BaseDatos
pre-diligenciada.

Para ello se deben seguir los siguientes pasos:

1. Descargar y clonar el repositorio.
2. En el respositorio, ingresar al directorio "/PruebaTecnicaAsd_2023/Docker/BaseDatos/compose", desde una terminal.
3. Ejecutar el comando: docker-compose build
4. Ejecutar el comando: docker-compose up -d

Los pasos anteriores levantaran un contenedor con un PostgresSql que contiene la base de datos "asd_activos", y que por defecto se han
configurado las credenciales de:
- Usuario:postgres
- Contraseña:postgres

**Componente API RESTFul**

Una vez desplegado el contenedor con la base de datos postgres y confirmado que su estado es UP, en ejecución; Se procede a generar el
contenedor con el API.

1. Para desplegar el componente Spring Boot, se debe descargar y clonar el repositorio.
2. En el respositorio, ingresar al directorio de raiz "/activos-fijos-asd", desde una terminal.
3. Ejecutar el comando: # docker build -t img-activos-fijos-asd-app .

**Nota:**Antes de levantar el contenedor, configurar la variable de entorno "LOCALHOST_IP", con la dirección ip del equipo;
lo anterior para permitir conectar el componete Api, con la base de dato.

4. Ejecutar el comando:
docker container run -it -d --name con-activos-fijos-asd-app \
-e SPRING_PROFILES_ACTIVE=prod \
-e LOCALHOST_IP=192.168.20.40 \
-p 9090:9090 \
img-activos-fijos-asd-app
