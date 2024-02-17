package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Huespedes {

    // ArrayList para almacenar los huéspedes
    private final List<Huesped> coleccionHuespedes;

    // Constructor
    public Huespedes() {
        this.coleccionHuespedes = new ArrayList<>();
    }

    // Para devolver una copia profunda de los huéspedes
    public List<Huesped> get() {
        return copiaProfundaHuespedes();
    }

    // Para realizar la copia profunda de los huéspedes
    private List<Huesped> copiaProfundaHuespedes() {
        List<Huesped> misHuesped = new ArrayList<>();

        // Itera sobre los huéspedes y agrega copias profundas al nuevo ArrayList

        Iterator<Huesped> huespedIt = coleccionHuespedes.iterator();

        while (huespedIt.hasNext()){

            misHuesped.add(new Huesped(huespedIt.next()));

        }

        return misHuesped;
    }

    // Para obtener el tamaño de la lista
    public int getTamano() {
        return coleccionHuespedes.size();
    }

    // Para insertar un nuevo huésped en la colección
    public void insertar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede insertar un huésped nulo.");
        }

        // Comprueba si el huésped ya existe en la colección
        if (buscar(huesped) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe un huésped con ese dni.");
        }

        // Agrega el huésped al ArrayList
        coleccionHuespedes.add(new Huesped(huesped));
    }

    // Para buscar un huésped en la colección
    public Huesped buscar(Huesped huesped) {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede buscar un huésped nulo.");
        }

        // Utilizo un iterador para buscar el huésped en el ArrayList
        Iterator<Huesped> iterator = coleccionHuespedes.iterator();
        while (iterator.hasNext()) {
            Huesped actual = iterator.next();
            if (actual.equals(huesped)) {
                return new Huesped(actual);
            }
        }

        return null;
    }

    // Para eliminar un huésped de la colección
    public void borrar(Huesped huesped) throws OperationNotSupportedException {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se puede borrar un huésped nulo.");
        }

        if(!coleccionHuespedes.contains(huesped)){

            throw new OperationNotSupportedException("ERROR: No existe ningún huésped como el indicado.");
        }

        // Utilizo un iterador para buscar y eliminar el huésped del ArrayList
        Iterator<Huesped> iterator = coleccionHuespedes.iterator();
        while (iterator.hasNext()) {
            Huesped actual = iterator.next();
            if (actual.equals(huesped)) {
                iterator.remove();
                return;
            }
        }

    }
}
