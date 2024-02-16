package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import  java.util.Iterator;

public class Habitaciones {

    private List<Habitacion> coleccionHabitaciones;

    // MÉTODOS

    public Habitaciones() {
        this.coleccionHabitaciones = new ArrayList<>();
    }

    // Método para obtener una copia profunda de las habitaciones
    public List<Habitacion> get() {
        return copiaProfundaHabitaciones();
    }

    // Método privado para realizar una copia profunda de las habitaciones
    private List<Habitacion> copiaProfundaHabitaciones() {
        List<Habitacion> miHabitacion = new ArrayList<>();

        // Utilizo un iterador para recorrer la lista de habitaciones
        Iterator<Habitacion> iterator = coleccionHabitaciones.iterator();
        while (iterator.hasNext()) {
            Habitacion habitacion = iterator.next();
            if (habitacion != null) {
                miHabitacion.add(new Habitacion(habitacion));
            }
        }

        // Devuelvo una nueva lista con las habitaciones copiadas
        return new ArrayList<>(miHabitacion);
    }

    // Método para obtener las habitaciones de un tipo específico
    public List<Habitacion> get(TipoHabitacion tipoHabitacion) {
        List<Habitacion> habitacionesTipo = new ArrayList<>();

        // Utilizo un iterador para recorrer la lista de habitaciones
        Iterator<Habitacion> iterator = coleccionHabitaciones.iterator();
        while (iterator.hasNext()) {
            Habitacion habitacion = iterator.next();
            if (habitacion.getTipoHabitacion().equals(tipoHabitacion)) {
                habitacionesTipo.add(new Habitacion(habitacion));
            }
        }

        // Devuelvo una nueva lista con las habitaciones del tipo especificado copiadas
        return new ArrayList<>(habitacionesTipo);
    }

    // Método para obtener el tamaño actual de la colección
    public int getTamano() {
        return coleccionHabitaciones.size();
    }

    // Método para insertar una nueva habitación en la colección
    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede insertar una habitación nula.");
        }

        // Compruebo si la habitación ya existe en la colección
        if (buscar(habitacion) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una habitación con ese identificador.");
        }

        // Añado la nueva habitación a la colección
        coleccionHabitaciones.add(new Habitacion(habitacion));
    }

    // Método privado para buscar el índice de una habitación en la colección
    private int buscarIndice(Habitacion habitacion) {
        return coleccionHabitaciones.indexOf(habitacion);
    }

    // Método privado para verificar si un índice está fuera del tamaño actual
    private boolean capacidadSuperada(int indice) {
        return indice >= coleccionHabitaciones.size();
    }

    // Método para buscar una habitación en la colección
    public Habitacion buscar(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("error nulo");
        }

        // Busco el índice de la habitación en la colección
        int indice = buscarIndice(habitacion);

        // Devuelvo la habitación encontrada o null si no se encontró
        return (indice != -1) ? new Habitacion(coleccionHabitaciones.get(indice)) : null;
    }

    // Método para borrar una habitación de la colección
    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se puede borrar una habitación nula.");
        }

        // Busco el índice de la habitación en la colección
        int indice = buscarIndice(habitacion);

        // Compruebo si encuentra la habitación
        if (indice == -1) {
            throw new OperationNotSupportedException("ERROR: No existe ninguna habitación como la indicada.");
        }

        // Elimino la habitación de la colección
        coleccionHabitaciones.remove(indice);
    }
}
