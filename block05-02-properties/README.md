# 5. Spring Boot (Básico)

## 5.2 Carga de propiedades

#### **Nombre proyecto Maven:** block05-02-properties

#### **Tiempo estimado:** 4 horas

#

1) Realizar aplicación que tenga en el fichero "application.properties" los siguientes valores:

         greeting=Hello world!
         my.number=100

      - El programa debe imprimir por consola:

            El valor de greeting es: (valor de "greeting")
            El valor de my.number es: (valor de "my.number")


      - Intentar leer el valor "new.property", que no existe en application.properties.
      
      - Deberá asignar a la variable el texto "new.property no tiene valor".
      - Imprime por consola:

            El valor de new.property es: (valor de "new.property")

      - Poner la variable "new.property" dentro del S.O.
      - Lanzar aplicación y ver como la presenta.

<br>

2) Hacer el ejercicio anterior, pero cambiando el fichero **application.properties** por **"application.yml"** (adaptando el formato).

      - Deberían obtenerse los mismos resultados que anteriormente.<br>

      - *Yml es abreviatura de YAML:*

<br>
   
Propiedades de Spring Boot:https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html