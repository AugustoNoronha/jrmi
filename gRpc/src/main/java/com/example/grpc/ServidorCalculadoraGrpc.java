package com.example.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ServidorCalculadoraGrpc {
    private Server server;

    public void start() throws IOException {
        int port = 50051;
        server = ServerBuilder.forPort(port)
                .addService(new CalculadoraServiceImpl())
                .build()
                .start();
        System.out.println("Servidor gRPC da Calculadora iniciado na porta " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** Desligando o servidor gRPC porque a JVM está sendo desligada");
            ServidorCalculadoraGrpc.this.stop();
            System.err.println("*** Servidor desligado");
        }));
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    static class CalculadoraServiceImpl extends CalculadoraServiceGrpc.CalculadoraServiceImplBase {

        @Override
        public void adicionar(OperacaoRequest request, StreamObserver<OperacaoResponse> responseObserver) {
            double resultado = request.getNum1() + request.getNum2();
            OperacaoResponse response = OperacaoResponse.newBuilder().setResultado(resultado).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void subtrair(OperacaoRequest request, StreamObserver<OperacaoResponse> responseObserver) {
            double resultado = request.getNum1() - request.getNum2();
            OperacaoResponse response = OperacaoResponse.newBuilder().setResultado(resultado).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void multiplicar(OperacaoRequest request, StreamObserver<OperacaoResponse> responseObserver) {
            double resultado = request.getNum1() * request.getNum2();
            OperacaoResponse response = OperacaoResponse.newBuilder().setResultado(resultado).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void dividir(OperacaoRequest request, StreamObserver<OperacaoResponse> responseObserver) {
            if (request.getNum2() == 0) {
                responseObserver.onError(new IllegalArgumentException("Divisão por zero não é permitida."));
                return;
            }
            double resultado = request.getNum1() / request.getNum2();
            OperacaoResponse response = OperacaoResponse.newBuilder().setResultado(resultado).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final ServidorCalculadoraGrpc server = new ServidorCalculadoraGrpc();
        server.start();
        server.blockUntilShutdown();
    }
}
