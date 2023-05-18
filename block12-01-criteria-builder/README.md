# 12. Spring Data (Avanzado)

## 12.1 CriteriaBuilder

#### **Nombre proyecto Maven:**  block12-01-criteria-builder (hay que modificar block11-01-cors)

#### **Tiempo estimado:** 16 horas

#

**1) Crear endpoint.**
- Debe permitir buscar aquellas personas cuyo "user", "name", "surname" o "fecha creación" superior a una dada y/o inferior a una dada. 
- Incluir otro campo que indicará si se quiere ordenar el resultado por "user" o "name". 
- Tener en cuenta que cualquiera de esos campos puede ser mandados, pero ninguno es obligatorio.

**2) Incluir paginación.**
- Debe poder mostrar N elementos a partir de la página dada. Así si se
define que el tamaño de la página es 10 y que se quiere mostrar la página 1, se mostrará del registro
11 al 20 (ambos inclusive). 
- Por defecto el tamaño de la página será 10 y el número de página será obligatorio mandarlo.