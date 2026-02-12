📱 Sistema de Gestión de Ventas de Celulares

Sistema desarrollado en Java + MySQL utilizando arquitectura MVC (Modelo – Vista – Controlador) para la gestión de clientes, inventario de celulares y registro de ventas.

🚀 Descripción del Proyecto

Este sistema permite administrar una tienda de celulares mediante:

✅ Gestión de Clientes (CRUD)

✅ Gestión de Celulares (CRUD)

✅ Registro de Ventas con múltiples productos

✅ Actualización automática de stock

✅ Cálculo automático de IVA (19%)

✅ Consulta de ventas por mes

✅ Manejo de transacciones para garantizar integridad de datos

🏗 Arquitectura del Sistema

El proyecto está estructurado bajo el patrón MVC.

📂 MODELO

Contiene las entidades del sistema:

Cliente

Celular

Venta

VentaItem

Responsabilidades:

Definición de atributos

Constructores

Getters y Setters

Validaciones

Lógica interna (ej: cálculo de total)

📂 CONTROLADOR

Contiene la lógica de negocio y conexión a la base de datos.

Interfaces:

GestionarCliente

GestionarCelular

GestionarVenta

Implementaciones:

GestionarClienteImpl

GestionarCelularImpl

GestionarVentaImpl

Responsabilidades:

Guardar

Actualizar

Eliminar

Buscar

Listar

Manejo de transacciones

Consultas agregadas (ventas por mes)

📂 VISTA

Interfaz por consola con menús interactivos:

Menu

Menu_Cliente

Menu_Celular

Menu_Venta

Responsabilidades:

Captura de datos con Scanner

Mostrar información al usuario

Invocar métodos del controlador

🗄 Base de Datos

El sistema utiliza MySQL con las siguientes tablas:

📌 cliente

id (PK)

nombre

identificacion

correo

telefono

📌 celular

id (PK)

marca

modelo

sistema_operativo

gama

precio

stock

📌 venta

id (PK)

id_cliente (FK)

fecha

total

📌 detalle_venta

id (PK)

id_venta (FK)

id_celular (FK)

cantidad

subtotal

🔄 Funcionalidades Implementadas
👤 CRUD Cliente

Registrar cliente

Actualizar cliente

Eliminar cliente

Buscar cliente

Listar clientes

📱 CRUD Celular

Registrar celular

Actualizar celular

Eliminar celular

Buscar celular

Listar celulares

Mostrar celulares con stock bajo (<5)

🧾 Gestión de Ventas

Selección de cliente

Agregar múltiples celulares

Validación de stock

Cálculo automático de subtotal

Aplicación de IVA (19%)

Registro de venta

Registro en detalle_venta

Actualización automática de stock

Manejo de transacción (commit / rollback)

📊 Reporte

Ventas agrupadas por mes

💳 Manejo de Transacciones

En GestionarVentaImpl se implementa:

con.setAutoCommit(false);


Flujo:

Insertar venta

Insertar detalle_venta

Actualizar stock

commit()

Si ocurre error:

con.rollback();


Esto garantiza:

Integridad de datos

Consistencia

Evita ventas incompletas

🧮 Lógica de Negocio
📌 Cálculo de Total con IVA
total = subtotal + (subtotal * 0.19);


Se aplica 19% de IVA sobre el subtotal de la venta.

📌 Validación de Stock

Antes de registrar un detalle:

if (cel.getStock() < cantidad)


Evita vender más unidades de las disponibles.

📌 Ventas por Mes

Consulta SQL utilizada:

SELECT DATE_FORMAT(fecha, '%Y-%m') AS mes,
       SUM(total) AS total_mensual
FROM venta
GROUP BY mes
ORDER BY mes;


Permite visualizar ingresos mensuales.

🛠 Tecnologías Utilizadas

Java

JDBC

MySQL

Arquitectura MVC

Programación Orientada a Objetos

Transacciones SQL

📦 Requerimientos Cumplidos

✔ CRUD completo

✔ Uso de interfaces

✔ Implementación de JDBC

✔ PreparedStatement

✔ Manejo de excepciones

✔ Manejo de transacciones

✔ Claves foráneas

✔ Validaciones en modelo

✔ Separación de responsabilidades (MVC)

🚀 Cómo Ejecutar el Proyecto

Crear la base de datos en MySQL.

Ejecutar el script de creación de tablas.

Configurar la clase Conexion con:

URL

Usuario

Contraseña

Ejecutar la clase principal Menu.

🔮 Posibles Mejoras Futuras

Implementar patrón DAO

Validaciones más robustas en Vista

Manejo de logs

Pruebas unitarias

Interfaz gráfica (JavaFX / Swing)

Sistema de autenticación

Control de concurrencia

🎯 Conclusión

El sistema cumple con los requerimientos de gestión de inventario y ventas, implementando correctamente:

Arquitectura MVC

Transacciones

Relaciones entre tablas

Lógica de negocio

Integridad de datos

Es un sistema funcional, estructurado y escalable a mejoras futuras.