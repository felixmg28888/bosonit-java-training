package com.bosonit.formacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Metodos {

    private static String validacion(String line) {

        long numDelimitadores = line.chars().filter(c -> c == ':').count();

        while (numDelimitadores < 2) {
            numDelimitadores += 1;

            line += ":0";

        }

        return line;
    }


    public static List<Person> creaListPersonas() throws IOException, InvalidLineFormatException {
        String filename = "src/main/resources/people.csv";//Creo ruta del archivo
        FileReader fr = new FileReader(filename);// Creo fileReader con la ruta por parámetro

        BufferedReader br = new BufferedReader(fr);//Creo br memoria le paso el fr por parámetro
        String line = br.readLine();//Creo String para leer el br línea a línea

        List<Person> listPersonas = new ArrayList<>();//Creo lista (fuera del while)

        while (line != null) {//Bucle while se repite hasta que línea esté vacía

            line = validacion(line);


//            //------------------------------------------------------------------------
//            //.chars() convierte un String en Stream-
//            //                         filter método de Stream con Lambda
//            //                                            predicado c donde c== contenga ':'comillas simples
//            long numDelimitadores = line.chars().filter(c -> c==':').count();
//            if (numDelimitadores == 0)
//                throw new InvalidLineFormatException("Faltan dos delimitadores (:)");
//            if (numDelimitadores == 1)
//                throw new InvalidLineFormatException("Falta un delimitador (:)");
//
//            //------------------------------------------------------------------------


            String[] datosPersona = line.split(":");//Creo array de String con las subcadenas obtenidas método String.Split(regex)
            //Al utilizar el .split() ya se referencia la longitud del array
            if (datosPersona[0].isBlank())//isBlank Returns true if the string is empty or contains only white space codepoints, otherwise false.
                throw new InvalidLineFormatException("El nombre es obligatorio");


            System.out.println(line);
            //------------------------------------------------------------------------

            Person person = new Person();//Crea objeto persona con constructro vacío

            person.setName(datosPersona[0]);//Setea primera variable de person con el campo 0 del array
            person.setTown(datosPersona[1]);//Setea town
            //Clase Optional .ofnullable permite darle valor nulo si Optional.of(null) saltaría NullPointer Excepcion
            //                                      if avanzado > 2  ? este código : else este otro código
            person.setAge(Optional.ofNullable(datosPersona.length > 2 ? Integer.parseInt(datosPersona[2]) : null));//Setea age con Optional.of....
            //Ahora que tengo una persona creada la llevo a una lista.


            listPersonas.add(person);//Añado la persona
            line = br.readLine();//Salto a la siguiente linea del bufer para repetir el proceso con el while


        }//Esto se repetirá hasta que el while detecte una línea vacía


        return listPersonas;
    }

    public static void imprimeListPersonas(List<Person> listPersonas) {
        List<Person> listPersona = listPersonas;
        System.out.println("----------------------------");
        System.out.println("Primera parte del ejercicio");
        System.out.println("----------------------------");
        for (Person p : listPersona) {
            System.out.println(
                    "Name: " + p.getName() +
                            "   Town: " + (p.getTown().equals("") || (p.getTown().equals("0")) ? "unknown" : p.getTown()) +
                            "   Age: " + ((p.getAge().isEmpty() || (p.getAge().get().equals(0)) ? "unknow" : p.getAge().get())));//isEmpty -- isPresent
        }
    }

    public static List<Person> imprimeListPersonasFiltradaPorEdad(List<Person> listPersonas) {

        //Creo un stream de la lista
        Stream<Person> sListPersonas = listPersonas.stream();
        //Creo una nueva lista de menos de 25 utilizando los metodos del stream sListPersona
        //                                      filtra Lambda predico    condicion 1     y condición 2 .tolist  lo añade a la nueva lista
        List<Person> menosDe25 = sListPersonas.filter(sLP -> sLP.getAge().isPresent() && sLP.getAge().get() < 25).toList();
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("Apartado A: Personas menores de 25 ");
        System.out.println("-----------------------------------");

        for (Person p : menosDe25) {
            System.out.println(
                    "Name: " + p.getName() +
                            "   Town: " + (p.getTown().equals("") ? "unknown" : p.getTown()) +
                            "   Age: " + ((p.getAge().isEmpty()) ? "unknow" : p.getAge().get()));//isEmpty -- isPresent
        }
        return menosDe25;
    }

    public static void imprimeListaPersonasFiltraPrimeraLetra(List<Person> listPersonas) {
        //Otra forma de instanciar list más directa .stream.....
        //                                                                ! not
        List<Person> primeraLetra = listPersonas.stream().filter(lPS -> !lPS.getName().startsWith("A")).toList();

        System.out.println();
        System.out.println("----------------------------------------------------");
        System.out.println("Apartado B: Personas cuyo nombre no empieza por la A");
        System.out.println("----------------------------------------------------");

        for (Person p : primeraLetra) {
            System.out.println(
                    "Name: " + p.getName() +
                            "   Town: " + (p.getTown().equals("") ? "unknown" : p.getTown()) +
                            "   Age: " + ((p.getAge().isEmpty()) ? "unknow" : p.getAge().get()));//isEmpty -- isPresent

        }


    }

    public static void imprimeListPersonasFiltradaPorEdadYMadrid(List<Person> listPersonas) {
        //Optional<T> findFirst()
        //Returns an Optional describing the first element of this stream, or an empty Optional if the stream is empty. If the stream has no encounter order, then any element may be returned.
        //.Find First retorna un optional, por suerte se                                                             puede volver a convertir en stream
        List<Person> listaOrdenada = listPersonas.stream().filter(lPS -> lPS.getTown().equals("Madrid")).findFirst().stream().toList();

        System.out.println();
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Apartado C: Primera persona de la lista de menos de 25 que vive en Madrid");
        System.out.println("-------------------------------------------------------------------------");

        for (Person p : listaOrdenada) {
            if (listaOrdenada.isEmpty()) {
                System.out.println("No hay ninguna persona de Madrid");

            } else {
                System.out.println(
                        "Name: " + p.getName() +
                                "   Town: " + (p.getTown().equals("") ? "unknown" : p.getTown()) +
                                "   Age: " + ((p.getAge().isEmpty()) ? "unknow" : p.getAge().get()));//isEmpty -- isPresent

            }
        }
    }

    public static void imprimeListPersonasFiltradaPorEdadYBarcelona(List<Person> listPersonas) {
        //Optional<T> findFirst()
        //Returns an Optional describing the first element of this stream, or an empty Optional if the stream is empty. If the stream has no encounter order, then any element may be returned.
        //.Find First retorna un optional, por suerte se                                                             puede volver a convertir en stream
        List<Person> listaOrdenada = listPersonas.stream().filter(lPS -> lPS.getTown().equals("Barcelona")).findFirst().stream().toList();

        System.out.println();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Apartado D: Primera persona de la lista de menos de 25 que vive en Barcelona");
        System.out.println("----------------------------------------------------------------------------");

        for (Person p : listaOrdenada) {
            if (listaOrdenada.isEmpty()) {
                System.out.println("No hay ninguna persona de Barcelona");

            } else {
                System.out.println(
                        "Name: " + p.getName() +
                                "   Town: " + (p.getTown().equals("") ? "unknown" : p.getTown()) +
                                "   Age: " + ((p.getAge().isEmpty()) ? "unknow" : p.getAge().get()));//isEmpty -- isPresent

            }
        }
    }

}
