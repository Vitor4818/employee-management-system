package model;

import java.time.LocalDate;

public class Person {
    private String name;
    private LocalDate birthDate;

    //Construtor parametrizado
    public Person(String name, LocalDate birthDate) {
        this.birthDate = birthDate;
        this.name = name;
    }

    //Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("Person{name='%s', birthDate=%s}", name, birthDate);
    }
}
