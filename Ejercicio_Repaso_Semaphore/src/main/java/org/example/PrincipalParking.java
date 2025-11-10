package org.example;

public class PrincipalParking {
    public static void main(String[] args) throws InterruptedException {

        //Variables
        final int Plazas = 3;
        final int Coches = 7;

        // Creamos un aparcamiento con el número de plazas definido
        Aparcamiento aparcamiento = new Aparcamiento(Plazas);

        // Creamos un array de hilos para los coches con el número definido
        Thread[] Hilos = new Thread[Coches];

        // Bucle for para crear los hilos de los coches
        for (int i = 0; i < Coches; i++) {

            // Genera un nombre para cada coche, crea el hilo y lo asigna al array
            String Nombre = "Coche-" + (i + 1);
            Hilos[i] = new Thread(new Coche(aparcamiento, Nombre), Nombre);
        }

        // Bucle for para iniciar todos los hilos
        for (Thread h : Hilos) h.start();

        // Bucle for para esperar a que cada hilo termine
        for (Thread h : Hilos) h.join();

        System.out.println("Simulación finalizada.");
    }
}