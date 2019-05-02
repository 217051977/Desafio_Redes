package servidor;

import Comum.Mensagem;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Servidor {

    private ServerSocket serverSocket;
    private Map<Integer, ObjectOutputStream> clientes = new HashMap<>();

    public Servidor(int porta) throws IOException {
        serverSocket = new ServerSocket(porta);
    }

    public void start() throws IOException {
        System.out.println("O servidor foi inicializado");
        while (true) {
            Socket cliente = serverSocket.accept();
            ObjectOutputStream outCliente = new ObjectOutputStream(cliente.getOutputStream());
            clientes.put(cliente.getPort(), outCliente);
            processarPedidos(cliente);
        }
    }

    private void processarPedidos(final Socket cliente) throws IOException {
        ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
        new Thread(() -> {
            while (true) {
                try {
                    Mensagem mensagemParaProcessar = (Mensagem) in.readObject();
                    clientes.forEach((porta, out) -> {
                        if(porta != cliente.getPort()) {
                            try {
                                out.writeObject(mensagemParaProcessar);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
