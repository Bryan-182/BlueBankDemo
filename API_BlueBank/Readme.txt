Instrucciones para levantar el servidor JSON localmente.

Tambien se pueden ver en la pagina oficial https://www.npmjs.com/package/json-server

1. Instalar node.js en el PC y configurar las variables de entorno.
2. Abrir la terminal y escribir "npm install -g json-server"
3. Una vez instalada puede editar el archivo db.json que será la base de datos inicial del API, por defecto ya tiene un registro creado.
4. Iniciar el servidor desde la terminal, ubicandose en la carpeta donde está la base de datos escribir "json-server --host 192.168.0.*** db.json"
reemplazando los *** por la IP del PC.
5. Por defecto el puerto que se crea es el 3000.
6. Una vez iniciado el servidor se puede consultar la URL desde el navegador, POSTMAN o la app.

