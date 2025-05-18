package com.example.jrmi;

import java.rmi.Naming;

public class ClienteCalculadora {
    public static void main(String[] args) {
        try {
            Calculadora calculadora = (Calculadora) Naming.lookup("//localhost:1099/CalculadoraService");

            System.out.println("10 + 5 = " + calculadora.adicionar(10, 5));
            System.out.println("10 - 5 = " + calculadora.subtrair(10, 5));
            System.out.println("10 * 5 = " + calculadora.multiplicar(10, 5));
            System.out.println("10 / 5 = " + calculadora.dividir(10, 5));
            try {
                System.out.println("10 / 0 = " + calculadora.dividir(10, 0));
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Erro no cliente RMI: " + e.toString());
            e.printStackTrace();
        }
    }
}
