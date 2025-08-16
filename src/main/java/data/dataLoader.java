package data;

import model.Employee;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class dataLoader {

    public static List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("Maria", LocalDate.of(2000, 10, 18), "Operator", new BigDecimal("2009.44")));
        employees.add(new Employee("João", LocalDate.of(1990, 5, 12), "Operator", new BigDecimal("2284.38")));
        employees.add(new Employee("Caio", LocalDate.of(1961, 5, 2), "Coordinator", new BigDecimal("9836.14")));
        employees.add(new Employee("Miguel", LocalDate.of(1988, 10, 14), "Director", new BigDecimal("19119.88")));
        employees.add(new Employee("Alice", LocalDate.of(1995, 1, 5), "Receptionist", new BigDecimal("2234.68")));
        employees.add(new Employee("Heitor", LocalDate.of(1999, 11, 19), "Operator", new BigDecimal("1582.72")));
        employees.add(new Employee("Arthur", LocalDate.of(1993, 3, 31), "Accountant", new BigDecimal("4071.84")));
        employees.add(new Employee("Laura", LocalDate.of(1994, 7, 8), "Manager", new BigDecimal("3017.45")));
        employees.add(new Employee("Heloísa", LocalDate.of(2003, 5, 24), "Electrician", new BigDecimal("1606.85")));
        employees.add(new Employee("Helena", LocalDate.of(1996, 9, 2), "Manager", new BigDecimal("2799.93")));

        return employees;
    }
}