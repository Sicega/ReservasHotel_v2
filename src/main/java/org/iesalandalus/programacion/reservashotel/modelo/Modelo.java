package org.iesalandalus.programacion.reservashotel.modelo;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.reservashotel.modelo.negocio.*;
import java.util.List;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;

public class Modelo {


    //Elimino la constante CAPACIDAD

    private Huespedes huespedes;
    private Habitaciones habitaciones;
    private Reservas reservas;

    //MÉTODOS CONSTRUCTOR, COMENZAR Y TERMINAR

    public Modelo(){ // Creo el constructor que inicializa las instancias de las clases de negocio

        huespedes = new Huespedes();
        habitaciones = new Habitaciones();
        reservas = new Reservas();

    }

    public void comenzar(){

        //Dejo el método comenzar vacío

    }

    public void terminar(){ //Simplemente avisa por pantalla que el modelo ha terminado

        System.out.println("El modelo ha terminado.");

    }

    // Métodos para gestionar huespedes

    public void insertar(Huesped huesped) throws OperationNotSupportedException {

        huespedes.insertar(huesped);

    }

    public Huesped buscar(Huesped huesped){

        return huespedes.buscar(huesped);

    }

    public void borrar(Huesped huesped) throws OperationNotSupportedException {

        huespedes.borrar(huesped);

    }

    public Huesped[] getHuespedes() { //Modifico el método para adaptarlo a la lista

        List<Huesped> listaHuespedes = huespedes.get();

        return listaHuespedes.toArray(new Huesped[0]);
    }

    // Métodos para gestionar habitaciones

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

    // Métodos para gestionar reservas

    public void insertar(Reserva reserva) throws OperationNotSupportedException {

        reservas.insertar(reserva);

    }

    public Reserva buscar(Reserva reserva){

        return reservas.buscar(reserva);

    }

    public void borrar(Reserva reserva) throws OperationNotSupportedException {

        reservas.borrar(reserva);

    }

    //Modifico los métodos de getReservas para que hagan uso de las listas

    public Reserva[] getReservas() {
        List<Reserva> listaReservas = reservas.get();
        return listaReservas.toArray(new Reserva[0]);
    }

    public Reserva[] getReservas(Huesped huesped) {
        List<Reserva> listaReservas = reservas.getReservas(huesped);
        return listaReservas.toArray(new Reserva[0]);
    }

    public Reserva[] getReservas(TipoHabitacion tipoHabitacion) {
        List<Reserva> listaReservas = reservas.getReservas(tipoHabitacion);
        return listaReservas.toArray(new Reserva[0]);
    }

    public Reserva[] getReservasFuturas(Habitacion habitacion) {
        List<Reserva> listaReservas = reservas.getReservasFuturas(habitacion);
        return listaReservas.toArray(new Reserva[0]);
    }

    //Métodos para gestionar checkIn y checkOut

    public void realizarCheckIn(Reserva reserva, LocalDateTime fecha) {

        reservas.realizarCheckin(reserva, fecha);

    }

    public void realizarCheckOut(Reserva reserva, LocalDateTime fecha) {

        reservas.realizarCheckout(reserva, fecha);

    }


}
