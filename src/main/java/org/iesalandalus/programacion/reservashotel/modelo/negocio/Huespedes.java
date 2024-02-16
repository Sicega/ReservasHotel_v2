package org.iesalandalus.programacion.reservashotel.modelo.negocio;

import org.iesalandalus.programacion.reservashotel.modelo.dominio.Huesped;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class Huespedes {

    private int capacidad;
    private int tamano;
    private Huesped [] coleccionHuespedes;

    /*1.1-Crea el constructor con par�metros que crear� una lista de la capacidad indicada en el
     par�metro e inicializar� los atributos de la clase a los valores correspondientes.*/

    public Huespedes(int capacidad){

        if(capacidad<=0){

            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }

        this.capacidad=capacidad;

        this.tamano=0;

        coleccionHuespedes = new Huesped[capacidad];

    }

    /*1.2-El m�todo get devolver� una copia profunda de
    la colecci�n haciendo uso del m�todo copiaProfundaHuespedes.*/

    public Huesped [] get(){

        return copiaProfundaHuespedes();
    }

    private Huesped [] copiaProfundaHuespedes(){

        Huesped[] miHuesped = new Huesped[getCapacidad()];

        int indice = 0;

        for (Huesped huesped : coleccionHuespedes) {

            if (huesped != null) {

                miHuesped[indice] = new Huesped(huesped);

                indice++;
            }
        }

        return Arrays.copyOf(miHuesped, indice);
    }

    public int getCapacidad() {

        return capacidad;
    }

    public int getTamano() {

        return tamano;
    }

    /*1.3-Se permitir�n insertar hu�spedes no
    nulos al final de la colecci�n sin admitir repetidos.*/

    public void insertar (Huesped huesped) throws OperationNotSupportedException {

        if (huesped == null) {

            throw new NullPointerException("ERROR: No se puede insertar un hu�sped nulo.");
        }

        // Compruebo si el hu�sped ya existe en la colecci�n

        if (buscar(huesped) != null) {

            throw new OperationNotSupportedException("ERROR: Ya existe un hu�sped con ese dni.");
        }

        if(capacidad<0){

            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }

        if (tamano >= capacidad) {

            throw new OperationNotSupportedException ("ERROR: No se aceptan m�s hu�spedes.");
        }

        // Agrego el hu�sped al final de la colecci�n

        coleccionHuespedes[getTamano()] = new Huesped(huesped);

        tamano++;

    }

    private int buscarIndice(Huesped huesped)  {

        if(huesped==null){

            throw new NullPointerException("huesped null buscarindice");

        }

        for (int i = 0; i < tamano; i++) {

            if (coleccionHuespedes[i].equals(huesped)) {

                return i;
            }
        }

        return -1;
    }

    private boolean tamanoSuperado(int indice) {

        return indice >= tamano;
    }

    private boolean capacidadSuperada(int indice) {

        return indice >= capacidad;
    }

    /*1.4-El m�todo buscar devolver� un hu�sped si �ste
    se encuentra en la colecci�n y null en caso contrario.*/

    public Huesped buscar(Huesped huesped) {

        if(huesped==null){

            throw new NullPointerException("huesped buscar null");
        }

        int indice = buscarIndice(huesped);

        return (indice != -1) ? coleccionHuespedes[indice] : null;
    }

    /*1.5-El m�todo borrar, si el hu�sped se encuentra en la colecci�n, lo borrar� y desplazar�
    los elementos hacia la izquierda para dejar el array compactado.*/

    public void borrar (Huesped huesped) throws OperationNotSupportedException {

        if(huesped==null){

            throw new NullPointerException("ERROR: No se puede borrar un hu�sped nulo.");
        }

        int indice = buscarIndice(huesped);

        if(indice == -1){

            throw new  OperationNotSupportedException ("ERROR: No existe ning�n hu�sped como el indicado.");
        }


        desplazarUnaPosicionHaciaIzquierda(indice);

        tamano--;


    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {

        if(indice<0 || capacidadSuperada(indice)){

            throw new IllegalArgumentException("desplazar izquierda out of bounds");
        }

        for (int i = indice; i < tamano - 1; i++) {

            coleccionHuespedes[i] = coleccionHuespedes[i + 1];
        }
        // Garantiza que el �ltimo elemento se establece en null

        coleccionHuespedes[tamano - 1] = null;
    }




}
