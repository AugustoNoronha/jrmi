package com.example.jrmi;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class Calculadora extends UnicastRemoteObject implements ICalculadora {
    protected Calculadora() throws RemoteException {
        super();
    }

    @Override
    public double adicionar(double a, double b) throws RemoteException {
        return a + b;
    }

    @Override
    public double subtrair(double a, double b) throws RemoteException {
        return a - b;
    }

    @Override
    public double multiplicar(double a, double b) throws RemoteException {
        return a * b;
    }

    @Override
    public double dividir(double a, double b) throws RemoteException {
        if (b == 0) {
            throw new RemoteException("Divisão por zero não é permitida.");
        }
        return a / b;
    }
}
