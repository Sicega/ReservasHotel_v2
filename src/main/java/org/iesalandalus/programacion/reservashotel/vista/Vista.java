package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;
import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class Vista {

    private static Controlador controlador;

    //Creo m�todo setControlador

    public void setControlador(Controlador controlador) {

        if (controlador != null) {

            this.controlador = controlador;
        }
    }

    public void comenzar(){ // Inicia la ejecuci�n de la vista

        Opcion opcion;

        do {
            Consola.mostrarMenu(); // Muestra el men� en la consola

            opcion = Consola.elegirOpcion(); // Lee la opci�n elegida por el usuario

            ejecutarOpcion(opcion); // Ejecuta la opci�n seleccionada

        } while (opcion != Opcion.SALIR);

    }

    public void terminar() { // Finaliza la ejecuci�n de la vista

        System.out.println("�Hasta luego!"); //Mensaje de salida de despedida
    }


//M�TODOS MOVIDOS DEL MAINAPP

    private static void ejecutarOpcion(Opcion opcion){ // Ejecuta la opci�n seleccionada

        switch (opcion) {
            case INSERTAR_HUESPED:
                insertarHuesped();
                break;
            case BUSCAR_HUESPED:
                buscarHuesped();
                break;
            case BORRAR_HUESPED:
                borrarHuesped();
                break;
            case MOSTRAR_HUESPEDES:
                mostrarHuespedes();
                break;
            case INSERTAR_HABITACION:
                insertarHabitacion();
                break;
            case BUSCAR_HABITACION:
                buscarHabitacion();
                break;
            case BORRAR_HABITACION:
                borrarHabitacion();
                break;
            case MOSTRAR_HABITACIONES:
                mostrarHabitaciones();
                break;
            case INSERTAR_RESERVA:
                insertarReserva();
                break;
            case ANULAR_RESERVA:
                anularReserva();
                break;
            case MOSTRAR_RESERVAS:
                mostrarReservas();
                break;
            case CONSULTAR_DISPONIBILIDAD:
                consultarDisponibilidad(null,null,null);
                break;
            case REALIZAR_CHECKIN: //A�ado realizar checkIn
                realizarCheckin();
                break;
            case REALIZAR_CHECKOUT: //A�ado realizar checkOut
                realizarCheckout();
                break;
            case SALIR:
                break;
            default:
                System.out.println("Opci�n no v�lida.");
        }

    }

    private static void insertarHuesped(){ // Inserta un nuevo hu�sped


        try {

            Huesped nuevoHuesped = Consola.leerHuesped();

            controlador.insertar(nuevoHuesped);


            System.out.println("Hu�sped insertado correctamente.");
        }
        catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {

            System.out.println(e.getMessage());
        }

    }

    private static void buscarHuesped(){ // Busca un hu�sped por DNI

        try {

            Huesped huesped = Consola.leerHuespedPorDni();

            Huesped huespedEncontrado = controlador.buscar(huesped);

            System.out.println(huespedEncontrado);

        }catch (IllegalArgumentException|NullPointerException e){

            System.out.println(e.getMessage());

            System.out.println("No se encontr� un hu�sped con ese DNI");

        }


    }

    private static void borrarHuesped(){ // Borra un hu�sped por DNI

        try {

            Huesped huesped = Consola.leerHuespedPorDni();

            controlador.borrar(huesped);

            System.out.println("Hu�sped borrado correctamente.");

        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {

            System.out.println(e.getMessage());
        }

    }

    private static void mostrarHuespedes(){ // Muestra todos los hu�spedes


        Huesped[] listaHuespedes = controlador.getHuespedes();

        if (listaHuespedes.length > 0) {



            for (Huesped huesped : listaHuespedes) {

                System.out.println(huesped);
            }

        } else {

            System.out.println("No hay hu�spedes registrados.");
        }

    }

    private static void insertarHabitacion(){ // Inserta una nueva habitaci�n

        try {

            Habitacion nuevaHabitacion = Consola.leerHabitacion();

            controlador.insertar(nuevaHabitacion);

            System.out.println("Habitaci�n insertada correctamente.");

        } catch (IllegalArgumentException|OperationNotSupportedException|NullPointerException e) {

            System.out.println(e.getMessage());
        }

    }

    private static void buscarHabitacion(){ // Busca una habitaci�n por identificador

        Habitacion habitacionEncontrada = Consola.leerHabitacionPorIdentificador();

        if (habitacionEncontrada != null) {

            System.out.println(habitacionEncontrada);

        } else {

            System.out.println("No se encontr� la habitaci�n.");
        }

    }

    private static void borrarHabitacion(){ // Borra una habitaci�n por identificador

        try {

            controlador.borrar(Consola.leerHabitacionPorIdentificador());

            System.out.println("Habitaci�n borrada correctamente.");

        } catch (IllegalArgumentException|NullPointerException|OperationNotSupportedException e) {

            System.out.println(e.getMessage());
        }

    }

    private static void mostrarHabitaciones() {
        List<Habitacion> listaHabitaciones = controlador.getHabitaciones();

        if (!listaHabitaciones.isEmpty()) {
            for (Habitacion habitacion : listaHabitaciones) {
                System.out.println(habitacion);
            }
        } else {
            System.out.println("No hay habitaciones registradas.");
        }
    }

    private static void insertarReserva(){ // Inserta una nueva reserva

        try {

            Reserva nuevaReserva = Consola.leerReserva();

            controlador.insertar(nuevaReserva);

            System.out.println("Reserva insertada correctamente.");

        } catch (IllegalArgumentException | OperationNotSupportedException|NullPointerException e) {

            System.out.println(e.getMessage());
        }

    }

    private static void listarReservas(Huesped huesped){ // Lista las reservas de un hu�sped

        Reserva[] reservasHuesped =  controlador.getReservas(huesped);

        if (reservasHuesped.length > 0) {

            for (Reserva reserva : reservasHuesped) {

                System.out.println(reserva);
            }
        } else {

            System.out.println("El hu�sped no tiene reservas.");
        }

    }

    private static void listarReservas(TipoHabitacion tipoHabitacion){ // Lista las reservas de un tipo de habitaci�n


        Reserva[] reservasTipoHabitacion = controlador.getReservas(tipoHabitacion);

        if (reservasTipoHabitacion.length > 0) {

            for (Reserva reserva : reservasTipoHabitacion) {

                System.out.println(reserva);
            }
        } else {

            System.out.println("No hay reservas para el tipo de habitaci�n " + tipoHabitacion);
        }

    }

    private static Reserva [] getReservasAnulables( Reserva [] reservasAnular) { // Obtiene un array de reservas anulables

        Reserva [] arrayNuevo= new Reserva[reservasAnular.length];

        int j=0;

        for(int i=0; i<reservasAnular.length;i++){

            if(reservasAnular[i].getFechaInicioReserva().isAfter(LocalDate.now())){

                arrayNuevo[j]= new Reserva(reservasAnular[i]);

                j++;
            }
        }

        return arrayNuevo;
    }

    private static void anularReserva(){ // Anula una reserva comprobando antes que esa reserva existe


        Huesped huesped = Consola.leerHuespedPorDni();

        Reserva[] reservasAnulables = controlador.getReservas(huesped);

        reservasAnulables = getReservasAnulables(reservasAnulables);

        if (reservasAnulables.length == 0) {

            System.out.println("No hay reservas para anular.");

        } else if (reservasAnulables.length == 1) {

            System.out.println("�Confirma la anulaci�n de la reserva?" + reservasAnulables[0]);

            if (Entrada.cadena().equalsIgnoreCase("si")){

                try {

                    controlador.borrar(reservasAnulables[0]);
                    System.out.println("Reserva anulada correctamente.");

                } catch (IllegalArgumentException | OperationNotSupportedException|NullPointerException e) {

                    System.out.println(e.getMessage());
                }

            } else {

                System.out.println("Anulaci�n cancelada.");

            }
        } else {

            int contador=0;

            for(Reserva elemento : reservasAnulables){

                System.out.println(contador+ " : " + elemento);

                contador++;

            }

            int indiceReserva;

            do {

                System.out.println("�Qu� reserva desea anular?");

                indiceReserva = Entrada.entero();

            }while(indiceReserva<0 || indiceReserva > reservasAnulables.length);


            try {

                controlador.borrar(reservasAnulables[indiceReserva]);

                System.out.println("Reserva anulada correctamente.");

            } catch (IllegalArgumentException | OperationNotSupportedException| NullPointerException e) {

                System.out.println(e.getMessage());
            }
        }

    }

    private static void mostrarReservas(){ // Muestra todas las reservas

        Reserva [] listaReservas = controlador.getReservas();
        if (listaReservas.length > 0) {
            for (Reserva reserva : listaReservas) {
                System.out.println(reserva);
            }
        } else {
            System.out.println("No hay reservas registradas.");
        }

    }

    private static int getNumElementosNoNulos(Reserva [] reservas){

        // Obtiene el n�mero de elementos no nulos en un array de reservas

        int contador=0;

        for(Reserva elemento : reservas){

            if(elemento !=null){

                contador++;
            }
        }
        return contador;
    }

    private static Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva) {
        boolean tipoHabitacionEncontrada = false;
        Habitacion habitacionDisponible = null;

        List<Habitacion> habitacionesTipoSolicitado = Arrays.asList((Habitacion) controlador.getHabitaciones(tipoHabitacion));

        if (habitacionesTipoSolicitado == null || habitacionesTipoSolicitado.isEmpty()) {
            return habitacionDisponible;
        }

        for (Habitacion habitacion : habitacionesTipoSolicitado) {
            if (habitacion != null) {
                List<Reserva> reservasFuturas = Arrays.asList(controlador.getReservasFuturas(habitacion));

                if (reservasFuturas.isEmpty()) {
                    habitacionDisponible = new Habitacion(habitacion);
                    tipoHabitacionEncontrada = true;
                } else {
                    // Ordenamos de mayor a menor las reservas futuras por fecha de fin de la reserva.
                    reservasFuturas.sort(Comparator.comparing(Reserva::getFechaFinReserva).reversed());

                    if (fechaInicioReserva.isAfter(reservasFuturas.get(0).getFechaFinReserva())) {
                        habitacionDisponible = new Habitacion(habitacion);
                        tipoHabitacionEncontrada = true;
                    }

                    if (!tipoHabitacionEncontrada) {
                        // Ordenamos de menor a mayor las reservas futuras por fecha de inicio de la reserva.
                        reservasFuturas.sort(Comparator.comparing(Reserva::getFechaInicioReserva));

                        if (fechaFinReserva.isBefore(reservasFuturas.get(0).getFechaInicioReserva())) {
                            habitacionDisponible = new Habitacion(habitacion);
                            tipoHabitacionEncontrada = true;
                        }
                    }

                    // Recorremos la lista de reservas futuras para verificar si las fechas solicitadas est�n disponibles.
                    if (!tipoHabitacionEncontrada) {
                        Iterator<Reserva> iterator = reservasFuturas.iterator();
                        Reserva reservaAnterior = iterator.next();

                        while (iterator.hasNext() && !tipoHabitacionEncontrada) {
                            Reserva reservaActual = iterator.next();

                            if (fechaInicioReserva.isAfter(reservaAnterior.getFechaFinReserva()) &&
                                    fechaFinReserva.isBefore(reservaActual.getFechaInicioReserva())) {
                                habitacionDisponible = new Habitacion(habitacion);
                                tipoHabitacionEncontrada = true;
                            }

                            reservaAnterior = reservaActual;
                        }
                    }
                }
            }
        }

        return habitacionDisponible;
    }

    private static void realizarCheckin() { // Realiza el checkIn de un hu�sped comprobado que coincide con una reserva

        System.out.println("Introduce el DNI del hu�sped:");

        Huesped huesped = Consola.leerHuespedPorDni();

        System.out.println("Introduce la fecha (dd/MM/yyyy) y la hora (hh:mm:ss) del checkin:");

        LocalDateTime fechaCheckin = Consola.leerFechaHora(Entrada.cadena());

        Reserva[] reservasHuesped = controlador.getReservas(huesped);

        if (reservasHuesped.length == 0) { //Verifica si el huesped tiene reservas

            System.out.println("El hu�sped no tiene reservas.");

            return;
        }

        System.out.println("Reservas del hu�sped:");

        for (int i = 0; i < reservasHuesped.length; i++) {

            System.out.println(i + ": " + reservasHuesped[i]);
        }

        int indiceReserva;

        do {
            System.out.println("�Qu� reserva desea hacer checkin?");

            indiceReserva = Entrada.entero();

        } while (indiceReserva < 0 || indiceReserva >= reservasHuesped.length);

        try {
            controlador.realizarCheckIn(controlador.buscar(reservasHuesped[indiceReserva]), fechaCheckin);

            System.out.println("Checkin realizado correctamente.");

        } catch (IllegalArgumentException | NullPointerException e) {

            System.out.println(e.getMessage());
        }
    }

    private static void realizarCheckout() {

        System.out.println("Introduce el DNI del hu�sped:");

        Huesped huesped = Consola.leerHuespedPorDni();

        System.out.println("Introduce la fecha (dd/MM/yyyy) y la hora (hh:mm:ss) del checkout:");

        LocalDateTime fechaCheckout = Consola.leerFechaHora(Entrada.cadena());

        Reserva[] reservasHuesped = controlador.getReservas(huesped);

        if (reservasHuesped.length == 0) { // Verifica si el hu�sped tiene reservas

            System.out.println("El hu�sped no tiene reservas.");

            return;
        }

        System.out.println("Reservas del hu�sped:");

        for (int i = 0; i < reservasHuesped.length; i++) {

            System.out.println(i + ": " + reservasHuesped[i]); // Muestra todas las reservas del hu�sped
        }

        int indiceReserva;

        do {

            System.out.println("�Qu� reserva desea hacer checkout?"); //Salida de consola para que el usuario elija una reserva

            indiceReserva = Entrada.entero();

        } while (indiceReserva < 0 || indiceReserva >= reservasHuesped.length);

        try { // Realiza el checkout utilizando el controlador

            controlador.realizarCheckOut(controlador.buscar(reservasHuesped[indiceReserva]), fechaCheckout);

            System.out.println("Checkout realizado correctamente.");

        } catch (IllegalArgumentException | NullPointerException e) {

            System.out.println(e.getMessage());
        }
    }


}
