package todolist;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        try {
            taskManager.createTable();

            while (true) {
                System.out.println("\n1. Добавить задачу");
                System.out.println("2. Показать все задачи");
                System.out.println("3. Обновить статус задачи");
                System.out.println("4. Удалить задачу");
                System.out.println("5. Выйти");
                System.out.print("Выберите действие: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Очистка буфера

                switch (choice) {
                    case 1:
                        System.out.print("Введите описание задачи: ");
                        String description = scanner.nextLine();
                        taskManager.addTask(description);
                        System.out.println("Задача добавлена.");
                        break;
                    case 2:
                        List<Task> tasks = taskManager.getAllTasks();
                        for (Task task : tasks) {
                            System.out.println(task.getId() + ". " + task.getDescription() + " - " + (task.isDone() ? "Выполнено" : "Не выполнено"));
                        }
                        break;
                    case 3:
                        System.out.print("Введите ID задачи: ");
                        int id = scanner.nextInt();
                        System.out.print("Задача выполнена? (true/false): ");
                        boolean isDone = scanner.nextBoolean();
                        taskManager.updateTaskStatus(id, isDone);
                        System.out.println("Статус задачи обновлен.");
                        break;
                    case 4:
                        System.out.print("Введите ID задачи для удаления: ");
                        int deleteId = scanner.nextInt();
                        taskManager.deleteTask(deleteId);
                        System.out.println("Задача удалена.");
                        break;
                    case 5:
                        System.out.println("Программа завершена.");
                        return;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}