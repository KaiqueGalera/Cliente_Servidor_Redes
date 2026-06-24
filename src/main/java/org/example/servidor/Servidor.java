package org.example.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

    public static final int PORTA = 5000;
    public static ArrayList<ClienteHandler> clientes = new ArrayList<>();

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {

            System.out.println("Servidor iniciado na porta " + PORTA);

            while (true) {

                Socket socket = serverSocket.accept();

                System.out.println("Novo cliente conectado.");

                ClienteHandler cliente = new ClienteHandler(socket);

                clientes.add(cliente);
                cliente.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcast(ClienteHandler remetente, String mensagem) {

        String horario = java.time.LocalTime.now()
                .withNano(0)
                .toString();

        for (ClienteHandler cliente : clientes) {

            if (cliente == remetente) {

                cliente.enviarMensagem(
                        "[" + horario + "] Você: " + mensagem
                );

            } else {

                cliente.enviarMensagem(
                        "[" + horario + "] "
                                + remetente.getNome()
                                + ": "
                                + mensagem
                );
            }
        }
    }

    public static void broadcastSistema(String mensagem) {

        String horario = java.time.LocalTime.now()
                .withNano(0)
                .toString();

        for (ClienteHandler cliente : clientes) {

            cliente.enviarMensagem(
                    "[" + horario + "] " + mensagem
            );
        }
    }

    // Remove cliente da lista
    public static void removerCliente(ClienteHandler cliente) {
        clientes.remove(cliente);
    }
}