package org.example;

import java.util.concurrent.Semaphore;

public class Aparcamiento {

    // Semáforo para controlar las plazas del aparcamiento
    private final Semaphore Semaforo;
    // Capacidad total del aparcamiento
    private final int Capacidad;
    // Contador de plazas ocupadas
    private int Ocupadas;

    // Constructor
    public Aparcamiento(int Capacidad) {
        this.Capacidad = Capacidad;
        this.Semaforo = new Semaphore(Capacidad, true); // fair=true asegura que los hilos esperen en orden FIFO
        this.Ocupadas = 0;
    }

    // Se usa para que un coche entre al aparcamiento
    public void entrar(String nombreCoche) throws InterruptedException {

        // Sentencia if para comprobar la disponibilidad
        // Si no hay plazas, el coche espera
        if (!Semaforo.tryAcquire()) {
            synchronized (this) {
                System.out.printf(nombreCoche + " está esperando...%n");
            }
            // Espera hasta que haya permiso
            Semaforo.acquire();
        }

        // Si hay plazas, el coche entra y se actualiza el contador
        synchronized (this) {
            // Incrementa el numero de coches ocupando plazas
            Ocupadas ++;
            System.out.printf(nombreCoche + " ha entrado. Plazas ocupadas: " + Ocupadas + "/" + Capacidad + "%n");
        }
    }

    public void salir(String nombreCoche) {
        synchronized (this) {

            // Liberar el permiso del semáforo indicando que hay una plaza libre
            Semaforo.release();

            // Actualizar el contador de ocupadas según los permisos dispobinles
            Ocupadas = Capacidad - Semaforo.availablePermits();

            System.out.printf(nombreCoche + " ha SALIDO. Plazas ocupadas: " + Ocupadas + "/" + Capacidad + "%n");
        }
    }
}