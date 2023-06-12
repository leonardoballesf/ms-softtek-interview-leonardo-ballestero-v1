# MS Softek Interview Leonardo Ballestero V1
Entrevista de habilidades técnicas para cliente Inditex referente a e-comerce

## Iniciando

Siga las siguientes instrucciones para iniciar el desarrollo de este proyecto.

### Pre-Requisitos

Plugins que deben estar instalados en su IDE:
* [Lombok](http://projectlombok.org/) - *Libreria de Bytecode que genera automaticamente los Getters y Setters*.
* [CheckStyle](http://www.checkstyle.com/) - *Plugin para poder comprobar el estilo del codigo usando las reglas de Google*
* FindBugs - *Plugin que realiza un análisis estático para buscar errores en el código en base a patrones de errores.* 


Definir las siguiente variable de entorno:

| Variable | Valor |
| -------- | ----- |
| LOGSTASH_SOCKET | pmbklnxd11:9800 |
| SPRING_PROFILES_ACTIVE |local|

Ejemplo para levantar el api en Windows: mvn spring-boot:run -Drun.jvmArguments="-DLOGSTASH_SOCKET=pmbrklnxd11:9800"


## Tipos de commits utilizados
| Variable | Descripción |
| -------- | ----- |
| chore | Se usa para cargar el el proyecto base. También se utiliza para un first o initial commit del repositorio donde no tiene sentido dividir los commits ya que simbolizan un único entregable |
| feat |Agrega una nueva funcionalidad|

- La estrategia implementada consiste en utilizar commits atómicos que incluyen funcionalidad y pruebas unitarias. Estos commits se validan para asegurar que agreguen un valor mínimo funcional. El objetivo es mantener los commits limpios y fáciles de entender, evitando que superen los 30 archivos o las 200 líneas de código por archivo.

