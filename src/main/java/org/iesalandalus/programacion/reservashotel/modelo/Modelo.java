package org.iesalandalus.programacion.reservashotel.modelo;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.*;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;

public class Modelo {

    private static final int CAPACIDAD=10; //Inicializo la constante CAPACIDAD

    private Huespedes huespedes;
    private Habitaciones habitaciones;
    private Reservas reservas;

    //M�TODOS CONSTRUCTOR, COMENZAR Y TERMINAR

    public Modelo(){ // Creo el constructor que inicializa las instancias de las clases de negocio

        huespedes = new Huespedes(CAPACIDAD);
        habitaciones = new Habitaciones();
        reservas = new Reservas(CAPACIDAD);

    }

    public void comenzar(){

        //Dejo el m�todo comenzar vac�o

    }

    public void terminar(){ //Simplemente avisa por pantalla que el modelo ha terminado

        System.out.println("El modelo ha terminado.");

    }

    // M�todos para gestionar huespedes

    public void insertar(Huesped huesped) throws OperationNotSupportedException {

        huespedes.insertar(huesped);

    }

    public Huesped buscar(Huesped huesped){

        return huespedes.buscar(huesped);

    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException {

        huespedes.borrar(huesped);

    }

    public Huesped[] getHuespedes(){

        return huespedes.get();
    }

    // M�todos para gestionar habitaciones

    public void insertar(Habitacion habitacion) throws OperationNotSupportedException {

        habitaciones.insertar(habitacion);

    }

    public Habitacion buscar(Habitacion habitacion){

        return habitaciones.buscar(habitacion);
    }

    public void borrar(Habitacion habitacion) throws OperationNotSupportedException {

        habitaciones.borrar(habitacion);
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones.get();
    }

    public List<Habitacion> getHabitaciones(TipoHabitacion tipoHabitacion) {
        return habitaciones.get(tipoHabitacion);
    }

    // M�todos para gestionar reservas

    public void insertar(Reserva reserva) throws OperationNotSupportedException {

        reservas.insertar(reserva);

    }

    public Reserva buscar(Reserva reserva){

        return reservas.buscar(reserva);

    }

    public void borrar(Reserva reserva) throws OperationNotSupportedException {

        reservas.borrar(reserva);

    }

    public Reserva [] getReservas(){

        return reservas.get();

    }

    public Reserva [] getReservas(Huesped huesped){

        return reservas.getReservas(huesped);
    }

    public Reserva [] getReservas(TipoHabitacion tipoHabitacion){

        return reservas.getReservas(tipoHabitacion);
    }

    public Reserva [] getReservasFuturas(Habitacion habitacion){

        return reservas.getReservasFuturas(habitacion);
    }

    //M�todos para gestionar checkIn y checkOut

    public void realizarCheckIn(Reserva reserva, LocalDateTime fecha) {

        reservas.realizarCheckin(reserva, fecha);

    }

    public void realizarCheckOut(Reserva reserva, LocalDateTime fecha) {

        reservas.realizarCheckout(reserva, fecha);

    }


}
