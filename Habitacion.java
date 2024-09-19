public class Habitacion {
    private int numero;
    private String tipo;  
    private boolean ocupada;

    public Habitacion(int numero, String tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.ocupada = false;
    }

    public boolean estaDisponible() {
        return !ocupada;
    }

    public void reservar() {
        if (estaDisponible()) {
            this.ocupada = true;
            System.out.println("La habitación fue registrada con exito.");
        } else {
            System.out.println("La habitación ha sido reservada.");
        }
    }

    public void liberar() {
        this.ocupada = false;
        System.out.println("La habitación ha sido liberada.");
    }

    public String getTipo() {
        return tipo;
    }

    public int getNumero() {
        return numero;
    }

    public void mostrarInformacion() {
        String estado = estaDisponible() ? "Disponible" : "Ocupada";
        System.out.printf("Número de habitación: %d\nTipo de habitación: %s\nEstado: %s\n", numero, tipo, estado);
    }
}


