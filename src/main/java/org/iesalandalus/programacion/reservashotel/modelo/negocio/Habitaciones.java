package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Habitacion;
import org.iesalandalus.programacion.reservashotel.modelo.dominio.TipoHabitacion;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class Habitaciones {

    private int capacidad;
    private int tamano;
    private Habitacion [] coleccionHabitaciones;

    //M�TODOS

    public Habitaciones(int capacidad){

        if(capacidad<=0){ // Validaci�n de la capacidad

            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }

        // Inicializaci�n de los atributos

        this.capacidad=capacidad;

        this.tamano=0;

        coleccionHabitaciones = new Habitacion [capacidad];
    }

    public Habitacion [] get(){ //M�todo que devuelve la copia profunda de las habitaciones

        return copiaProfundaHabitaciones();
    }

    private Habitacion [] copiaProfundaHabitaciones(){ //M�todo que realiza la copia profunda de las habitaciones

        Habitacion[] miHabitacion = new Habitacion[getCapacidad()];

        int indice = 0;

        for (Habitacion habitacion : coleccionHabitaciones) { // Itero sobre la colecci�n de habitaciones

            if (habitacion != null) {

                miHabitacion[indice] = new Habitacion(habitacion); // Creao una copia profunda de la habitaci�n y lo almaceno en el nuevo array

                indice++; //Se actualiza el �ndice
            }
        }

        return Arrays.copyOf(miHabitacion, indice); // Devuelve un nuevo array con las habitaciones copiadas
    }

    // M�todo para obtener las habitaciones de un tipo (Simple, doble, etc...)

    public Habitacion[] get(TipoHabitacion tipoHabitacion) {

        Habitacion[] habitacionesTipo = new Habitacion[capacidad];

        int indice = 0;


        for (int i = 0; i < tamano; i++) { //Recorro la colecci�n de habitaciones

            if (coleccionHabitaciones[i].getTipoHabitacion().equals(tipoHabitacion)) {

                habitacionesTipo[indice] = new Habitacion(coleccionHabitaciones[i]); //Creo una copia profunda de la habitacion

                indice++;
            }
        }

        return Arrays.copyOf(habitacionesTipo, indice); //Devuelvo un nuevo array con la copia de las habitaciones por tipo
    }

    public int getTamano() { // M�todo para obtener el tama�o actual de la colecci�n

        return tamano;
    }

    public int getCapacidad() { // M�todo para obtener la capacidad de la colecci�n

        return capacidad;
    }

    // M�todo para insertar una nueva habitaci�n

    public void insertar (Habitacion habitacion) throws OperationNotSupportedException {

        if (habitacion == null) { //Verifico que la habitaci�n no sea nula

            throw new NullPointerException("ERROR: No se puede insertar una habitaci�n nula.");
        }

        // Compruebo si la habitaci�n ya existe en la colecci�n

        if (buscar(habitacion) != null) {

            throw new OperationNotSupportedException("ERROR: Ya existe una habitaci�n con ese identificador.");
        }


        if (tamano >= capacidad) {  // Compruebo si se ha alcanzado la capacidad m�xima

            throw new OperationNotSupportedException ("ERROR: No se aceptan m�s habitaciones.");
        }

        // Agrego la habitaci�n al final de la colecci�n

        coleccionHabitaciones[getTamano()] = new Habitacion(habitacion);

        tamano++; //El incremental actualiza el tama�o
    }

    private int buscarIndice(Habitacion habitacion)  { // M�todo privado para buscar el �ndice de una habitaci�n


        for (int i = 0; i < tamano; i++) {

            if (coleccionHabitaciones[i].equals(habitacion)) {

                return i;
            }
        }
        return -1;
    }

    private boolean tamanoSuperado(int indice) { // M�todo para verificar si un �ndice est� fuera del tama�o actual

        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice) { // M�todo para verificar si un �ndice est� fuera de la capacidad

        return indice >= capacidad;
    }

    public Habitacion buscar(Habitacion habitacion) { // M�todo para buscar una habitaci�n en la colecci�n
        if (habitacion==null){

            throw new NullPointerException("error nulo");
        }
        int indice = buscarIndice(habitacion);   //Busca el �ndice de la habitaci�n

        return (indice != -1) ? coleccionHabitaciones[indice] : null; // Devuelve la habitaci�n encontrada o null si no se encontr�
    }

    public void borrar (Habitacion habitacion) throws OperationNotSupportedException { // M�todo para borrar una habitaci�n de la colecci�n

        if(habitacion==null){ //Verifico que la habitaci�n no sea nula

            throw new NullPointerException("ERROR: No se puede borrar una habitaci�n nula.");
        }

        int indice = buscarIndice(habitacion); //Busca el �ndice de la habitaci�n

        if(indice == -1){ //Comprueba si se encontr� la habitaci�n

            throw new  OperationNotSupportedException ("ERROR: No existe ninguna habitaci�n como la indicada.");
        }



        desplazarUnaPosicionHaciaIzquierda(indice); // Desplaza las habitaciones una posici�n a la izquierda

        tamano--; // Decrementa el tama�o de la colecci�n


    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) { // M�todo para desplazar las habitaciones una posici�n a la izquierda

        if(indice<0 || capacidadSuperada(indice)){ // Valida que el �ndice no sea negativo y no est� fuera de la capacidad

            throw new IllegalArgumentException("desplazarunaPosicion no puede ser menor que cero");
        }

        for (int i = indice; i < tamano - 1; i++) { // Itera desde el �ndice hasta el tama�o - 1

            coleccionHabitaciones[i] = coleccionHabitaciones[i + 1];  // Desplaza la habitaci�n actual a la posici�n anterior
        }
        // Garantiza que el �ltimo elemento se establece en null para evitar duplicados

        coleccionHabitaciones[tamano - 1] = null;
    }
}
