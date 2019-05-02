package cliente;

import Comum.Mensagem;

import java.io.*;
import java.net.*;

public class Cliente {

//  create a printWriter?????
    private ObjectOutputStream out;
//  create a buffer reader
private ObjectInputStream in;

    private String remetente;
    private String enderecoServidor;
    private int porta;
    private boolean execucao;

//  create a constructor
    public Cliente(String remetente, String enderecoServidor, int porta) {

        this.remetente = remetente;
        this.enderecoServidor = enderecoServidor;
        this.porta = porta;

    }

    public void start() throws IOException {

        execucao = true;

        Socket socket = new Socket(InetAddress.getByName(enderecoServidor), porta);

        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        receberMensagens();

    }

    public void terminar() {

    }

    public void enviarMensagem(String conteudo) throws IOException {

        Mensagem mensagemParaEnviar = new Mensagem(remetente, conteudo);
        out.writeObject(mensagemParaEnviar);

    }

    private void receberMensagens() {

        new Thread(() -> {

            try {

                while (execucao) {

                    Mensagem mensagemRecebida = (Mensagem) in.readObject();
                    System.out.println(mensagemRecebida);

                }

            } catch (IOException | ClassNotFoundException e) {

                e.printStackTrace();

            }

        }).start();

    }

}