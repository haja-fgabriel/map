package ubb.scs.map.ir.seminar.studentsmanagement.ui;

import ubb.scs.map.ir.seminar.studentsmanagement.services.service.GradeService;
import ubb.scs.map.ir.seminar.studentsmanagement.services.service.HomeworkService;
import ubb.scs.map.ir.seminar.studentsmanagement.services.service.StudentService;

import java.util.Scanner;

class ExitException extends RuntimeException {
    public ExitException() {
    }

    public ExitException(String message) {
        super(message);
    }
}

public class ConsoleUI {
    private HomeworkService homeworkService;
    private StudentService studentService;
    private GradeService gradeService;

    public ConsoleUI(HomeworkService homeworkService, StudentService studentService, GradeService gradeService) {
        this.homeworkService = homeworkService;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    private static String options = "Options:\n"
            + "1. Print students\n"
            + "2. Print homework\n"
            + "3. Print grades\n"
            + "4. Add student (ID, name, avg)\n"
            + "5. Add homework (ID, description, deadline)\n"
            + "6. Add grade (homework ID, student ID, value)\n"
            + "10. Exit\n";

    private void takeInput(String input) {
        if (input.startsWith("10"))
            throw new ExitException("Exiting program...");
        else if (input.startsWith("1")) {
            studentService.getAllStudents().forEach(System.out::println);
        } else if (input.startsWith("2")) {
            homeworkService.getAllHomework().forEach(System.out::println);
        } else if (input.startsWith("3")) {
            System.out.println("[DEBUG] intra");
            gradeService.getAllGrades().forEach(System.out::println);
        } else if (input.startsWith("4")) {
            String[] values = input.split(" ");
            studentService.addStudent(Long.parseLong(values[1].strip()), values[2], Float.parseFloat(values[3].strip()), values[4].strip());
        } else if (input.startsWith("5")) {
            String[] values = input.split(" ");
            homeworkService.addHomework(Long.parseLong(values[1].strip()), values[2], Integer.parseInt(values[3].strip()));
        } else if (input.startsWith("6")) {
            String[] values = input.split(" ");
            gradeService.addGrade(Long.parseLong(values[1].strip()), Long.parseLong(values[2].strip()), Float.parseFloat(values[3].strip()), null, values[4].strip(), values[5].strip(), 0);
        }
    }

    public void run() {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println(options);
            String input = s.nextLine();
            try {
                takeInput(input);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                if (e instanceof ExitException)
                    break;
            }
        }
    }

    // TODO connect the rest of the service functions to this UI
}
