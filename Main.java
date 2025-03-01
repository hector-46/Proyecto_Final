import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Clase Usuario
class Usuario {
    private int matricula;
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private String rol;

    public Usuario(int matricula, String nombre, String apellido, String correo, String contraseña, String rol) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public int getMatricula() {
        return matricula;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "matricula=" + matricula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }

}

// Clase Proyecto
class Proyecto {
    private int clave;
    private String nombre;
    private String descripcion;
    private Date fechaInicio;
    private List<Usuario> miembros;

    public Proyecto(int clave, String nombre, String descripcion, Date fechaInicio) {
        this.clave = clave;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.miembros = new ArrayList<>();
    }

    public int getClave() {
        return clave;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "clave=" + clave +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", miembros=" + miembros +
                '}';
    }

    public void crearProyecto() {
        System.out.println("Proyecto " + nombre + " creado.");
    }

    public void modificarProyecto(String nuevaDescripcion) {
        this.descripcion = nuevaDescripcion;
        System.out.println("Proyecto " + nombre + " modificado.");
    }

    public void eliminarProyecto() {
        System.out.println("Proyecto " + nombre + " eliminado.");
    }

    public void asignarMiembro(Usuario usuario) {
        miembros.add(usuario);
        System.out.println("Usuario " + usuario.getMatricula() + " asignado al proyecto " + nombre);
    }
    
