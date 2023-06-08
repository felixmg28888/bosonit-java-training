# **17. Spring Batch**

#### **Nombre proyecto Maven:**  block17-spring-batch
#### **Tiempo estimado:** 6 horas

#

Aplicación de Spring Batch para procesar un .csv con información del tiempo, distribuido en localidades y o provincias.

Consistirá en dos tablas o entidades una llamada **Tiempo** y otra **TiempoRiesgo** relacionadas uno a uno.

En **TiempoRiesgo** se almacena:

        - El objeto tiempo.
        - La fecha de la predicción.
        - El riesgo que supone dependiendo de la temperatura de la zona: 
                - ALTO(+36)
                - MEDIO(+32)
                - BAJO(-24)

<br>

*Lo mismo se puede aplicar para otros datos como la probabilidad de precipitación, granizo o nevada.*

# 

## **CSV**

**Campos:** 

        Localidad, día (día/mes/año), temperatura.

<br>

Los resultados se guardan en una tabla con estos campos: 

        - Localidad
        - Mes
        - Número mediciones
        - Año
        - Temperatura Media
        - Riesgo

Esta tabla **NO** se debe truncar cada vez que se generan datos.

# 

## **Tratamiento de errores:**
- Si la temperatura es superior a 50 grados y/o inferior a -20 grados se debe marcar registro como erróneo.

- Si hay más de 100 errores en total, se cancela el traspaso (cuidado con los registros ya guardados).

- Los errores se guardarán en un CSV: REGISTROS_ERRONEOS.CSV

# 

Este error deberá poder luego ser tratado y metido en la tabla de resultados anteriores.

 

**Ejemplo: CSV entrada**

        Madrid, 01/01/2022,15
        Madrid, 01/01/2022,17
        Madrid, 01/01/2022,65 -- ERROR
        Vitoria, 01/01/2022,12
        Vitoria, 01/01/2022,-2
        Vitoria, 01/01/2022,17

<br>

**Salida Media (guardado en DB):**

        Madrid, 01, 2022,2,16
        Vitoria, 01, 2022,3,8

<br>


**Salida Error en CSV. (El mismo formato que el de entrada)**

        Madrid, 01/01/2022,65

# 

Cuando volvamos a ejecutar el proceso con el fichero de errores como entrada, deberemos tener en cuenta que siempre habra unos datos anteriores en la DB.

<br>

**CSV de entrada:**

        Madrid, 01/01/2022,6

**Salida en DB (aparte de los registros que ya hubiera):**

        Madrid, 01, 2022,3,8

#

***Opcional:<br>
En el caso de que haya más de 5 errores por Chunk ese chunk se despreciara y se guardara en un fichero de errores. CHUNKS_ERRONEOS.CSV (estudiar?)***