package service;

import entities.*;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentService {
    ClassroomService classroomService=new ClassroomService();
    Utils utils=new Utils();
    public boolean studentMenu(Scanner scanner, ArrayList<User> users, User user, UserService userService, Map<Integer,Lesson> lessonMap, Map<Integer,Classroom> classroomMap, Map<Integer, Homework> homeworkMap, Map<Integer, Point> pointMap,Map<Integer, SubmitHomework> submitHomeworkMap,Map<Integer,Schedule> scheduleMap, Map<Integer,Attendance> attendanceMap){
        boolean isContinue = true;
        boolean isClassroomExist=false;
        for (Map.Entry<Integer,Classroom> entry :classroomMap.entrySet()){
            if (entry.getValue().getStudents().contains(user.getId())){
                int idClassroom=entry.getKey();
                isClassroomExist=true;
                int select = 0;
                do {
                    System.out.println("Chào mừng "+user.getUsername()+", bạn có thể thực hiện các công việc sau:");
                    System.out.println("1 - Lớp học của tôi");
                    System.out.println("2 - Hồ sơ cá nhân");
                    System.out.println("3 - Bảo mật tài khoản");
                    System.out.println("4 - Đăng xuất");
                    System.out.println("0 - Thoát chương trình ");
                    System.out.println("Mời bạn chọn:");
                    select=utils.inputInteger(scanner);
                    switch (select){
                        case 0:
                            isContinue=false;
                            return isContinue;
                        case 1:
                            classroomService.myClassMenuForStudent(scanner,users,lessonMap,classroomMap,idClassroom,homeworkMap,pointMap,submitHomeworkMap, user.getId());
                            break;
                        case 2:
                            userService.infoMenu(scanner,user);
                            break;
                        case 3:
                            userService.accountMenu( scanner, users,  user,  userService);
                            break;
                        case 4:
                            userService.logOut(scanner,users,userService, lessonMap, classroomMap,homeworkMap,pointMap,submitHomeworkMap,scheduleMap, attendanceMap);
                            break;
                    }
                }while (select!=0);
                break;
            }else {
                isClassroomExist=false;
            }
        }
        if (!isClassroomExist){
            System.out.println("Bạn chưa được thêm vào lớp nào");
        }
        return isContinue;
    }

}

