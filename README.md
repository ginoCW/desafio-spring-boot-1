# Instrucciones ejecución desafio-spring-boot
## 1. Para ejecutar Proyecto en visual Studio code
	Descargar el codigo entregado
	En visual studio code seleccionar File->Open Folder...
		Seleccionar el repositorio "desafio-spring-boot"
	Configuración VS Code 
		Instalar la extension "https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack"
	Ejecutar proyecto
		Identificar el archivo "src\main\java\com\taskmanager\tmanager\TmanagerApplication.java"
		Click Derecho -> Run Java
	Con esto estamos listos para consumir la API por Swagger o Postman

## 2. Pruebas
### 2.1 Para probar API Swagger
	Ir a la url 
	localhost:8080/swagger-ui/index.html
	En el buscador de swagger introducir "/v3/api-docs" y presionar boton "Explore"
	Autenticar JWT
		Identificar "auth-controller/auth-login"
		Logear con cualquier nombre ("dummy" por ejemplo)
		Donde se muestra el response copiar el token
		Ir a la parte superior de la página y presionar el boton "Authorize"
		Pegar el token obtenido y presionar "Authorize"
		Hecho esto estaremos en condiciones para consumir cualquier endpoint disponible en swagger
	
### 2.2 Para probar desde Postman
	Abri postman
	Crear un workspace en blanco
	Identificar archivos ubicados en la carpeta "Postman Collections" en la raiz del proyecto java
	Configuracion entorno
		Seleccionar "Environments"
		Presionar boton "Import"
		Importar archivo de entorno "RegistroTareasToken.postman_environment"
		Una vez importado, en la parte superior derecha donde dice "No environment" debemos presionar y seleccionar "RegistroTareasToken"
	Importar Collección
		Seleccionar "Collections"
		Presionar boton "Import"
		Importar archivo "TaskManagerAPI.postman_collection"
		Buscamos el endpoint login de la carpeta "Auth" y apretamos "Send"
			(Este request tiene configurado un script para actualizar automaticamente nuestro token del environment configurado)
		Ahora para probar que todo funcione vamos al request "getAllTareas" dentro de la carpeta "Tareas"
			Si esto funciona nuestro token se configuro correctamente
