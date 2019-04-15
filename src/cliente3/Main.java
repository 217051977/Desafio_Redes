package cliente3;
import cliente.Cliente;
import pessoa.Pessoa;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Cliente client3 = new Cliente();

        Pessoa person3 = new Pessoa(12, "Tó Zé", "Hi");

        while (true) {

            client3.sendMessage(person3);

        }
    }
}