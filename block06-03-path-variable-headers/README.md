# 6. Spring Web

## 6.3 Path variables y headers

#### **Nombre proyecto Maven:** block6-03-path-variable-headers
#### **Tiempo estimado:** 8 horas

#

1) Programa ejemplo: https://spring.io/guides/gs/rest-service/<br>
Hacer aplicación como la del ejemplo <br>
Incluyendo:
- Petición POST: mandando un objeto JSON en el body y recibiendo ese mismo objeto JSON en la respuesta (en el body).

- Petición GET: mandando parámetros en el path (http://localhost:8080/user/{id}) y devolver el mismo id mandado.

- Petición PUT: mandando Request Params (http://localhost:8080/post?var1=1&var2=2) devolver un HashMap con los datos mandados.<br>
Por ejemplo: [ {var1: 1}, {var2: 2} ]