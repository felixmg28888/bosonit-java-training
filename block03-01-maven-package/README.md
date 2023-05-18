# 3. Herramientas: Maven, Git
## 3.1 Empaquetar proyecto Maven

#### **Nombre proyecto Maven:** block03-01-maven-package
#### **Tiempo estimado:** 3 horas

#

- Crear un programa simple que imprima por consola: Hello world!.
- Compilar nuestro proyecto con Maven desde IntelliJ. Esto nos deberá crear un fichero JAR, en el directorio ‘target’. Conseguir ejecutar el programa java desde línea de comandos con la
instrucción:

        java –jar XXX.jar

**Hint:** Para poder ejecutar nuestro JAR, hay que incluir este plugin en el fichero pom.xml.

#

*Para que Java sepa dónde está nuestro main, dentro del fichero JAR, hay que especificarlo de algún modo.<br> 
Este plugin indica en qué clase está nuestra función main.<br>
En el ejemplo, es la clase Prueba la que está en el paquete “com.bosonit.prueba”.<br>
Recordar que Java deberá estar en el path de windows.*


    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>2.4</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.bosonit.prueba.Prueba</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>



