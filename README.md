# DesafioServicio
Servicio en Primer Plano

Descripción
En el siguiente desafío se deberá construir una aplicación utilizando un servicio en primer
plano y la barra de notificaciones. La app deberá tener un botón para iniciar/detener el
servicio que notificará en un intervalo regular de 7 segundos a la interfaz. Por cada
ejecución se debe incrementar un contador que será mostrado en pantalla como se observa
en la imagen 1.
El desafío tiene una actividad principal con un diseño usando ConstraintLayout con el texto
para el contador y un botón de inicio/detención. Además, un servicio en primer plano que se
extiende de Service y que implementa una tarea que se ejecuta frecuentemente hasta que el
servicio es detenido.
Instrucciones generales
1. Creación del proyecto con el layout que contiene el textView y el botón para
implementar la funcionalidad.
2. Creación de clase con ForegroundService que extiende de Service.
3. Agregar la declaración del servicio y los permisos en AndroidManifest.xml.
4. ForegroundService debe tener un indicador de su estado (iniciado/detenido) y el
contador de ejecuciones.
5. MainActivity implementa Handler.Callback para recibir mensajes desde el servicio y
presentar la información de cantidad de ejecuciones.

Paso a paso
1. Crear proyecto android, api mínima 21, targetSdk 29.
2. Modificar diseño layout por defecto activity_main.xml para incluir el TextView y el
botón.
3. Crear la clase ForegroundService que extienda de Service e implementar los
callbacks necesarios.
4. Habilitar el enlace de las vistas con MainActivity usando view binding o synthetic de
las Kotlin extension.
5. Enlazar las vistas de ser necesario.
6. MainActivity implementa la interfaz Handler.Callback para registrarse con el servicio
y que sea notificado cuando ocurra un evento, implementando handleMessage().
7. Agregar la funcionalidad de onClickListener al botón para iniciar/detener el servicio.
8. Declarar en el manifesto la clase ForegroundService dentro de app.
9. Indicar el permiso para la ejecución del servicio usando <uses-permission>.
10.Probar la app usando AVD, otro emulador o un dispositivo físico.
