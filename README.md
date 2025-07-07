# 🎓 EduTech Platform

> **Proyecto educativo en Spring Boot**

---

## 📚 ¿Qué es EduTech?

**EduTech** es un sistema que hicimos junto a Sergio Puebla en **Spring Boot** para administrar instituciones educativas. Sirve para manejar usuarios, cursos, inscripciones y pagos, todo con roles para estudiantes, profes y administradores.  

Está pensado como un sistema completo para la empresa de EduTech. Con arquitectura en capas y conceptos de programación en Java.

---

## 🛠️ ¿Qué hace?

| Funcionalidad              | ¿Para qué sirve?                                      | Quién lo usa                |
|----------------------------|------------------------------------------------------|-----------------------------|
| Gestión de usuarios        | Registro, login y perfiles                           | Todos                       |
| Gestión de cursos          | Crear, modificar y entregar cursos                   | Profes, administradores     |
| Sistema de inscripción     | Inscribir estudiantes y pagar                         | Estudiantes, administradores|
| Actividades educativas     | Asistencia, evaluaciones, notificaciones             | Estudiantes, profes         |
| Procesar pagos             | Métodos de pago con tarjeta                          | Estudiantes                 |
| Administración             | Configuración multicampus                            | Administradores             |
| Contenidos de cursos       | Subir y gestionar materiales                          | Profes                      |
| Reseñas de cursos          | Opiniones de estudiantes                             | Estudiantes                 |

---

## 🏛️ ¿Cómo está armado?

El sistema sigue arquitectura en capas:

- **Base de datos**: MySQL  
- **Repositorios**:
  - UsuarioRepository
  - CursoRepository
  - ProfesorRepository
  - AdministradorRepository
- **Servicios**:
  - UsuarioService
  - CursoService
  - ProfesorService
  - AdministradorService
- **Controladores (REST)**:
  - UsuarioController
  - CursoController
  - ProfesorController
  - AdministradorController

---

## 🧩 Entidades principales

Las entidades que arman el corazón de EduTech:

- Usuario (estudiantes)  
- Profesor (instructores)  
- Administrador  
- Persona (datos personales)  
- Rol (roles de acceso)  
- Curso  
- Evaluacion  
- Contenido  
- Inscripcion  
- Asistencia  
- Especialidad  
- Sede  
- Notificacion  
- Resenia  
- Tarjeta  
- FormaPago

---

## ⚙️ Tecnologías que usé

| Componente       | Tecnología        | Versión  | Para qué sirve                      |
|------------------|-------------------|----------|-------------------------------------|
| Framework        | Spring Boot       | 3.4.5    | El motor de la app                  |
| IDE              | VSCode y Zed      | 3.4.5    | El motor de la app                  |
| Framework        | Spring Boot       | 3.4.5    | El motor de la app                  |
| Lenguaje         | Java              | 17       | Puro Java                           |
| Build Tool       | Maven             | 3.9.9    | Manejar dependencias                |
| Base de datos    | MySQL             | Latest   | Persistencia de datos               |
| ORM              | Spring Data JPA   | Incluido | Mapeo objetos-relacional            |
| Web              | Spring MVC        | Incluido | APIs REST                           |
| Documentación    | OpenAPI + Swagger | 2.5.0    | Documentar endpoints                |
| Monitoreo        | Spring Actuator   | Incluido | Monitorear la app                   |
| Testing          | JUnit 5 + Mockito | Incluido | Probar el código                    |

---

## 🔄 Principales flujos

### ➡️ Registrar usuario

```plaintext
POST /usuarios
|
UsuarioController
-> UsuarioService
-> UsuarioRepository
-> PersonaRepository

[si el email ya existe]
"error: usuario ya existe"

[si está disponible]
guardar usuario
"Usuario registrado ok"

Asignar persona:
POST /usuarios/asignar/{email}/{rut}
|
asociar persona y usuario
"Persona asociada con éxito"
