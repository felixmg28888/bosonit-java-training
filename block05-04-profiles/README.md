# 5. Spring Boot (Básico)
## 5.4 Perfiles
  
  
#### **Nombre proyecto Maven:** block05-04-profiles
#### **Tiempo estimado:** 4 horas.

#

Crear programa que reciba el nombre del entorno vía JVM y cargue el fichero de propiedades correspondiente al entorno. El programa debe tener 3 ficheros de propiedades, uno para cada
entorno: local, INT y PRO.

Crear el programa modo consola, usando CommandLineRunner.

Cada fichero de propiedades debe contener las mismas propiedades pero diferente información.

Para el ejercicio, deben contener únicamente las siguientes propiedades: "spring.profiles.active" y "bd.url".

Para local:

      spring.profiles.active=local
      bd.url=localhost:5432
Para INT:

      spring.profiles.active=INTEGRATION
      bd.url=int.bosonit.com:5432
Para PRO:

      spring.profiles.active=PRODUCTION
      bd.url=pro.bosonit.com:5432
En formato YAML (YML)

      spring:
         profiles:
            active: PRODUCTION
      bd.url: pro.bosonit.com:5432

<br>

**Nota:** Recordad guardar las Run Configuration del ejercicio.