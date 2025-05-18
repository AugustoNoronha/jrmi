package com.example.grpc;
import com.example.calculadora.CalculadoraServiceGrpc;
import com.example.calculadora.OperacaoRequest;
import com.example.calculadora.OperacaoResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
public class ClienteCalculadoraGRPC {
    public static void main(String[] args) throws InterruptedException {
        String host = "localhost";
        int port = 50051;

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        try {
            CalculadoraServiceGrpc.CalculadoraServiceBlockingStub blockingStub = CalculadoraServiceGrpc.newBlockingStub(channel);

            OperacaoRequest adicionarRequest = OperacaoRequest.newBuilder().setNum1(10).setNum2(5).build();
            OperacaoResponse adicionarResponse = blockingStub.adicionar(adicionarRequest);
            System.out.println("10 + 5 = " + adicionarResponse.getResultado());

            OperacaoRequest subtrairRequest = OperacaoRequest.newBuilder().setNum1(10).setNum2(5).build();
            OperacaoResponse subtrairResponse = blockingStub.subtrair(subtrairRequest);
            System.out.println("10 - 5 = " + subtrairResponse.getResultado());

            OperacaoRequest multiplicarRequest = OperacaoRequest.newBuilder().setNum1(10).setNum2(5).build();
            OperacaoResponse multiplicarResponse = blockingStub.multiplicar(multiplicarRequest);
            System.out.println("10 * 5 = " + multiplicarResponse.getResultado());

            OperacaoRequest dividirRequest = OperacaoRequest.newBuilder().setNum1(10).setNum2(5).build();
            OperacaoResponse dividirResponse = blockingStub.dividir(dividirRequest);
            System.out.println("10 / 5 = " + dividirResponse.getResultado());

            OperacaoRequest dividirPorZeroRequest = OperacaoRequest.newBuilder().setNum1(10).setNum2(0).build();
            try {
                blockingStub.dividir(dividirPorZeroRequest);
            } catch (StatusRuntimeException e) {
                System.out.println("Erro na divisão por zero: " + e.getStatus().getDescription());
            }

        } finally {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
