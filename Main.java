import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingrese el nombre del usuario a registrar: ");
            String usuario = scanner.nextLine();
            System.out.print("Ingrese la contraseña ");
            String contraseña = scanner.nextLine();
            System.out.print("Ingrese el rol (ADMINISTRADOR, EMPLEADO, CLIENTE): ");
            String rolInput = scanner.nextLine().toUpperCase();
            Rol rol = Rol.valueOf(rolInput);

            Usuario.registrarUsuario(usuario, contraseña, rol);
            System.out.println("El Usuario fue registrado con éxito.");

            System.out.print("Ingrese el nombre del usuario: ");
            String usuarioLogin = scanner.nextLine();
            System.out.print("Ingrese la contraseña: ");
            String contraseñaLogin = scanner.nextLine();

            Usuario usuarioAutenticado = Usuario.iniciarSesion(usuarioLogin, contraseñaLogin);
            System.out.println("El Inicio de sesión fue exitoso, El Rol del usuario es: " + usuarioAutenticado.getRol());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}

