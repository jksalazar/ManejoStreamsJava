package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<Persona> personas = Arrays.asList(
                new Persona("Juan", 30),
                new Persona("Ana", 22),
                new Persona("Pedro", 28),
                new Persona("Lucía", 18)
        );


        // Filtrar personas mayores de 25 años
        List<Persona> mayoresDe25 = personas.stream()
                .filter(persona -> persona.getEdad() > 25)
                .collect(Collectors.toList());

        // Impresión para verificar el resultado
        mayoresDe25.forEach(persona -> System.out.println(persona.getNombre() + " - " + persona.getEdad()));

        // Imprimir nombres de personas mayores de 25 años
        personas.stream()
                .filter(persona -> persona.getEdad() > 25)
                .forEach(persona -> System.out.println(persona.getNombre()));

        // Transformar nombres a mayúsculas y recolectarlos en una lista
        List<String> nombresMayusculas = personas.stream()
                .map(persona -> persona.getNombre().toUpperCase())
                .collect(Collectors.toList());

        // Impresión para verificar el resultado
        nombresMayusculas.forEach(System.out::println);


        List<Persona2> personas2 = Arrays.asList(
                new Persona2("Juan", 30, "CDMX"),
                new Persona2("Ana", 22, "Guadalajara"),
                new Persona2("Pedro", 35, "CDMX"),
                new Persona2("Lucía", 18, "Monterrey")
        );

        //Ordenar una lista de personas por edad utilizando sorted() y mostrar cada paso del proceso con peek()
        List<Persona> personasOrdenadas = personas.stream()
                .sorted((p1, p2) -> Integer.compare(p1.getEdad(), p2.getEdad()))
                .peek(persona -> System.out.println("Ordenando: " + persona.getNombre() + " - " + persona.getEdad()))
                .collect(Collectors.toList());

        // Impresión final para verificar el resultado
        personasOrdenadas.forEach(persona -> System.out.println(persona.getNombre() + " - " + persona.getEdad()));

        //Encontrar cualquier persona que sea de "CDMX" y tenga más de 30 años utilizando findAny()
        Optional<Persona2> personaCDMXMayor30 = personas2.stream()
                .filter(persona -> persona.getCiudad().equals("CDMX") && persona.getEdad() > 30)
                .findAny();

        //Impresión para verificar el resultado
        personaCDMXMayor30.ifPresent(persona -> System.out.println(persona.getNombre() + " - " + persona.getEdad() + " - " + persona.getCiudad()));

        //Agrupar personas por ciudad de origen utilizando collect() con groupingBy()
        personas2.stream()
                .collect(Collectors.groupingBy(Persona2::getCiudad))
                .forEach((ciudad, listaPersonas) -> {
                    System.out.println(ciudad + ":");
                    listaPersonas.forEach(persona -> System.out.println(persona.getNombre() + " - " + persona.getEdad()));
                });

        //Separar personas en dos grupos utilizando partitioningBy()
        personas2.stream()
                .collect(Collectors.partitioningBy(persona -> persona.getEdad() > 25))
                .forEach((mayores, listaPersonas) -> {
                    System.out.println(mayores ? "Mayores de 25:" : "Menores de 25:");
                    listaPersonas.forEach(persona -> System.out.println(persona.getNombre() + " - " + persona.getEdad()));
                });

        //Filtrar, transformar y ordenar datos de una lista de personas
        List<String> nombresMayoresDe25 = personas2.stream()
                .filter(persona -> persona.getEdad() > 25) //filtrar personas mayores de 25 años
                .sorted((p1, p2) -> p1.getNombre().compareTo(p2.getNombre())) //ordenar por nombre
                .map(Persona2::getNombre) //transformar a nombres
                .collect(Collectors.toList()); //recolectar en una lista

        //Impresión para verificar el resultado
        nombresMayoresDe25.forEach(System.out::println);

    }
}