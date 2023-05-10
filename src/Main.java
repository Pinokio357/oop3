import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {
    private static Random random = new Random();

    static Employee generateEmployee() {

        String[] names = new String[]{"Анатолий", "Глеб", "Иван", "Сергей", "Борис", "Егор", "Станислав", "Пётр", "Матвей", "Владимир", "Ашот", "Николай", "Антон", "Михаил"};
        String[] surnames = new String[]{"Иванов", "Петров", "Васечкин", "Сидоров", "Шубин", "Григорьев", "Васильев", "Фокин", "Летов"};
        int choise = random.nextInt(2);
        int salary = random.nextInt(80000, 100000);
        switch (choise) {
            case 0:

                return new Worker(names[random.nextInt(13)], surnames[random.nextInt(9)], salary);
            default:
                int hours = random.nextInt(80, 160);
                double rate = random.nextInt(500, 1000);
                return new FreeLancer(names[random.nextInt(13)], surnames[random.nextInt(9)], rate, hours);

        }

    }

    public static void main(String[] args) {
        Employee[] employees = new Employee[10];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = generateEmployee();
        }
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        System.out.println();

        // Arrays.sort(employees, new NaneComparator());
        Arrays.sort(employees, new SalaryComparator());
        System.out.println();
        System.out.println("Сортировка по возрастанию заработной платы:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        Arrays.sort(employees, new SalaryComparatorV2());
        System.out.println();
        System.out.println("Сортировка по убыванию заработной платы:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        Arrays.sort(employees, new NaneComparator());
        System.out.println();
        System.out.println("Сортировка по именам:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        Arrays.sort(employees, new SurnameComparator());
        System.out.println();
        System.out.println("Сортировка по фамилиям:");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    static class SalaryComparator implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
            // return o1.calculateSalary() == o2.calculateSalary() ? 0 : (o1.calculateSalary() > o2.calculateSalary() ? 1 : -1) ;
            return Double.compare(o1.calculateSalary(), o2.calculateSalary());
        }
    }
    static class SalaryComparatorV2 implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {

            return Double.compare(o2.calculateSalary(), o1.calculateSalary());
        }
    }

    static class NaneComparator implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.name.compareTo(o2.name);
        }
    }
    static class SurnameComparator implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.surname.compareTo(o2.surname);
        }
    }

    abstract static class Employee {
        protected String name;
        protected String surname;
        protected double salary;

        public Employee(String name, String surname, double salary) {
            this.name = name;
            this.surname = surname;
            this.salary = salary;
        }

        public abstract double calculateSalary();

        @Override
        public String toString() {
            return String.format("Employee: %s %s, receives: %.2f rub", name, surname, salary);
        }


    }

    static class Worker extends Employee {

        public Worker(String name, String surName, double salary) {
            super(name, surName, salary);
        }

        @Override
        public double calculateSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return String.format("рабочий: %s %s, фиксированная месячная плата: %.2f руб.", name, surname, salary);
        }
    }

    static class FreeLancer extends Employee {
        protected int hours;

        public FreeLancer(String name, String surname, double salary, int hours) {
            super(name, surname, salary);
            this.hours = hours;
        }

        @Override
        public double calculateSalary() {
            return salary * hours;
        }

        @Override
        public String toString() {
            return String.format("Фрилансер: %s %s ; заработная плата: %.2f", name, surname, calculateSalary());
        }
    }
}
