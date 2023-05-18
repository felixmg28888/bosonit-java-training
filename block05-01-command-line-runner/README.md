# 5. Spring Boot (Básico)
## 5.1 CommandLineRunner


#### **Nombre proyecto Maven:** block05-01-command-line-runner
#### **Tiempo estimado:** 4 horas

#

1) Realizar programa con tres clases que se deberán ejecutar al arrancar el programa. 
- Una primera clase mostrará el texto: "Hola desde clase inicial".
- Una segunda clase mostrará el texto: "Hola desde clase secundaria". 
- Una tercera clase mostrará el texto: "Soy la tercera clase". 

    *Se deberá utilizar ***@Postconstruct*** en la primera clase y la interface ***CommandLineRunner*** en las dos siguientes.*



Por ejemplo:

    @SpringBootApplication
    public class Main {
            @Bean
            CommandLineRunner ejecutame()
            {
                return p ->
                {
                    System.out.println("Linea a ejecutar cuando arranque");
                };
            }
    }

¿En qué orden se muestran los mensajes? ¿Por qué?

<br>

2) Modificar la tercera clase para que imprima los valores pasados como parámetro al ejecutar el
programa.