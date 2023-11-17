package view;

import entities.*;
import service.UserService;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    public void optionMenu(Scanner scanner, ArrayList<User> users, UserService userService, Map<Integer,Lesson> lessonMap, Map<Integer, Classroom> classroomMap, Map<Integer, Homework> homeworkMap, Map<Integer, Point> pointMap,Map<Integer, SubmitHomework> submitHomeworkMap,Map<Integer,Schedule> scheduleMap,Map<Integer,Attendance> attendanceMap){
        boolean isContinue = true;
        do {
            System.out.println("1-Đăng nhập");
            System.out.println("2-Dăng ký");
            System.out.println("Mời bạn lựa chọn: ");
            int select=Integer.parseInt(scanner.nextLine());
            switch (select){
                case 1:
                    isContinue = userService.inputLogin(scanner, users, userService,lessonMap, classroomMap,homeworkMap,pointMap,submitHomeworkMap,scheduleMap, attendanceMap);
                    break;
                case 2:
                    userService.inputRegister(scanner,users);
                    break;
            }
        }while (isContinue);
    }
}
