package model;

import java.math.BigDecimal;

public class Employee extends Person{
    private String role;
    private BigDecimal salary;

    //Construtor Parametrizado
    public Employee(String name, java.time.LocalDate birthDate, String role, BigDecimal salary) {
        super(name, birthDate);
        this.role = role;
        this.salary = salary;
    }

    //Getters e Setters
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    //ToString
    @Override
    public String toString() {
        return String.format("Employee{name='%s', birthDate=%s, role='%s', salary=%s}",
                getName(), getBirthDate(), role, salary);
    }

}

