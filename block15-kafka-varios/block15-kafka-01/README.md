# 12. Mensajería

## Kafka

#### **Nombre proyecto Maven:** block15-kafka
#### **Tiempo estimado:** 16 horas

#

Usando Kafka, montar dos aplicaciones: una que envíe mensajes y la segunda le responda con otros mensajes.
<br>

#

### **Nota**
*En esta carpeta se encuentran tres proyectos diferentes con distintas versiones del ejercicio, dentro de cada carpeta se adjunta un README.md donde se especifican más detalles de cada versión.*
<br>

#

### **Primera versión de este ejercicio.**

Dos aplicaciones independientes, un productor que envía mensajes a un Topic y un Consumer que los recibe.

Los mensajes en esta versión tan solo son cadenas de String.

Se adjunta el fichero Postman donde a través de Endpoint se recrea la situación de una Pizzería, donde el producer cada cierto tiempo
va actualizando el estado de la pizza y el cliente recibe cada actualización a través de consulta web.