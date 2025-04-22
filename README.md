# Proyecto Integrador - 4to Semestre 2025 - Grupo 4A

Este trabajo está enfocado en la formulación del proyecto integrador del cuarto semestre (2025) del grupo 4A, siguiendo los parámetros establecidos según la documentación que se nos brindó.

El objetivo de este proyecto es desarrollar un sistema para gestionar el préstamo de equipos audiovisuales y salas de informática dentro de una institución universitaria. Este sistema busca facilitar el acceso y uso de estos recursos por parte de docentes y personal administrativo, mediante un proceso organizado que incluye la solicitud, aprobación, préstamo, uso, devolución, evaluación, sanciones y mantenimiento.

Para el desarrollo del proyecto se utilizarán:

- **Diagramas UML** para representar los casos de uso y la lógica del sistema.
- **Modelado de base de datos** para estructurar y organizar la información.
- **Diseño de interfaces gráficas** que permitan una interacción clara, intuitiva y funcional con los usuarios.

La implementación se enfocará en aplicar los conocimientos adquiridos en materias como análisis y diseño de sistemas, bases de datos y desarrollo de software, integrando teoría y práctica para resolver una necesidad real del entorno universitario.

---
## Planteamiento del problema

En muchas instituciones universitarias, el proceso de préstamo de equipos audiovisuales y salas de informática se realiza de forma manual o poco sistematizada, lo que genera dificultades en la gestión eficiente de estos recursos. Los usuarios —principalmente docentes y personal administrativo— deben realizar solicitudes por correo electrónico, extensiones telefónicas o de forma presencial, lo cual puede ocasionar errores, retrasos, duplicidad de solicitudes y falta de trazabilidad.

Además, la validación de disponibilidad, el seguimiento al uso de los recursos, la aplicación de sanciones por mal uso o devoluciones tardías, y la actualización del estado de los equipos o salas, suelen depender de registros aislados o procesos informales. Esta situación no solo dificulta el acceso oportuno a los recursos tecnológicos, sino que también compromete la calidad del servicio y el aprovechamiento de la infraestructura disponible.

Por tanto, se hace necesario desarrollar un sistema integral que permita gestionar de forma automatizada y transparente el ciclo completo de los préstamos, desde la solicitud hasta la devolución, incluyendo la retroalimentación de los usuarios y el mantenimiento de los recursos.

El planteamiento de este problema de manera mas detallada puede encontrarse en el siguiente enlace: [Planteamiento problema proyecto integrador](https://github.com/duvanleandro/ProyectoIntegradorUDI/blob/main/docs/Planteamiento%20problema/PropuestaProyectoIntegrador-Cuarto-1-2025.pdf)

---
## Cómo instalar y usar el repositorio

Sigue los pasos a continuación para instalar y ejecutar el proyecto en tu máquina local:

### Requisitos previos
1. Asegúrate de tener instalado:
   - [Git](https://git-scm.com/) para clonar el repositorio.
   - [Java](https://www.oracle.com/java/technologies/downloads/?er=221886) para ejecutar el aplicativo (instala versión 8 en adelante)
     * *Si no sabes que version de java tienes, puedes entrar en el **cmd** y escribir `java -version` para verificar la versión de java que tienes instalada, si no te sale ninguna información o comando desconocido, debes decargar java*
   - [NetBeans](https://netbeans.apache.org/front/main/index.html) para utilizarlo como entorno de desarrollo para la interfaz de la aplicación

### Instalación
1. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/duvanleandro/ProyectoIntegradorUDI.git

2. Accede al directorio del proyecto:
   ```bash
   cd ProyectoIntegradorUDI
---

## Cómo utilizar la aplicación

### Como un usuario

1. Descarga el proyecto en formato ZIP desde el mismo repositorio
2. Asegurate de tener instalado el JAVA versión 8
3. Accede al aplicativo siguiendo la ruta de INTEGRADOR4A > Target > INTEGRADOR4A-1.0-SNAPSHOT-jar-with-dependencies.jar
4. Una vez en la pantalla de Login, puede ingresar a la prueba utilizando las siguientes credenciales
   * - Correo: usuario@example.com
     - Contraseña: usuario
       
   * - Correo: admin@example.com
     - Contraseña: admin

### Como un desarrollador

1. Descarga el proyecto en formato ZIP desde el mismo repositorio
2. Asegurate de tener instalado el JAVA y el NeatBeans
3. Desde NeatBeans accede a "file" y luego a "Open Project"
4. Busca el archivo en donde lo hayas guardado y selecciona la carpeta "INTEGRADOR4A"
5. Realice las modificaciones necesarias
---
## Errores comunes

### Versión de Java incompatible
![JavaError](https://i.sstatic.net/4vO7Y.png)

Este error ocurre al intentar ejecutar el archivo `.jar` con una versión de Java inferior a la 8.  
Para solucionarlo, simplemente ingresa a la página de Oracle e instala cualquier versión de Java 8 o superior.  
El enlace directo se encuentra en la sección de **Requisitos previos** mencionada anteriormente.

---
## Ejemplo previo de la interfaz

### Login
En el login puede ingresar usando las credenciales mencionadas en cómo se puede utilizar la aplicación como un usuario
![Login](https://github.com/duvanleandro/ProyectoIntegradorUDI/blob/main/img/interfaces/Interfaz1Login.png)

### interfaz del usuario
![InterfazUsu](https://github.com/duvanleandro/ProyectoIntegradorUDI/blob/main/img/interfaces/Interfaz2PanelUsuario.png)

## interfaz del administrador
![InterfazAdm](https://github.com/duvanleandro/ProyectoIntegradorUDI/blob/main/img/interfaces/Interfaz9PanelAdmin.png)

**Nota:** Este archivo puede ser modificado en el transcurso del desarrollo del proyecto para reflejar avances, ajustes o nuevas decisiones tomadas por el grupo.
