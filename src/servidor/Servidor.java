package servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

public class Servidor {

//  create a socket for the server
    private ServerSocket serverSocket;
//  create a socket for the client
    private Socket clientSocket;
//  create a printWriter?????
    private PrintWriter out;
//  create a buffer reader
    private ObjectInputStream in;

    private int day, month, year, hour, minute, secound;

//  create a constructor
    public Servidor() throws IOException {

//      initialize the socket
        serverSocket = new ServerSocket(3000);

    }

//  create a function that start the server
    public void start() throws IOException, ClassNotFoundException {

//      prints the message
        System.out.println("O servidor foi inicializado!");

        boolean isRunning = true;

        while (isRunning) {

            day = Calendar.getInstance().get(Calendar.DATE);
            month = Calendar.getInstance().get(Calendar.MONTH);
            year = Calendar.getInstance().get(Calendar.YEAR);
            hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            minute = Calendar.getInstance().get(Calendar.MINUTE);
            secound = Calendar.getInstance().get(Calendar.SECOND);

//      create a ghost socket
            clientSocket = serverSocket.accept();
//      set the a new PrintWriter and a new bufferReader
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new ObjectInputStream(clientSocket.getInputStream());
//      declares and initialize a string called "resposta" with the content of the buffer reader
            String mensagem = in.readObject().toString();
//      prints the content of the variable "resposta"
            System.out.println(mensagem);

            String[] messageParts = mensagem.split(";");

            isRunning = !closeConnection(messageParts);

            String newMessage = messageParts[0] + " has sent: \"" + messageParts[2] + "\" at: " +
                    day + "/" + (month + 1) + "/" + year + " - " + hour + ":" + minute + ":" + secound;

            System.out.println(newMessage);

            out.println(newMessage);

        }

    }

    private boolean closeConnection(String[] messageParts) {

        for (String messagePart : messageParts) {

            if (messagePart.equalsIgnoreCase("terminar")) {

                return true;

            }

        }

        return false;

    }

//  create a function that stops the server
    public void stop() throws IOException {

//      closes the printWriter
//      closes the bufferReader
//      closes both sockets
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();

    }

}
