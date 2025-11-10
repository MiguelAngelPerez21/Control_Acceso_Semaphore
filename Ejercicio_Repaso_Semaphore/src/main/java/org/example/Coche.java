package org.example;

import java.util.Random;

public class Coche implements Runnable {

    // Variables
    // Variable de la clase Aparcamiento
    private final Aparcamiento Aparcamiento;
    private final String Nombre;
    private final Random Aleatorio = new Random();

    // Constructor
    public Coche(Aparcamiento aparcamiento, String nombre) {
        this.Aparcamiento = aparcamiento;
        this.Nombre = nombre;
    }

    @Override
    public void run() {
        try {
            // Llama a entrar para intentar aparcar
            Aparcamiento.entrar(Nombre);

            // Variable que simula un tiempo de entre 1-4 segundos simulando la espera de los coches
            long espera = 1000 + Aleatorio.nextInt(3000);

            // Dormimos el hilo el tiempo generado
            Thread.sleep(espera);

            // Catch para el control de excepciones
        } catch (InterruptedException e) {

            // Capturamos esta excepción que sucede si hay una interrupción del hilo
            Thread.currentThread().interrupt();

            // Mensaje para comprobar en que momento se interrumpe el hilo
            System.out.printf(Nombre  + " fue interrumpido.%n");

        // Creamos finally ya que se ejecuta incluso si hay una excepción
        } finally {

            // Llama a salir para liberar la plaza
            Aparcamiento.salir(Nombre);
        }
    }
}