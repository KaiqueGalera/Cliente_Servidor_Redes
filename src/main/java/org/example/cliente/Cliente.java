package org.example.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    private static final String HOST = "localhost";
    private static final int PORTA = 5000;

    public static void main(String[] args) {

        try {
            Socket socket = new Socket(HOST, PORTA);

            System.out.println("=================================");
            System.out.println("         CHAT TCP");
            System.out.println("=================================");
            System.out.println("Conectado ao servidor.");
            System.out.println("Digite /sair para encerrar.");
            System.out.println();

            BufferedReader teclado =
                    new BufferedReader(new InputStreamReader(System.in));

            BufferedReader entrada =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter saida =
                    new PrintWriter(socket.getOutputStream(), true);

            // Thread para receber mensagens
            Thread receberMensagens = new Thread(() -> {

                try {
                    String mensagem;

                    while ((mensagem = entrada.readLine()) != null) {

                        System.out.print("\r");
                        System.out.println(mensagem);
                        System.out.print("> ");
                    }

                } catch (IOException e) {
                    System.out.println("\nConexão encerrada.");
                }
            });

            receberMensagens.setDaemon(true);
            receberMensagens.start();

            String texto;

            while (true) {

                System.out.print("> ");
                texto = teclado.readLine();

                if (texto == null) break;

                if (texto.trim().isEmpty()) continue;

                saida.println(texto);

                if (texto.equalsIgnoreCase("/sair")) break;
            }

            System.out.println("Desconectando...");
            socket.close();

        } catch (IOException e) {
            System.out.println("Erro ao conectar no servidor.");
        }
    }
}