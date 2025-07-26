# Sistema de Gestión de Equipos de Robótica

## Descripción
Sistema Java con interfaz gráfica para gestionar participantes y equipos de competencias de robótica. El sistema incluye dos tipos de usuarios: administradores y jurados.

## Características

### Funcionalidades del Administrador:
- **Gestión completa de participantes** (CRUD)
- Agregar nuevos participantes
- Editar información de participantes existentes
- Eliminar participantes
- Ver lista completa de participantes

### Funcionalidades del Jurado:
- **Visualización de equipos**
- **Edición de puntajes y resultados**
- Actualizar puntajes de equipos
- Asignar resultados a equipos

## Estructura de la Base de Datos

### Tablas principales:
- **usuarios**: Almacena credenciales de acceso
- **equipos**: Información de equipos participantes
- **participantes**: Miembros de los equipos
- **categorias**: Categorías de competencia

## Instalación y Configuración

### Requisitos:
- Java 8 o superior
- MySQL Database
- Driver JDBC para MySQL

### Configuración de la Base de Datos:
1. Crear la base de datos con las tablas proporcionadas
2. Insertar usuarios de ejemplo:
   - Usuario: `admin1`, Contraseña: `admin123`, Rol: `administrador`
   - Usuario: `jurado1`, Contraseña: `jurado123`, Rol: `jurado`

### Ejecución:
1. Compilar todos los archivos Java
2. Ejecutar la clase `Main.java`
3. Usar las credenciales proporcionadas para acceder al sistema

## Archivos Principales

### Interfaces de Usuario:
- `LoginInterface.java`: Pantalla de inicio de sesión
- `ParticipantesCRUDInterface.java`: Gestión de participantes (Administrador)
- `JuradoInterface.java`: Gestión de puntajes (Jurado)

### Clases de Modelo:
- `Usuario.java`: Modelo de usuario
- `Participante.java`: Modelo de participante
- `Equipo.java`: Modelo de equipo

### Clases de Acceso a Datos:
- `ConexionDB.java`: Gestión de conexión a base de datos
- `UsuarioBase.java`: Autenticación de usuarios
- `ParticipanteDAO.java`: Operaciones CRUD de participantes
- `EquipoDAO.java`: Operaciones CRUD de equipos

## Uso del Sistema

### Como Administrador:
1. Iniciar sesión con credenciales de administrador
2. Usar la interfaz de gestión de participantes
3. Agregar, editar o eliminar participantes según sea necesario

### Como Jurado:
1. Iniciar sesión con credenciales de jurado
2. Ver la lista de equipos participantes
3. Seleccionar un equipo para editar su puntaje y resultado
4. Actualizar la información del equipo

## Notas Importantes
- El sistema requiere conexión a internet para acceder a la base de datos
- Los cambios se guardan automáticamente en la base de datos
- Se recomienda hacer respaldos regulares de la información 