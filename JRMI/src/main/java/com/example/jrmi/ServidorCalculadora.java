package com.example.jrmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServidorCalculadora {
    public static void main(String[] args) {
        try {
            Calculadora calculadora = new Calculadora();

            Naming.rebind("//localhost:1099/CalculadoraService", calculadora);

            System.out.println("Servidor RMI da Calculadora pronto.");
        } catch (Exception e) {
            System.err.println("Erro no servidor RMI: " + e.toString());
            e.printStackTrace();
        }
    }
}
