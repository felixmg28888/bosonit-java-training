# 11. Spring Web (Avanzado)

## 11.2 Subir y bajar ficheros

#### **Nombre proyecto Maven:** block11-02-upload-download-files

#### **Tiempo estimado:** 8 horas

#

**1) Subir ficheros.**
- Permitir subir un fichero incluyendo como metadato la categoría.

- Guardar el fichero y en una tabla el metadato, nombre de fichero, fecha de subida, etc. Devolver Entidad "Fichero" con los datos, incluyendo un ID único.

- Descargar fichero, buscándolo por diferentes métodos (id y nombre de fichero).

**2) Crear programa con estos endpoints.**
- Petición POST. /upload/{tipo} (@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes).

- Solo aceptara ficheros con la extensión indicada en la URL.

- Petición GET /setpath?path={directorio_a_guardar}

<br>

El programa al arrancar permite mandar un parámetro que es el directorio donde debe guardar los
ficheros. Ejemplo: java –jar MIPROGRAMA.jar “/DIRECTORIO_A_GUARDAR”. Si no se especifica esta
variable ponerlo en el directorio donde se lanza java.

<br>

#
*En el directorio "recursos" se incluyen los distintos archivos .png, .jpg, .mp3 y .mp4 utilizados, ya que se corresponden con las peticiones adjuntadas en el directorio "postman".*

*El contenido de dicho directorio es libre de derechos de autor, sin copyright.*
#