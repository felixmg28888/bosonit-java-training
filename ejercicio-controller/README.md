# Ejercicio sobre Controllers

### **Tiempo:** El doble del que le haya llevado al primer estudiante (aprox: 6-8 horas)

### **Requisitos:** Usar buenas prácticas. 

#

**1)** Hacer un programa con un "controller" que tenga una función a la que entre cualquier petición GET que no tenga una ruta específica en otro GET, independiente de la URL o query vaya a ello.

**Ejemplo:**<br>

- localhost:8080/ass/dfasdfas -> Entre a la funcion entryOther

- localhost:8080/ereqe/dfasdfas?eee=aaa -> Entre a la funcion entryOther

- Localhost:8080/ -> entryOne

- Localhost:8080/one/ -> entryOne

        @RestController public class PrincipalController {

        @GetMapping(…) // Generico public ControllerObject entryOther() { }

        @GetMapping(value={"/","one"}) public String entryOne( ) {

        return "he entrado a entroyOne";
        }

<br>

**2)** El controller por otra parte debe devolver este objeto, con estos campos:

    ControllerObject

    (
    List<String> paths; // Lista de paths. Por ejemplo, si llamamos a localhost:8080/a/b tendrá los valores a y b.

    List<Map<String,String>> querys; //Lista de parámetros en query. Ejemplo localhost:8080?nombre=jesus&ciudad=madrid. Tendra una lista de maps con los valores:

    [

    {name="nombre",value="Jesus"},

    {name="ciudad",value="madrid"}

    ]

    List<Map> headers; Lo mismo que el campo anterior, pero para los headers mandados.

    String urlOrigen. La ip desde done nos han hecho la petición.
    )
<br>

**3)** Poner un filtro de tal manera que si en la petición hay una cabecera (header) tipo "REDIRIGE" con el valor "SALTA" vaya a la función controlador.

    @GetMapping(value={"/salta"}) public String entryJump( ) {

    return "he ido a Jump";

    }