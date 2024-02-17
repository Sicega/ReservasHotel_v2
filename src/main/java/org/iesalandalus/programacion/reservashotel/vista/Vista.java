package org.iesalandalus.programacion.reservashotel.vista;

import org.iesalandalus.programacion.reservashotel.controlador.Controlador;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;
import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class Vista {

    private static Controlador controlador;

    //Creo método setControlador

    public void setControlador(Controlador controlador) {

        if (controlador != null) {

            this.controlador = controlador;
        }
    }

    public void comenzar(){ // Inicia la ejecución de la vista

        Opcion opcion;

        do {
            Consola.mostrarMenu(); // Muestra el menú en la consola

            opcion = Consola.elegirOpcion(); // Lee la opción elegida por el usuario

            ejecutarOpcion(opcion); // Ejecuta la opción seleccionada

        } while (opcion != Opcion.SALIR);

    }

    public void terminar() { // Finaliza la ejecución de la vista

        System.out.println("¡Hasta luego!"); //Mensaje de salida de despedida
    }


//MÉTODOS MOVIDOS DEL MAINAPP

    private static void ejecutarOpcion(Opcion opcion){ // Ejecuta la opción seleccionada

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
            case CONSULTAR_DISPONIBILIDAD: //Modifico para añadir mensajes por consola personalizados en este método

                System.out.println("Introduce el tipo de habitación: ");
                TipoHabitacion tipoHabitacionEscogida = Consola.leerTipoHabitacion();

                System.out.println("Introduce la fecha de inicio de reserva: ");
                LocalDate fechaInicioEscogida = Consola.leerFecha();

                System.out.println("Introduce la fecha de fin de reserva: ");
                LocalDate fechaFinEscogida = Consola.leerFecha();


                consultarDisponibilidad(tipoHabitacionEscogida,fechaInicioEscogida,fechaFinEscogida);


                break;
            case REALIZAR_CHECKIN: //Añado realizar checkIn
                realizarCheckin();
                break;
            case REALIZAR_CHECKOUT: //Añado realizar checkOut
                realizarCheckout();
                break;
            case SALIR:
                break;
            default:
                System.out.println("Opción no válida.");
        }

    }

    private static void insertarHuesped(){ // Inserta un nuevo huésped


        try { //Corrijo tener el método entero recogido en el try catch y así hago con los demás

            Huesped nuevoHuesped = Consola.leerHuesped();

            controlador.insertar(nuevoHuesped);


            System.out.println("Huésped insertado correctamente.");
        }
        catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {

            System.out.println(e.getMessage());
        }

    }

    private static void buscarHuesped(){ // Busca un huésped por DNI

        try {

            Huesped huesped = Consola.leerHuespedPorDni();

            Huesped huespedEncontrado = controlador.buscar(huesped);

            System.out.println(huespedEncontrado);

        }catch (IllegalArgumentException|NullPointerException e){

            System.out.println(e.getMessage());

        }


    }

    private static void borrarHuesped(){ // Borra un huésped por DNI

        try {

            Huesped huesped = Consola.leerHuespedPorDni();

            controlador.borrar(huesped);

            System.out.println("Huésped borrado correctamente.");

        } catch (IllegalArgumentException | NullPointerException | OperationNotSupportedException e) {

            System.out.println(e.getMessage());
        }

    }

    private static void mostrarHuespedes() { // Muestra todos los huéspedes ordenados de la A a la Z

        // Obtengo la lista de huéspedes desde la clase controlador
        List<Huesped> listaHuespedes = controlador.getHuespedes();

        if (!listaHuespedes.isEmpty()) {

            // Ordenarla lista alfabéticamente utilizando Comparator comparing
            Iterator<Huesped> iterator = listaHuespedes.stream()
                    .sorted(Comparator.comparing(Huesped::getNombre))
                    .iterator();

            while (iterator.hasNext()) { //Recorro la lista con un iterador
                System.out.println(iterator.next());
            }

        } else { //Mensaje por consola en caso de no haber huéspedes registrados
            System.out.println("No hay huéspedes registrados.");
        }
    }


    private static void insertarHabitacion(){ // Inserta una nueva habitación

        try {

            Habitacion nuevaHabitacion = Consola.leerHabitacion();

            controlador.insertar(nuevaHabitacion);

            System.out.println("Habitación insertada correctamente.");

        } catch (IllegalArgumentException|OperationNotSupportedException|NullPointerException e) {

            System.out.println(e.getMessage());
        }

    }

    private static void buscarHabitacion(){ // Busca una habitación por identificador

        Habitacion habitacionEncontrada = Consola.leerHabitacionPorIdentificador();

        try {

            habitacionEncontrada = controlador.buscar(habitacionEncontrada);

        }catch (NullPointerException | IllegalArgumentException e){

            System.out.println(e.getMessage());
        }

        if (habitacionEncontrada != null) {

            System.out.println(habitacionEncontrada);

        } else {

            System.out.println("No se encontró la habitación.");
        }

    }

    private static void borrarHabitacion(){ // Borra una habitación por identificador

        try {

            controlador.borrar(Consola.leerHabitacionPorIdentificador());

            System.out.println("Habitación borrada correctamente.");

        } catch (IllegalArgumentException|NullPointerException|OperationNotSupportedException e) {

            System.out.println(e.getMessage());
        }

    }

    private static void mostrarHabitaciones() {

        // Obtengo la lista de habitaciones desde la clase controlador
        List<Habitacion> listaHabitaciones = controlador.getHabitaciones();

        if (!listaHabitaciones.isEmpty()) {

            /* Ordena la lista primero por planta y después por puerta en orden ascendente
            * En caso de empatar en planta, con then comparing ordena por puerta*/
            Iterator<Habitacion> iterator = listaHabitaciones.stream()
                    .sorted(Comparator.comparing(Habitacion::getPlanta).thenComparing(Habitacion::getPuerta))
                    .iterator();

            while (iterator.hasNext()) { //Recorro la lista con un iterador
                System.out.println(iterator.next());
            }

        } else {
            System.out.println("No hay habitaciones registradas.");
        }
    }


    private static void insertarReserva(){ // Inserta una nueva reserva

        try {

            Reserva nuevaReserva = Consola.leerReserva();

            Huesped huespedIntroducido = nuevaReserva.getHuesped();

            huespedIntroducido = controlador.buscar(huespedIntroducido);

            Habitacion habitacionIntroducida = nuevaReserva.getHabitacion();

            habitacionIntroducida = controlador.buscar(habitacionIntroducida);

            nuevaReserva = new Reserva(huespedIntroducido, habitacionIntroducida,nuevaReserva.getRegimen(), nuevaReserva.getFechaInicioReserva(), nuevaReserva.getFechaFinReserva(), nuevaReserva.getNumeroPersonas());

            controlador.insertar(nuevaReserva);

            System.out.println("Reserva insertada correctamente.");

        } catch (IllegalArgumentException | OperationNotSupportedException|NullPointerException e) {

            System.out.println(e.getMessage());
        }

    }

    private static void listarReservas(Huesped huesped) { // Lista las reservas de un huésped

        // Obtengo la lista de reservas para el huésped desde la clase controlador
        List<Reserva> reservasHuesped = controlador.getReservas(huesped);

        if (!reservasHuesped.isEmpty()) {

            // Ordena la lista de reservas por fecha de inicio en orden descendente
            // En caso de empate, ordena por número de planta y puerta en orden ascendente

            Iterator<Reserva> iterator = reservasHuesped.stream()
                    .sorted(Comparator.comparing(Reserva::getFechaInicioReserva).reversed()
                            .thenComparing((reserva -> reserva.getHabitacion().getPlanta()))
                            .thenComparing((reserva -> reserva.getHabitacion().getPuerta())))
                    .iterator();

            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

        } else {
            System.out.println("No hay reservas para el huésped seleccionado.");
        }
    }


    private static void listarReservas(TipoHabitacion tipoHabitacion) { // Lista las reservas de un tipo de habitación

        List<Reserva> reservasTipoHabitacion = controlador.getReservas(tipoHabitacion);

        if (!reservasTipoHabitacion.isEmpty()) { //En caso de que la lista no esté vacía la recorro con un iterador
            Iterator<Reserva> iterator = reservasTipoHabitacion.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } else {
            System.out.println("No hay reservas para el tipo de habitación " + tipoHabitacion);
        }
    }


    private static List<Reserva> getReservasAnulables(List<Reserva> reservasAnular) {
        return reservasAnular.stream() // Utilizo stream para filtrar las reservas anulables
                // Filtro las reservas cuya fecha de inicio sea posterior a la fecha y hora actuales
                .filter(reserva -> reserva.getFechaInicioReserva().isAfter(ChronoLocalDate.from(LocalDateTime.now())))
                .collect(Collectors.toList()); // Con collect recojo el resultado del filtrado en una lista
    }

    private static void anularReserva() {

        Huesped huesped = Consola.leerHuespedPorDni();

        //Convierto reservasAnulables en una Arraylist

        List<Reserva> reservasAnulables = new ArrayList<>(controlador.getReservas(huesped));

        reservasAnulables = getReservasAnulables(reservasAnulables);

        if (reservasAnulables.isEmpty()) {

            System.out.println("No hay reservas para anular.");

        } else if (reservasAnulables.size() == 1) {

            System.out.println("¿Confirma la anulación de la reserva?" + reservasAnulables.get(0));

            if (Entrada.cadena().equalsIgnoreCase("si")) {

                try {

                    controlador.borrar(reservasAnulables.get(0));

                    System.out.println("Reserva anulada correctamente.");

                } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {

                    System.out.println(e.getMessage());
                }

            } else {

                System.out.println("Anulación cancelada.");
            }

        } else {

            int contador = 0;

            Iterator<Reserva> iterator = reservasAnulables.iterator();

            while (iterator.hasNext()) { // Recorro la lista con un iterador

                Reserva elemento = iterator.next();
                System.out.println(contador + " : " + elemento);

                contador++;
            }

            int indiceReserva;

            do {

                System.out.println("¿Qué reserva desea anular?");

                indiceReserva = Entrada.entero();

            } while (indiceReserva < 0 || indiceReserva >= reservasAnulables.size());

            try {
                controlador.borrar(reservasAnulables.get(indiceReserva));

                System.out.println("Reserva anulada correctamente.");

            } catch (IllegalArgumentException | OperationNotSupportedException | NullPointerException e) {

                System.out.println(e.getMessage());
            }
        }
    }



    private static void mostrarReservas() {

        // Obtengo la lista de reservas desde la clase controlador

        List<Reserva> listaReservas = controlador.getReservas();

        if (!listaReservas.isEmpty()) {

            Iterator<Reserva> iterator = listaReservas.stream() // Con stream ordeno la lista y utilizo el método sorted para ordenar por fecha de inicio en orden descendente
                    .sorted(Comparator.comparing(Reserva::getFechaInicioReserva).reversed()
                            .thenComparing(reserva -> { // En caso de empate, con then comparing se ordena por habitación en orden ascendente
                                if (reserva.getHabitacion() != null) {
                                    // Ordena por número de planta y puerta en orden ascendente
                                    return reserva.getHabitacion().getIdentificador();
                                }
                                return null;
                            }))
                    .iterator(); // Obtengo un iterador de la lista ordenada

            while (iterator.hasNext()) { // Itero sobre la lista ordenada con el iterador
                System.out.println(iterator.next());
            }
        } else {
            System.out.println("No hay reservas registradas.");
        }
    }


    private static int getNumElementosNoNulos(List<Reserva> reservas) {

        // Obtengo el número de elementos no nulos en la lista de reservas

        int contador = 0;

        Iterator<Reserva> iterator = reservas.iterator();

        while (iterator.hasNext()) {

            Reserva elemento = iterator.next();

            if (elemento != null) {
                contador++;
            }
        }

        return contador;
    }


    private static Habitacion consultarDisponibilidad(TipoHabitacion tipoHabitacion, LocalDate fechaInicioReserva, LocalDate fechaFinReserva) {

        boolean tipoHabitacionEncontrada = false;

        Habitacion habitacionDisponible = null;

        List<Habitacion> habitacionesTipoSolicitado = controlador.getHabitaciones(tipoHabitacion);

        if (habitacionesTipoSolicitado == null || habitacionesTipoSolicitado.isEmpty()) {

            return habitacionDisponible;
        }

        for (Iterator<Habitacion> iterator = habitacionesTipoSolicitado.iterator(); iterator.hasNext() && !tipoHabitacionEncontrada; ) {

            Habitacion habitacion = iterator.next();

            if (habitacion != null) {

                List<Reserva> reservasFuturas = new ArrayList<>(controlador.getReservasFuturas(habitacion));

                if (reservasFuturas.isEmpty()) {

                    habitacionDisponible = new Habitacion(habitacion);

                    tipoHabitacionEncontrada = true;
                } else {
                    // Ordeno de mayor a menor las reservas futuras por fecha de fin de la reserva.
                    reservasFuturas.sort(Comparator.comparing(Reserva::getFechaFinReserva).reversed());

                    if (fechaInicioReserva.isAfter(reservasFuturas.get(0).getFechaFinReserva())) {

                        habitacionDisponible = new Habitacion(habitacion);

                        tipoHabitacionEncontrada = true;
                    }

                    if (!tipoHabitacionEncontrada) {
                        // Ordeno de menor a mayor las reservas futuras por fecha de inicio de la reserva.
                        reservasFuturas.sort(Comparator.comparing(Reserva::getFechaInicioReserva));

                        if (fechaFinReserva.isBefore(reservasFuturas.get(0).getFechaInicioReserva())) {

                            habitacionDisponible = new Habitacion(habitacion);

                            tipoHabitacionEncontrada = true;
                        }
                    }

                    // Recorro la lista de reservas futuras para verificar si las fechas solicitadas están disponibles
                    if (!tipoHabitacionEncontrada) {

                        for (Iterator<Reserva> reservaIterator = reservasFuturas.iterator(); reservaIterator.hasNext() && !tipoHabitacionEncontrada; ) {

                            Reserva reservaAnterior = reservaIterator.next();

                            if (reservaIterator.hasNext()) {

                                Reserva reservaActual = reservaIterator.next();

                                if (fechaInicioReserva.isAfter(reservaAnterior.getFechaFinReserva()) && fechaFinReserva.isBefore(reservaActual.getFechaInicioReserva()))
                                {
                                    habitacionDisponible = new Habitacion(habitacion);

                                    tipoHabitacionEncontrada = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return habitacionDisponible;
    }



    private static void realizarCheckin() {

        System.out.println("Introduce el DNI del huésped:");

        Huesped huesped = Consola.leerHuespedPorDni();

        System.out.println("Introduce la fecha (dd/MM/yyyy) y la hora (hh:mm:ss) del checkin:");

        LocalDateTime fechaCheckin = Consola.leerFechaHora(Entrada.cadena());

        List<Reserva> reservasHuesped = controlador.getReservas(huesped);

        if (reservasHuesped.isEmpty()) {

            System.out.println("El huésped no tiene reservas.");

            return;
        }

        System.out.println("Reservas del huésped:");

        Iterator<Reserva> iterator = reservasHuesped.iterator();

        int i = 0;

        while (iterator.hasNext()) { //Recorro la lista de reservas con un iterador

            System.out.println(i + ": " + iterator.next());

            i++;
        }

        int indiceReserva;

        do {
            System.out.println("¿Qué reserva desea hacer checkin?");

            indiceReserva = Entrada.entero();

        } while (indiceReserva < 0 || indiceReserva >= reservasHuesped.size());

        try {
            controlador.realizarCheckIn(reservasHuesped.get(indiceReserva), fechaCheckin);

            System.out.println("Checkin realizado correctamente.");

        } catch (IllegalArgumentException | NullPointerException e) {

            System.out.println(e.getMessage());
        }
    }


    private static void realizarCheckout() {

        System.out.println("Introduce el DNI del huésped:");

        Huesped huesped = Consola.leerHuespedPorDni();

        System.out.println("Introduce la fecha (dd/MM/yyyy) y la hora (hh:mm:ss) del checkout:");

        LocalDateTime fechaCheckout = Consola.leerFechaHora(Entrada.cadena());

        List<Reserva> reservasHuesped = controlador.getReservas(huesped);

        if (reservasHuesped.isEmpty()) {

            System.out.println("El huésped no tiene reservas.");

            return;
        }

        System.out.println("Reservas del huésped:");

        Iterator<Reserva> iterator = reservasHuesped.iterator();

        int i = 0;

        while (iterator.hasNext()) { //Igual que en el método realizarCheckIn recorro la lista de reservas con un iterador

            Reserva reserva = iterator.next();

            System.out.println(i + ": " + reserva);

            i++;
        }

        int indiceReserva;

        do {
            System.out.println("¿Qué reserva desea hacer checkout?");

            indiceReserva = Entrada.entero();

        } while (indiceReserva < 0 || indiceReserva >= reservasHuesped.size());

        try {
            controlador.realizarCheckOut(reservasHuesped.get(indiceReserva), fechaCheckout);

            System.out.println("Checkout realizado correctamente.");

        } catch (IllegalArgumentException | NullPointerException e) {

            System.out.println(e.getMessage());
        }
    }
}
