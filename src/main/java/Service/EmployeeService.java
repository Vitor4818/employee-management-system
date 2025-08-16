package service;

import model.Employee;
import util.FormatterUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {

    private List<Employee> employees;

    // 3.1 - Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
    public EmployeeService(List<Employee> employees) {
        this.employees = employees != null ? employees : new ArrayList<>();
    }

    // 3.2 - Remover o funcionário “João” da lista.
    public void removeEmployee(String name) {
        if (employees == null || employees.isEmpty()) {
            System.out.println("Employee list is empty.");
            return;
        }
        boolean removed = employees.removeIf(e -> e.getName().equalsIgnoreCase(name));
        if (!removed) {
            System.out.println("Employee " + name + " not found.");
        }
    }

    // 3.3 - Imprimir todos os funcionários
    public void printAllEmployees() {
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }
        for (Employee e : employees) {
            String birthDate = FormatterUtil.formatDate(e.getBirthDate());
            String salary = FormatterUtil.formatCurrency(e.getSalary());
            System.out.println(e.getName() + " | " + birthDate + " | " + e.getRole() + " | " + salary);
        }
    }

    // 3.4 - Aplicar aumento de salário
    public void applySalaryIncrease(double percent) {
        if (employees == null || employees.isEmpty()) return;
        for (Employee e : employees) {
            if (e.getSalary() == null) continue;
            BigDecimal increase = e.getSalary().multiply(BigDecimal.valueOf(percent / 100));
            e.setSalary(e.getSalary().add(increase));
        }
    }

    // 3.5 e 3.6 - Agrupar e imprimir funcionários por função
    public void printEmployeesGroupedByRole() {
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees to group.");
            return;
        }
        Map<String, List<Employee>> grouped = employees.stream()
                .collect(Collectors.groupingBy(Employee::getRole));

        grouped.forEach((role, list) -> {
            System.out.println("Role: " + role);
            list.forEach(e -> System.out.println("  " + e.getName()));
        });
    }

    // 3.8 - Imprimir aniversariantes nos meses indicados
    public void printBirthdaysInMonths(int... months) {
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees to check birthdays.");
            return;
        }
        Set<Integer> monthsSet = Arrays.stream(months).boxed().collect(Collectors.toSet());
        employees.stream()
                .filter(e -> e.getBirthDate() != null && monthsSet.contains(e.getBirthDate().getMonthValue()))
                .forEach(e -> System.out.println(e.getName() + " | " + FormatterUtil.formatDate(e.getBirthDate())));
    }

    // 3.9 - Funcionário mais velho
    public void printOldestEmployee() {
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees to determine oldest.");
            return;
        }
        Employee oldest = employees.stream()
                .filter(e -> e.getBirthDate() != null)
                .max(Comparator.comparing(EmployeeService::calculateAge))
                .orElse(null);

        if (oldest != null) {
            int age = calculateAge(oldest);
            System.out.println("Oldest: " + oldest.getName() + " | Age: " + age);
        }
    }

    private static int calculateAge(Employee e) {
        if (e.getBirthDate() == null) return 0;
        return Period.between(e.getBirthDate(), LocalDate.now()).getYears();
    }

    // 3.10 - Ordem alfabética
    public void printEmployeesAlphabetically() {
        if (employees == null || employees.isEmpty()) return;
        employees.stream()
                .map(Employee::getName)
                .sorted()
                .forEach(System.out::println);
    }

    // 3.11 - Total de salários
    public void printTotalSalaries() {
        if (employees == null || employees.isEmpty()) {
            System.out.println("Total Salaries: 0,00");
            return;
        }
        BigDecimal total = employees.stream()
                .filter(e -> e.getSalary() != null)
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total Salaries: " + FormatterUtil.formatCurrency(total));
    }

    // 3.12 - Salários em múltiplos do salário mínimo
    public void printSalariesInMinimumWage(double minimumWage) {
        if (minimumWage <= 0) {
            System.out.println("Invalid minimum wage.");
            return;
        }
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees to calculate minimum wages.");
            return;
        }
        employees.forEach(e -> {
            if (e.getSalary() == null) return;
            double multiples = e.getSalary().doubleValue() / minimumWage;
            System.out.println(e.getName() + " earns " + String.format("%.2f", multiples) + " minimum wages");
        });
    }
}
