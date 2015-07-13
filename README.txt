ASIGNATURA: PROGRAMACIÓN DE APLICACIONES MÓVILES EN ANDROID
Unidad 3: Desarrollo de aplicaciones en Android
Actividad 10 – Tarea sumativa: Construcción de una interfaz de usuario en Android
Profesor: Cristian Correa
Estudiante: Danilo Vásquez Simonetti

¿Qué componentes UI estaba utilizando incorrectamente y por qué?

El RelativeLayout no daba los resultados esperados ya que al mostrarse el teclado se desconfiguraban algunos objetos. Se cambio por LinearLayout y GridLayout incluyendo un scroll vertical.
Las pestañas se deben implementar con ViewPage ya que esto permite aplicar el "swype" (deslizar horizontalmente para cambiar pestañas) lo cual facilita el cambio de pestañas para el usuario.

¿Cuál es el aporte de los wireframes al nuevo proceso de construcción?

Los wireframes permiten tener una idea básica del flujo de uso de la aplicación y como debe organizarse la información a grandes rasgos.

¿Cómo mejoró la interfaz de la aplicación al utilizar wireframes?

Creo que el uso de wireframes es útil cuando estas recien definiendo la interfaz. Al crear los wireframes tienes que tomar las primeras decisiones de como mostrar la información al usuario y además sirve para mostrar un bosquejo al usuario.
Mas allá de eso las mejoras aplicadas consiste principalmente en un nuevo sistema de pestañas basado en ViewPage que permite mostrar la pestaña activa y permite cambiar entre pestañas utilizando el "swype".
