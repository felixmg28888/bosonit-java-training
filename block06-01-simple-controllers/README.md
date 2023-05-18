# 6. Spring Web

## 6.1 Controladores


#### **Nombre proyecto Maven:** block6-01-simple-controllers
#### **Tiempo estimado:** 4 horas

#

Crear una aplicación que tenga los siguientes endpoints.
- /user/{nombre} (GET) //Debe devolver un String poniendo “Hola” + el contenido de la variable nombre
- /useradd (POST) //Recibe un objeto en formato JSON.<br>
Recibe un objeto de la clase Persona, con los campos: nombre, población y edad.

  Devolver un objeto tipo persona cuya edad sea la recibida más una.<br>
  Ejemplo de cómo mandar hacer una petición POST, mandando un objeto JSON utilizando Postman
  
      {
        "name": "Persona2",
        "edad": 20,
        "ciudad": "Ciudad Persona2"
      }