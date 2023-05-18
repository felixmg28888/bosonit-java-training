package com.bosonit.formacion;

import java.io.IOException;

public class Ejercicio {
//    public static void creaListaPersonas() throws InvalidLineFormatException, IOException {
//        List<Person> listPersonas =Metodos.creaListPersonas();
//    }

    public static void primeraParteEjercicio() throws InvalidLineFormatException, IOException {
        Metodos.imprimeListPersonas(Metodos.creaListPersonas());
    }

    public static void apartadoA() throws InvalidLineFormatException, IOException {
        Metodos.imprimeListPersonasFiltradaPorEdad(Metodos.creaListPersonas());
    }

    public static void apartadoB() throws InvalidLineFormatException, IOException {
        Metodos.imprimeListaPersonasFiltraPrimeraLetra(Metodos.creaListPersonas());
    }

    public static void apartadoC() throws InvalidLineFormatException, IOException {
        Metodos.imprimeListPersonasFiltradaPorEdadYMadrid(Metodos.creaListPersonas());
    }

    public static void apartadoD() throws InvalidLineFormatException, IOException {
        Metodos.imprimeListPersonasFiltradaPorEdadYBarcelona(Metodos.creaListPersonas());
    }
}
