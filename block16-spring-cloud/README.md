# 16. Spring Cloud

## Proyecto Cloud

#### **Nombre proyecto Maven:**  block16-spring-cloud
#### **Tiempo estimado:** 8 horas parte 1 + 4 horas parte 2 + 8 horas parte 3

#

### **Parte 1 - block16-01-spring-app-viaje-cliente**

Primero se creará una aplicación backend con dos entidades:

#### **Cliente.**
- Con las variables:

        Id, nombre, apellido, edad, email y teléfono.
<br>

- Los endpoints serán un crud básico, crear borrar, actualizar y buscar tanto por id como obtener todos los clientes.

<br>

#### **Viaje (Es un autobús de 40 plazas).**
- Variables:

        Id, origin, destination, departureDate, arrivalDate, passenger y status.

- Endpoints:

    - Crud básico para cada viaje. 
    - Añadir pasajero a viaje. Usaremos el id de cada uno, se verá de la siguiente forma:

            localhost:8080/trip/addPassenger/4/8

    - Haremos una cuenta de pasajeros que hay en cada viaje, ya que al añadir un pasajero a cada viaje deberá de limitarse a la cuenta de 40 pasajeros.
    
            localhost:8080/passenger/count/{tripId}

    - Un endpoint para cambiar la disponibilidad del viaje ya que es posible que el autobús se averíe.

            localhost:8080/trip/{tripId}/{tripStatus}

    - Finalmente crearemos un endpoint que nos indique la disponibilidad del viaje.

            localhost:8080/trip/verify/{tripId}

- *Hemos de tener en cuenta que ambas entidades están conectadas a una base de datos.*

#

### **Parte 2 - block16-02-spring-app-ticket**

Haremos la aplicación backend-Front que constará de la siguiente entidad:

#### **Ticket (Se guarda en base de datos diferente a la de viajes o clientes).**
- Variables:

        id, passengerId, passengerName, passenger Lastname, passgener Email, tripOrigin, tripDestination, departureDate y arrivalDate.

Tendremos que crear dos entidades a parte exactamente iguales que las del backend pero sin acceso a la base de datos, ya que con RestTemplate recogeremos variables de tipo cliente y viaje.

<br>

- Endpoints:

Como es un caso de práctica resumimos los endpoint a uno solo, que será el que cree el ticket, obteniendo el pasajero a través del id y añadiendolo al viaje.

    localhost:8080/generateTicket/{userId}/{tripId}

<br>

- *Para esta parte necesitaremos conocer el uso de RestTemplate o Feign, que hará uso de la aplicación backend ejecutándose de forma simultánea a esta.*

- *Para esta aplicación se podrá usar la misma base de datos u otra diferente (si es así mejor, se entenderá mejor por que los microservicios son tan usados).*

#

### **Parte 3 - block16-03-spring-app-ureka-naming-server**

Crearemos la aplicación eureka-naming-server.

- *Como has visto en la teoría poco hay que explicar en la hora de su desarrollo.*

#

### **Parte 4 - block16-04-spring-app-api-gateway**

Crearemos la aplicación api-gateway, que nos abrirá los puertos y permitirá relacionar nuestros microservicios con eureka.

#

### **Parte 5 - Dockerizar aplicación**

Finalmente dockeriza esta aplicación, respetando como dependen de las bases de datos y entre sí, es recomendable utilizar docker-compose.

    