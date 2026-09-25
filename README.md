# PP_TP2_47680

TP2: Programación Orientada a Objetos en Java

Materia: Paradigmas de Programacion
Unidad 2: Organización, reutilización y recursos avanzados en POO

Descripcion del Proyecto
Este proyecto consiste en un sistema orientado a objetos en Java diseñado para la administración, control y cálculo de costos de Eventos Universitarios y sus diversas actividades (Charlas, Talleres y Cursos). Fuimos construyendo la solución de manera gradual ejercicio por ejercicio, agregando control de errores, persistencia en disco y diseño basado en interfaces.

El primer paso fue darle estructura a nuesto proyecto en paquetes para luego enfocarnos en darle solidez al sistema frente a errores en tiempo de ejecución y en lograr que los datos no se perdieran al cerrar la aplicación mediante :

Control de Cupos con Excepciones: Para evitar que una actividad supere el límite de personas permitidas, creamos nuestra propia excepción chequeada llamada CupoExcedidoException. Agregamos la validación: si la cantidad de inscriptos alcanza el cupo máximo, el programa interrumpe el flujo y lanza la excepción con un mensaje claro, obligando a manejar el error con un bloque try-catch.

Persistencia mediante Serialización: Implementamos la interfaz Serializable en el árbol de clases  (EventoUniversitario, Actividad, Estudiante, Sala, Inscripcion). Esto nos permitió agregar en EventoUniversitario los métodos persistirEvento() , que se encargan de guardar el objeto completo con todo su estado interno en un archivo .dat.

Despues, buscamos incorporar nuevos tipos de actividades. Pimero la creación de la Interfaz Certificable dentro del paquete certificacion, luego fue la incorporación de la clase Curso como una nueva subclase de Actividad la hicimos implementar Certificable para emitir certificados de aprobación. Y modificamos Taller para que también implemente Certificable.

Finalmente, fue hacer que la clase EventoUniversitario pudiera filtrar actividades evitando casteos manuales o inseguros. Implementamos el método genérico por Tipo. Este método recibe la clase concreta que queremos buscar (por ejemplo Taller.class o Curso.class), recorre la lista general del evento y nos devuelve una lista con esa actividad específica. Y diseñamos el método calcularCostoMateriales usando el comodín o Wildcards, logramos que el método pueda recibir tanto la lista general de actividades como cualquier sublista filtrada de talleres o cursos, sumando los costos de materiales de manera polimórfica sin importar el tipo exacto de la lista recibida.
