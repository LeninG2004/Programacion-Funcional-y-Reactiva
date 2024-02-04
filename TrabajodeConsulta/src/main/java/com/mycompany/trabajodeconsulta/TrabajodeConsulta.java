package com.mycompany.trabajodeconsulta;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Lenin Gonzalez
 */
public class TrabajodeConsulta {


    // Función que simula una operación asincrónica
    public static String obtenerDato(int id) {
        try {
            Thread.sleep(1000); // Simulando una operación que toma tiempo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Dato " + id;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Integer> ids = Arrays.asList(1, 2, 3, 4, 5);

        // Usando Observable para operaciones asincrónicas
        System.out.println("Operación con Observable:");
        Observable.fromIterable(ids)
                .flatMap(id ->
                        Observable.fromCallable(() -> obtenerDato(id))
                                .subscribeOn(Schedulers.io()) // Ejecutar en un hilo de fondo
                )
                .blockingSubscribe(System.out::println); // blockingSubscribe para esperar la finalización

        // Pausa para permitir que las operaciones asincrónicas se completen
        Thread.sleep(2000);
    }
}
