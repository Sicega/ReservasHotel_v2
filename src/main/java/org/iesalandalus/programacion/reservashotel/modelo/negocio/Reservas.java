package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import javax.naming.OperationNotSupportedException;

public class Reservas {

    // ArrayList para almacenar las reservas
    private final List<Reserva> coleccionReservas;

    // Constructor sin parámetro de capacidad, porque ya no hace falta
    public Reservas() {

        this.coleccionReservas = new ArrayList<>();
    }

    // Devuelvo una copia profunda de las reservas
    public List<Reserva> get() {

        return copiaProfundaReservas();
    }

    // Realizo la copia profunda de las reservas
    private List<Reserva> copiaProfundaReservas() {

        List<Reserva> misReservas = new ArrayList<>();

        // Itero sobre las reservas y agrego copias profundas al nuevo ArrayList
        for (Reserva reserva : coleccionReservas) {
            misReservas.add(new Reserva(reserva));
        }

        return misReservas;
    }

    // Para insertar una nueva reserva en el ArrayList
    public void insertar(Reserva reserva) throws OperationNotSupportedException {

        if (reserva == null) {

            throw new NullPointerException("ERROR: No se puede insertar una reserva nula.");

        }

        // Compruebo si la reserva ya existe en la colección

        if (buscar(reserva) != null) {

            throw new OperationNotSupportedException("ERROR: Ya existe una reserva igual.");
        }

        // Agrego la reserva al ArrayList
        coleccionReservas.add(new Reserva(reserva));
    }

    // Para buscar una reserva en la colección
    public Reserva buscar(Reserva reserva) {
        if (reserva == null) {
            throw new NullPointerException("error nulo");
        }

        // Utilizo un iterador para buscar la reserva en el ArrayList

        Iterator<Reserva> iterator = coleccionReservas.iterator();
        while (iterator.hasNext()) {
            Reserva actual = iterator.next();
            if (actual.equals(reserva)) {
                return new Reserva(actual);
            }
        }

        return null;
    }

    // Para eliminar una reserva de la colección
    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        if (reserva == null) {
            throw new NullPointerException("ERROR: No se puede borrar una reserva nula.");
        }

        // Utilizo un iterador para buscar y eliminar la reserva del ArrayList
        Iterator<Reserva> iterator = coleccionReservas.iterator();
        while (iterator.hasNext()) {
            Reserva actual = iterator.next();
            if (actual.equals(reserva)) {
                iterator.remove();
                return;
            }
        }

        // Si no encuentra la reserva, lanza una excepción
        throw new OperationNotSupportedException("ERROR: No existe ninguna reserva como la indicada.");
    }

    // Para obtener las reservas de un huésped
    public List<Reserva> getReservas(Huesped huesped) {
        if (huesped == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un huésped nulo.");
        }

        List<Reserva> miReserva = new ArrayList<>();

        // Utilizo un iterador para recorrer el ArrayList y agregar las reservas del huésped al nuevo ArrayList
        Iterator<Reserva> iterator = coleccionReservas.iterator();
        while (iterator.hasNext()) {
            Reserva actual = iterator.next();
            if (actual.getHuesped().equals(huesped)) {
                miReserva.add(new Reserva(actual));
            }
        }

        return miReserva;
    }

    // Para obtener las reservas de un tipo de habitación
    public List<Reserva> getReservas(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de un tipo de habitación nula.");
        }

        List<Reserva> miReserva = new ArrayList<>();

        // Utilizo un iterador para recorrer el ArrayList y agregar las reservas del tipo de habitación al nuevo ArrayList
        Iterator<Reserva> iterator = coleccionReservas.iterator();
        while (iterator.hasNext()) {
            Reserva actual = iterator.next();
            if (actual.getHabitacion().getTipoHabitacion().equals(tipoHabitacion)) {
                miReserva.add(new Reserva(actual));
            }
        }

        return miReserva;
    }

    // Para obtener las reservas futuras de una habitación
    public List<Reserva> getReservasFuturas(Habitacion habitacion) {
        if (habitacion == null) {
            throw new NullPointerException("ERROR: No se pueden buscar reservas de una habitación nula.");
        }

        LocalDate fechaActual = LocalDate.now();
        List<Reserva> reservasFuturas = new ArrayList<>();

        // Utilizo  un iterador para recorrer el ArrayList y agregar las reservas futuras de la habitación al nuevo ArrayList
        Iterator<Reserva> iterator = coleccionReservas.iterator();
        while (iterator.hasNext()) {
            Reserva actual = iterator.next();
            if (actual.getHabitacion().equals(habitacion) && actual.getFechaInicioReserva().isAfter(fechaActual)) {
                reservasFuturas.add(new Reserva(actual));
            }
        }

        return reservasFuturas;
    }

    // Para realizar el checkin de una reserva
    public void realizarCheckin(Reserva reserva, LocalDateTime fecha) {
        if (reserva == null || fecha == null) {
            throw new NullPointerException("ERROR: La reserva y la fecha no pueden ser nulas.");
        }

        if (fecha.isBefore(reserva.getFechaInicioReserva().atStartOfDay())) {
            throw new IllegalArgumentException("ERROR: La fecha del checkIn no puede ser anterior a la reserva.");
        }

        // Utilizo un iterador para buscar la reserva en el ArrayList
        Iterator<Reserva> iterator = coleccionReservas.iterator();
        while (iterator.hasNext()) {
            Reserva actual = iterator.next();
            if (actual.equals(reserva)) {
                actual.setCheckIn(fecha);
                return;
            }
        }

        // Si no encuentra la reserva, lanza una excepción
        throw new IllegalArgumentException("ERROR: No existe ninguna reserva como la indicada.");
    }

    // Para realizar el checkout de una reserva
    public void realizarCheckout(Reserva reserva, LocalDateTime fecha) {
        if (reserva == null || fecha == null) {
            throw new NullPointerException("ERROR: La reserva y la fecha no pueden ser nulas.");
        }

        if (reserva.getCheckIn() == null) {
            throw new NullPointerException("ERROR: No puedes hacer checkOut si el checkIn es nulo.");
        }

        if (fecha.isBefore(reserva.getFechaInicioReserva().atStartOfDay()) || fecha.isBefore(reserva.getCheckIn())) {
            throw new IllegalArgumentException("ERROR: La fecha del checkOut no puede ser anterior a la de inicio de reserva o antes del checkIn.");
        }

        // Utilizo un iterador para buscar la reserva en el ArrayList
        Iterator<Reserva> iterator = coleccionReservas.iterator();
        while (iterator.hasNext()) {
            Reserva actual = iterator.next();
            if (actual.equals(reserva)) {
                actual.setCheckOut(fecha);
                return;
            }
        }

        throw new IllegalArgumentException("ERROR: No existe ninguna reserva como la indicada.");
    }
}
