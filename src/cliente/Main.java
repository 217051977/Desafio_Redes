package cliente;

import pessoa.Pessoa;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Cliente client = new Cliente();

        Pessoa person = new Pessoa(21, "Bruno", "In the vacations I'm going to study to the test");

        while (true) {

        client.sendMessage(person);

        }
    }
}
