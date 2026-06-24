package org.example.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteHandler extends Thread {

    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter saida;
    private String nome;

    public ClienteHandler(Socket socket) {

        this.socket = socket;

        try {
            entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            saida = new PrintWriter(
                    socket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNome() {
        return nome;
    }

    public void enviarMensagem(String mensagem) {
        saida.println(mensagem);
    }

    @Override
    public void run() {

        try {

            saida.println("Digite seu nome:");
            nome = entrada.readLine();

            Servidor.broadcast(this, nome + " entrou no chat.");

            String mensagem;

            while ((mensagem = entrada.readLine()) != null) {

                if (mensagem.equalsIgnoreCase("/sair")) {
                    break;
                }

                Servidor.broadcast(this, mensagem);
            }

        } catch (IOException e) {
            System.out.println(nome + " desconectou inesperadamente.");
        } finally {

            Servidor.removerCliente(this);
            Servidor.broadcast(this, nome + " saiu do chat.");

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}