# BLUE BANK DEMO
Esta es una aplicación demo que realiza 4 transacciones bancarias (Crear Cuenta, Consultar Saldo, Deposito y Retiro) comunicandose a traves de un API 
simulada por medio de json-server y guardando los datos en una Base de Datos local. 

# Instalación de json-server
Para el correcto funcionamiento de esta demo se debe instalar json-server, a continuación se dará un breve instructivo para instalarlo y ejecutarlo.
Tambien se puede consultar la pagina oficial https://www.npmjs.com/package/json-server

1. Instalar node.js en el PC y configurar las variables de entorno.
2. Abrir la terminal y escribir "npm install -g json-server"
3. Una vez instalada puede editar el archivo db.json que será la base de datos inicial del API, por defecto ya tiene un registro creado.
4. Iniciar el servidor desde la terminal, ubicandose en la carpeta donde está la base de datos escribir "json-server --host 192.168.0.*** db.json"
   reemplazando los *** por la IP del PC en el que se ejecutará el Servidor.
5. Por defecto el puerto que se crea es el 3000.
6. Una vez iniciado el servidor se puede consultar la URL desde el navegador, POSTMAN o la app.

# Preparación de la APP
Para poder ejecutar la aplicación correctamente solo se debe editar la variable BASE_URL ubicada en ApiUtils.class, asignandole el valor de la IP 
que cofiguramos en el json-server EJ: "http://192.168.0.27:3000/"

Una vez hecho esto se puede compilar e iniciar la aplicación normalmente desde un AVD o desde un telefono real conectado a la misma red del PC que 
aloja al servidor simulado. 

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

# BLUE BANK DEMO
This is a demo application that performs 4 bank transactions (Create Account, Check Balance, Deposit and Withdrawal) communicating through an API
simulated by means of json-server and saving the data in a local Database.

# Installation of json-server
For the correct operation of this demonstration, json-server must be installed, then a brief instruction will be given to install and run it.
You can also consult the official website https://www.npmjs.com/package/json-server

1. Install node.js on the PC and configure the environment variables.
2. Open the terminal and type "npm install -g json-server"
3. Once installed, you can edit the db.json file that will be the initial API database, by default it already has a record created.
4. Start the server from the terminal, going to the folder where the database is, write "json-server --host 192.168.0. *** db.json"
   replacing the *** with the IP of the PC on which the Server will run.
5. By default the port that is created is 3000.
6. Once the server has started, the URL can be consulted from the browser, POSTMAN or the app.

# Preparation of the APP
In order to run the application correctly, you just have to edit the BASE_URL variable located in ApiUtils.class, assigning it the value of the IP
that we configure in the json-server EJ: "http://192.168.0.27:3000/"

Once this is done, the application can be compiled and started normally from an AVD or from a real phone connected to the same network of the PC that
hosts the simulated server.