        public String getNombre() {
            return nombre;
        }
    }
    
    // Clase Tarea
    class Tarea {
        private int numero;
        private String titulo;
        private String descripcion;
        private String estado;
        private Usuario responsable;
        private Proyecto proyecto;
    
        public Tarea(int numero, String titulo, String descripcion, Proyecto proyecto) {
            this.numero = numero;
            this.titulo = titulo;
            this.descripcion = descripcion;
            this.estado = "pendiente";
            this.proyecto = proyecto;
        }
    
        public int getNumero() {
            return numero;
        }
    
        @Override
        public String toString() {
            return "Tarea{" +
                    "numero=" + numero +
                    ", titulo='" + titulo + '\'' +
                    ", descripcion='" + descripcion + '\'' +
                    ", estado='" + estado + '\'' +
                    ", proyecto=" + proyecto.getNombre() +
                '}';
    }

    public void asignarResponsable(Usuario usuario) {
        this.responsable = usuario;
        System.out.println("Tarea " + titulo + " asignada a " + usuario.getMatricula());
    }

    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        System.out.println("Tarea " + titulo + " ahora está en estado " + estado);
    }
}

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Proyecto> proyectos = new ArrayList<>();
    private static List<Tarea> tareas = new ArrayList<>();

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ DE GESTIÓN DE PROYECTOS ---");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Crear proyecto");
            System.out.println("3. Asignar usuario a proyecto");
            System.out.println("4. Crear tarea");
            System.out.println("5. Cambiar estado de una tarea");
            System.out.println("6. Mostrar usuarios");
            System.out.println("7. Mostrar proyectos");
            System.out.println("8. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    crearProyecto();
                    break;
                case 3:
                    asignarUsuarioAProyecto();
                    break;
                case 4:
                    crearTarea();
                    break;
                case 5:
                    cambiarEstadoTarea();
                    break;
                case 6:
                    mostrarUsuarios();
                    break;
                case 7:
                    mostrarProyectos();
                    break;
                case 8:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    // Método para registrar un usuario
    private static void registrarUsuario() {
        System.out.print("Ingrese Matricula: ");
        int matricula = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();
        System.out.print("Rol (administrador/líder/desarrollador): ");
        String rol = scanner.nextLine();

        Usuario usuario = new Usuario(matricula, nombre, apellido, correo, contraseña, rol);
        usuarios.add(usuario);
        System.out.println("Usuario registrado exitosamente.");
    }

    // Método para crear un proyecto
    private static void crearProyecto() {
        System.out.print("Ingrese Clave del proyecto: ");
        int clave = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nombre del proyecto: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        Proyecto proyecto = new Proyecto(clave, nombre, descripcion, new Date());
        proyectos.add(proyecto);
        System.out.println("Proyecto creado correctamente.");
    }

    // Método para asignar un usuario a un proyecto
    private static void asignarUsuarioAProyecto() {
        if (usuarios.isEmpty() || proyectos.isEmpty()) {
            System.out.println("No hay usuarios o proyectos registrados.");
            return;
        }

        System.out.println("Usuarios disponibles:");
        for (Usuario u : usuarios) {
            System.out.println(u);
        }

        System.out.print("Ingrese Matricula del usuario a asignar: ");
        int matricula = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Proyectos disponibles:");
        for (Proyecto p : proyectos) {
            System.out.println(p);
        }

        System.out.print("Ingrese Clave del proyecto: ");
        int claveProyecto = scanner.nextInt();
        scanner.nextLine();

        Usuario usuario = buscarUsuarioPorMatricula(matricula);
        Proyecto proyecto = buscarProyectoPorClave(claveProyecto);

        if (usuario != null && proyecto != null) {
            proyecto.asignarMiembro(usuario);
            System.out.println("Usuario asignado al proyecto.");
        } else {
            System.out.println("Usuario o Proyecto no encontrado.");
        }
    }

    // Método para crear una tarea
    private static void crearTarea() {
        if (proyectos.isEmpty()) {
            System.out.println("No hay proyectos registrados.");
            return;
        }

        System.out.print("Ingrese ID de la tarea: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Título de la tarea: ");
        String titulo = scanner.nextLine();
        System.out.print("Descripción de la tarea: ");
        String descripcion = scanner.nextLine();

        System.out.println("Proyectos disponibles:");
        for (Proyecto p : proyectos) {
            System.out.println(p);
        }

        System.out.print("Ingrese Clave del proyecto de la tarea: ");
        int claveProyecto = scanner.nextInt();
        scanner.nextLine();

        Proyecto proyecto = buscarProyectoPorClave(claveProyecto);
        if (proyecto != null) {
            Tarea tarea = new Tarea(id, titulo, descripcion, proyecto);
            tareas.add(tarea);
            System.out.println("Tarea creada exitosamente.");
        } else {
            System.out.println("Proyecto no encontrado.");
        }
    }

    // Método para cambiar el estado de una tarea
    private static void cambiarEstadoTarea() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }

        System.out.println("Tareas disponibles:");
        for (Tarea t : tareas) {
            System.out.println(t);
        }

        System.out.print("Ingrese ID de la tarea: ");
        int idTarea = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nuevo estado (pendiente/progreso/completada): ");
        String nuevoEstado = scanner.nextLine();

        Tarea tarea = buscarTareaPorId(idTarea);
        if (tarea != null) {
            tarea.cambiarEstado(nuevoEstado);
            System.out.println("Estado de la tarea actualizado.");
        } else {
            System.out.println("Tarea no encontrada.");
        }
    }

    // Método para mostrar usuarios registrados
    private static void mostrarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (Usuario u : usuarios) {
                System.out.println(u);
            }
        }
    }

    // Método para mostrar proyectos registrados
    private static void mostrarProyectos() {
        if (proyectos.isEmpty()) {
            System.out.println("No hay proyectos registrados.");
        } else {
            for (Proyecto p : proyectos) {
                System.out.println(p);
            }
        }
    }

    // Métodos auxiliares
    private static Usuario buscarUsuarioPorMatricula(int matricula) {
        for (Usuario u : usuarios) {
            if (u.getMatricula() == matricula) return u;
        }
        return null;
    }

    private static Proyecto buscarProyectoPorClave(int clave) {
        for (Proyecto p : proyectos) {
            if (p.getClave() == clave) return p;
        }
        return null;
    }

    private static Tarea buscarTareaPorId(int id) {
        for (Tarea t : tareas) {
            if (t.getNumero() == id) return t;
        }
        return null;
    }
}