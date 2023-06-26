/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.taller10;

import java.util.Scanner;

/**
 *
 * @author USUARIOPC
 */
// Clase abstracta Personaje que define los atributos y métodos comunes a todos los personajes
 abstract class Personaje {
    protected String nombre;
    protected int puntosVida;
    protected int nivelExperiencia;

    public Personaje(String nombre, int puntosVida, int nivelExperiencia) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.nivelExperiencia = nivelExperiencia;
    }

    public abstract void atacar(Personaje objetivo);

    public abstract void defender(int puntosAtaque);

    public boolean estaVivo() {
        return puntosVida > 0;
    }

    public void recibirDaño(int puntosDaño) {
        puntosVida -= puntosDaño;
        if (puntosVida < 0) {
            puntosVida = 0;
        }
    }
}

// Clase para el personaje Guerrero
 class Guerrero extends Personaje {
    private int fuerza;

    public Guerrero(String nombre, int puntosVida, int nivelExperiencia, int fuerza) {
        super(nombre, puntosVida, nivelExperiencia);
        this.fuerza = fuerza;
    }

    public void atacar(Personaje objetivo) {
        int puntosAtaque = fuerza * nivelExperiencia;
        objetivo.defender(puntosAtaque);
        System.out.println(nombre + " ataca a " + objetivo.nombre + " con " + puntosAtaque + " puntos de ataque.");
    }

    public void defender(int puntosAtaque) {
        int puntosDaño = puntosAtaque / (nivelExperiencia * 2);
        recibirDaño(puntosDaño);
        System.out.println(nombre + " recibe " + puntosDaño + " puntos de daño.");
    }
}

// Clase para el personaje Mago
 class Mago extends Personaje {
    private int poderMagico;

    public Mago(String nombre, int puntosVida, int nivelExperiencia, int poderMagico) {
        super(nombre, puntosVida, nivelExperiencia);
        this.poderMagico = poderMagico;
    }

    public void atacar(Personaje objetivo) {
        int puntosAtaque = poderMagico * nivelExperiencia;
        objetivo.defender(puntosAtaque);
        System.out.println(nombre + " lanza un hechizo a " + objetivo.nombre + " con " + puntosAtaque + " puntos de ataque.");
    }

    public void defender(int puntosAtaque) {
        int puntosDaño = puntosAtaque / (nivelExperiencia * 3);
        recibirDaño(puntosDaño);
        System.out.println(nombre + " recibe " + puntosDaño + " puntos de daño.");
    }
}

// Clase para el personaje Arquero
 class Arquero extends Personaje {
    private int precision;

    public Arquero(String nombre, int puntosVida, int nivelExperiencia, int precision) {
        super(nombre, puntosVida, nivelExperiencia);
        this.precision = precision;
    }

    public void atacar(Personaje objetivo) {
        int puntosAtaque = precision * nivelExperiencia;
        objetivo.defender(puntosAtaque);
        System.out.println(nombre + " dispara a " + objetivo.nombre + " con " + puntosAtaque + " puntos de ataque.");
    }

    public void defender(int puntosAtaque) {
        int puntosDaño = puntosAtaque / (nivelExperiencia * 4);
        recibirDaño(puntosDaño);
        System.out.println(nombre + " recibe " + puntosDaño + " puntos de daño.");
    }
}

// Clase para simular una batalla entre dos personajes
 class Combate {
    public static void simularBatalla(Personaje personaje1, Personaje personaje2) {
        System.out.println("Comienza la batalla entre " + personaje1.nombre + " y " + personaje2.nombre + "!");
        while (personaje1.estaVivo() && personaje2.estaVivo()) {
            personaje1.atacar(personaje2);
            if (personaje2.estaVivo()) {
                personaje2.atacar(personaje1);
            }
        }
        System.out.println("La batalla ha terminado!");

        if (!personaje1.estaVivo() && !personaje2.estaVivo()) {
            System.out.println("La batalla termina en empate!");
        } else if (personaje1.estaVivo()) {
            System.out.println(personaje1.nombre + " ha ganado la batalla!");
        } else {
            System.out.println(personaje2.nombre + " ha ganado la batalla!");
        }
    }
}

// Clase de prueba/ejecutor
public class EjecutorJuego {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el nombre de su guerrero");
        String guerrero_ = teclado.nextLine();
        System.out.println("Ingrese el nombre de su guerrero");
        String mago_ = teclado.nextLine();
        System.out.println("Ingrese el nombre de su guerrero");
        String arquero_ = teclado.nextLine();

        Guerrero guerrero = new Guerrero(guerrero_, 100, 1, 10);
        Mago mago = new Mago(mago_,80, 1, 20);
        Arquero arquero = new Arquero(arquero_, 90, 1, 15);

        int opcion;
        do {
            System.out.println("Selecciona una opción:");
            System.out.println("1. Guerrero vs. Mago");
            System.out.println("2. Guerrero vs. Arquero");
            System.out.println("3. Mago vs. Arquero");
            System.out.println("4. Salir");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("¡Comienza la batalla entre Guerrero y Mago!");
                    System.out.println("-----------------------------------");
                    Combate.simularBatalla(guerrero, mago);
                    break;
                case 2:
                    System.out.println("¡Comienza la batalla entre Guerrero y Arquero!");
                    System.out.println("-----------------------------------");
                    Combate.simularBatalla(guerrero, arquero);
                    break;
                case 3:
                    System.out.println("¡Comienza la batalla entre Mago y Arquero!");
                    System.out.println("-----------------------------------");
                    Combate.simularBatalla(mago, arquero);
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Inténtalo nuevamente.");
                    break;
            }

            System.out.println();
        } while (opcion != 4);

        teclado.close();
    }
}
