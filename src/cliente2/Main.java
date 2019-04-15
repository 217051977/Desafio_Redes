package cliente2;
import cliente.Cliente;
import pessoa.Pessoa;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Cliente client2 = new Cliente();

        Pessoa person2 = new Pessoa(27, "Andre", "Bye");

        while (true) {

        client2.sendMessage(person2);

        }
    }
}