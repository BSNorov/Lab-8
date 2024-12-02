import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner sc;
    private Validator validator;
    private List<Box<Shape>> history = new ArrayList<>();

    public Menu(Scanner scanner) {
        this.sc = scanner;
        this.validator = new Validator(scanner);
    }

    public void showMainMenu() {
        boolean exit = false;

        while (!exit) {
            printMainMenu();
            int choice = validator.getValidChoice(1, 5);

            switch (choice) {
                case 1 -> {
                    printShapesMenu();
                    int shapeMenuChoice = validator.getValidChoice(1, 2);
                    switch (shapeMenuChoice) {
                        case 1 -> performRectangleCalculation();
                        case 2 -> performCircleCalculation();
                    }
                }
                case 2 -> printProgramInfo();
                case 3 -> printDeveloperInfo();
                case 4 -> showShapesHistory();
                case 5 -> {
                    exit = true;
                    System.out.println("Выход из программы...");
                }
                default -> System.out.println("Неверный выбор. Пожалуйста, выберите один из пунктов меню.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n======= Главное меню =======");
        System.out.println("1. Выполнить расчет для фигуры");
        System.out.println("2. Информация о программе");
        System.out.println("3. Информация о разработчике");
        System.out.println("4. Показать историю расчётов");
        System.out.println("5. Выход");
        System.out.print("Выберите пункт меню: ");
    }

    private void printShapesMenu() {
        System.out.println("\n======= Выбор фигуры =======");
        System.out.println("1. Площадь прямоугольника");
        System.out.println("2. Площадь круга");
        System.out.print("Выберите пункт меню: ");
    }

    private void performRectangleCalculation() {
        try {
            double length = validator.getPositiveDouble("Введите длину прямоугольника (положительное число): ");
            double width = validator.getPositiveDouble("Введите ширину прямоугольника (положительное число): ");
            Rectangle rectangle = new Rectangle(length, width);
            history.add(new Box<>(rectangle));
            System.out.println("Результат: " + rectangle.calculateArea());
        } catch (InvalidShapeParameterException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void performCircleCalculation() {
        try {
            double radius = validator.getPositiveDouble("Введите радиус круга (положительное число): ");
            Circle circle = new Circle(radius);
            history.add(new Box<>(circle));
            System.out.println("Результат: " + circle.calculateArea());
        } catch (InvalidShapeParameterException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void printProgramInfo() {
        System.out.println("\nИнформация о программе:");
        System.out.println("Эта программа предназначена для работы с различными геометрическими фигурами и расчета их площади.");
    }

    private void printDeveloperInfo() {
        System.out.println("\nИнформация о разработчике:");
        System.out.println("Разработчик: Бехруз, студент магистратуры по направлению 'Разработка и управление в программных проектах'.");
    }

    private void showShapesHistory() {
        System.out.println("\n======= История расчетов =======");
        if (history.isEmpty()) {
            System.out.println("История пуста.");
        } else {
            for (Box<Shape> box : history) {
                Shape shape = box.getContent();
                System.out.println(shape.toString());
                System.out.println("Результат: " + shape.calculateArea());
                System.out.println();
            }
        }
    }
}
