package cliente;

import pessoa.Pessoa;

import java.io.*;
import java.net.*;

public class Cliente {

//  create a socket
    private Socket clientSocket;
//  create a printWriter?????
    private ObjectOutputStream out;
//  create a buffer reader
    private BufferedReader in;

//  create a constructor
    public Cliente() throws IOException {

//      initialize the socket
//      initialize the printWriter
//      initialize the bufferReader
        clientSocket = new Socket("localhost", 3000);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    }

//  create a function that will send the message to the server
    public void sendMessage(Pessoa pessoa) throws IOException {

        String incomingMessage;

        out.writeObject(pessoa);

        try {

            incomingMessage = in.readLine();
            System.out.println(incomingMessage);

        }catch (Exception e) {

            e.printStackTrace();

        }

    }

//  create a function that stops the connection
    public void stopConnection() throws IOException {

//      closes the printWriter
//      closes the bufferReader
//      closes the socket
        in.close();
        out.close();
        clientSocket.close();

    }

}