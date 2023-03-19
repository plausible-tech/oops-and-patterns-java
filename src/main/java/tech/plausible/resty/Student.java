package tech.plausible.resty;

import java.io.Serializable;

public class Student implements Serializable {

    int id;
    String name;

    public int getId() {
        return id;
    }

    public int getName() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
