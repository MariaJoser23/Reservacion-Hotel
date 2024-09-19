import java.util.HashMap;
import java.util.Map;

public class Usuario {
    private String usuario;
    private String contraseña;
    private Rol rol;

    private static Map<String, Usuario> usuarios = new HashMap<>();

    public Usuario(String usuario, String contraseña, Rol rol) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public static void registrarUsuario(String usuario, String contraseña, Rol rol) throws Exception {
        if (usuarios.containsKey(usuario)) {
            throw new Exception("El usuario ya está registrado.");
        }
        usuarios.put(usuario, new Usuario(usuario, contraseña, rol));
    }

    public static Usuario iniciarSesion(String usuario, String contraseña) throws Exception {
        Usuario u = usuarios.get(usuario);
        if (u == null) {
            throw new Exception("Usuario no encontrado.");
        }
        if (!u.getContraseña().equals(contraseña)) {
            throw new Exception("Contraseña incorrecta.");
        }
        return u;
    }

    public static Rol obtenerRol(String usuario) throws Exception {
        Usuario u = usuarios.get(usuario);
        if (u == null) {
            throw new Exception("El Usuario no fue encontrado.");
        }
        return u.getRol();
    }

    public String getContraseña() {
        return contraseña;
    }

    public Rol getRol() {
        return rol;
    }
}


