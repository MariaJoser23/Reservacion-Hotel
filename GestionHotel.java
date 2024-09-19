import java.util.ArrayList;
import java.util.Scanner;

public class GestionHotel {
    private static ArrayList<Habitacion> habitaciones = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            System.out.println("Registre el Usuario:");
            System.out.print("Ingrese el nombre del usuario a registrar: ");
            String usuario = scanner.nextLine();
            System.out.print("Ingrese la contraseña: ");
            String contraseña = scanner.nextLine();
            System.out.print("Ingrese el rol (ADMINISTRADOR, EMPLEADO, CLIENTE): ");
            String rolInput = scanner.nextLine().toUpperCase();
            Rol rol = Rol.valueOf(rolInput);

            Usuario.registrarUsuario(usuario, contraseña, rol);
            System.out.println("El usuario fue registrado con éxito.");

            System.out.println("\nInicie sesión:");
            System.out.print("Ingrese el nombre del usuario: ");
            String usuarioLogin = scanner.nextLine();
            System.out.print("Ingrese la contraseña: ");
            String contraseñaLogin = scanner.nextLine();

            Usuario usuarioAutenticado = Usuario.iniciarSesion(usuarioLogin, contraseñaLogin);
            System.out.println("El inicio de sesión fue exitoso, el Rol del usuario es: " + usuarioAutenticado.getRol());

            int opcion;
            do {
                mostrarMenu(usuarioAutenticado.getRol());
                opcion = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcion) {
                    case 1:
                        if (usuarioAutenticado.getRol() == Rol.ADMINISTRADOR || usuarioAutenticado.getRol() == Rol.EMPLEADO) {
                            agregarHabitacion();
                        } else if (usuarioAutenticado.getRol() == Rol.CLIENTE) {
                            registrarHabitacionCliente(usuarioAutenticado);
                        } else {
                            System.out.println("No tiene permiso para agregar habitaciones.");
                        }
                        break;
                    case 2:
                        if (usuarioAutenticado.getRol() == Rol.ADMINISTRADOR || usuarioAutenticado.getRol() == Rol.EMPLEADO) {
                            cambiarEstadoHabitacion();
                        } else if (usuarioAutenticado.getRol() == Rol.CLIENTE) {
                            cambiarEstadoHabitacionCliente(usuarioAutenticado);
                        } else {
                            System.out.println("No tiene permiso para cambiar el estado de las habitaciones.");
                        }
                        break;
                    case 3:
                        mostrarHabitaciones();
                        break;
                    case 4:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } while (opcion != 4);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void mostrarMenu(Rol rol) {
        System.out.println("\nGestión de Hotel");
        if (rol == Rol.ADMINISTRADOR || rol == Rol.EMPLEADO) {
            System.out.println("1. Agregar habitación");
        } else if (rol == Rol.CLIENTE) {
            System.out.println("1. Registrar habitación");
        }
        System.out.println("2. Cambiar estado de habitación");
        System.out.println("3. Mostrar todas las habitaciones");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void agregarHabitacion() {
        System.out.print("Ingrese el número de la habitación: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el tipo de habitación (simple, doble, suite): ");
        String tipo = scanner.nextLine();

        if (!tipo.equalsIgnoreCase("simple") && !tipo.equalsIgnoreCase("doble") && !tipo.equalsIgnoreCase("suite")) {
            System.out.println("Tipo de habitación no válido. Debe ser 'simple', 'doble' o 'suite'.");
            return;
        }

        Habitacion habitacion = new Habitacion(numero, tipo);
        habitaciones.add(habitacion);
        System.out.println("La habitación fue registrada con éxito.");
    }

    private static void cambiarEstadoHabitacion() {
        System.out.print("Ingrese el número de la habitación: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numero) {
                if (habitacion.estaDisponible()) {
                    habitacion.reservar();
                } else {
                    habitacion.liberar();
                }
                return;
            }
        }
        System.out.println("La habitación no fue encontrada.");
    }

    private static void cambiarEstadoHabitacionCliente(Usuario usuario) {
        System.out.print("Ingrese el número de la habitación: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numero) {
                if (habitacion.estaDisponible()) {
                    habitacion.reservar();
                    System.out.println("La habitación fue reservada por el cliente.");
                } else {
                    System.out.println("La habitación ya está ocupada.");
                }
                return;
            }
        }
        System.out.println("La habitación no fue encontrada.");
    }

    private static void registrarHabitacionCliente(Usuario usuario) {
        System.out.print("Ingrese el número de la habitación que desea registrar: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el tipo de habitación (simple, doble, suite): ");
        String tipo = scanner.nextLine();

        if (!tipo.equalsIgnoreCase("simple") && !tipo.equalsIgnoreCase("doble") && !tipo.equalsIgnoreCase("suite")) {
            System.out.println("Tipo de habitación no válido. Debe ser 'simple', 'doble' o 'suite'.");
            return;
        }

        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numero) {
                System.out.println("La habitación con el número " + numero + " ya está registrada.");
                return;
            }
        }

        Habitacion habitacion = new Habitacion(numero, tipo);
        habitaciones.add(habitacion);
        System.out.println("La habitación fue registrada con exito por el cliente.");
    }

    private static void mostrarHabitaciones() {
        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones registradas.");
        } else {
            for (Habitacion habitacion : habitaciones) {
                habitacion.mostrarInformacion();
                System.out.println();
            }
        }
    }
}















      









