package pessoa;

import java.io.Serializable;

public class Pessoa implements Serializable {

    private int age;
    private String name;
    private String content;

    public Pessoa(int age, String name, String content) {

        this.age = age;
        this.name = name;
        this.content = content;

    }

    @Override
    public String toString() {

        return name + ";" + age + ";" + content;

    }
}
