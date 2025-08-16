package app;

import model.Employee;
import data.dataLoader;
import service.EmployeeService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // 3.1 – Inserir todos os funcionários na mesma ordem da tabela
        List<Employee> employees = dataLoader.loadEmployees();

        // Criar o service passando a lista
        EmployeeService service = new EmployeeService(employees);

        // 3.2 – Remover o funcionário “João”
        System.out.println("Deleting João...");
        service.removeEmployee("João");

        // 3.3 – Imprimir todos os funcionários com informações formatadas
        System.out.println("\n=== All Employees ===");
        service.printAllEmployees();

        // 3.4 – Aplicar aumento de 10% no salário
        service.applySalaryIncrease(10);
        System.out.println("\n=== After 10% Salary Increase ===");
        service.printAllEmployees();

        // 3.5 & 3.6 – Agrupar por função e imprimir
        System.out.println("\n=== Employees Grouped by Role ===");
        service.printEmployeesGroupedByRole();

        // 3.8 – Funcionários que fazem aniversário em outubro (10) e dezembro (12)
        System.out.println("\n=== Employees with Birthdays in October and December ===");
        service.printBirthdaysInMonths(10, 12);

        // 3.9 – Funcionário com maior idade
        System.out.println("\n=== Oldest Employee ===");
        service.printOldestEmployee();

        // 3.10 – Lista de funcionários em ordem alfabética
        System.out.println("\n=== Employees Alphabetically ===");
        service.printEmployeesAlphabetically();

        // 3.11 – Total de salários
        System.out.println("\n=== Total Salaries ===");
        service.printTotalSalaries();

        // 3.12 – Quantos salários mínimos ganha cada funcionário
        System.out.println("\n=== Employees in Minimum Wages ===");
        service.printSalariesInMinimumWage(1212.00);
    }
}
