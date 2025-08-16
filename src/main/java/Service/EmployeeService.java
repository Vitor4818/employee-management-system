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

    //Ações
    //3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
    //3.2 – Remover o funcionário “João” da lista.
    //3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
    //• informação de data deve ser exibido no formato dd/mm/aaaa;
    //• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
    //3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
    //3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
    //3.6 – Imprimir os funcionários, agrupados por função.
    //3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
    //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
    //3.10 – Imprimir a lista de funcionários por ordem alfabética.
    //3.11 – Imprimir o total dos salários dos funcionários.
    //3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.


    //3.1 - Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
    public EmployeeService(List<Employee> employees) {
        this.employees = employees;
    }

    //3.2 - Remover o funcionário “João” da lista. (Remove por nome)
    public void removeEmployee(String name) {
        employees.removeIf(e -> e.getName().equalsIgnoreCase(name));
    }

    //3.3 – Imprimir todos os funcionários com todas suas informações, sendo que:
    //• informação de data deve ser exibido no formato dd/mm/aaaa;
    //• informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.
    public void printAllEmployees() {
        for (Employee e : employees) {
            String birthDate = FormatterUtil.formatDate(e.getBirthDate());
            String salary = FormatterUtil.formatCurrency(e.getSalary());
            System.out.println(e.getName() + " | " + birthDate + " | " + e.getRole() + " | " + salary);
        }
    }

    //3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
    public void applySalaryIncrease(double percent) {
        for (Employee e : employees) {
            BigDecimal increase = e.getSalary().multiply(BigDecimal.valueOf(percent / 100));
            e.setSalary(e.getSalary().add(increase));
        }
    }

    //3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
    //3.6 – Imprimir os funcionários, agrupados por função.
    public void printEmployeesGroupedByRole() {
        Map<String, List<Employee>> grouped = employees.stream()
                .collect(Collectors.groupingBy(Employee::getRole));

        grouped.forEach((role, list) -> {
            System.out.println("Role: " + role);
            list.forEach(e -> System.out.println("  " + e.getName()));
        });
    }

    // 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12.
    public void printBirthdaysInMonths(int... months) {
        Set<Integer> monthsSet = Arrays.stream(months).boxed().collect(Collectors.toSet());
        employees.stream()
                .filter(e -> monthsSet.contains(e.getBirthDate().getMonthValue()))
                .forEach(e -> System.out.println(e.getName() + " | " + FormatterUtil.formatDate(e.getBirthDate())));
    }

    // 3.9 - Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
    public void printOldestEmployee() {
        Employee oldest = employees.stream()
                .min(Comparator.comparing(EmployeeService::calculateAge))
                .orElse(null);

        if (oldest != null) {
            int age = calculateAge(oldest);
            System.out.println("Oldest: " + oldest.getName() + " | Age: " + age);
        }
    }

    private static int calculateAge(Employee e) {
        return Period.between(e.getBirthDate(), LocalDate.now()).getYears();
    }

    // 3.10 - Imprimir a lista de funcionários por ordem alfabética.
    public void printEmployeesAlphabetically() {
        employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(e -> System.out.println(e.getName()));
    }

    // 3.11 - Imprimir o total dos salários dos funcionários.
    public void printTotalSalaries() {
        BigDecimal total = employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total Salaries: " + FormatterUtil.formatCurrency(total));
    }

    // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
    public void printSalariesInMinimumWage(double minimumWage) {
        employees.forEach(e -> {
            double multiples = e.getSalary().doubleValue() / minimumWage;
            System.out.println(e.getName() + " earns " + String.format("%.2f", multiples) + " minimum wages");
        });
    }
}
