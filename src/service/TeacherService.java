package service;

import entities.*;
import utils.Utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class TeacherService {
    ClassroomService classroomService=new ClassroomService();
    Utils utils=new Utils();
    public boolean teacherMenu(Scanner scanner, ArrayList<User> users, User user, UserService userService,Map<Integer,Lesson> lessonMap,Map<Integer,Classroom> classroomMap, Map<Integer, Homework> homeworkMap,Map<Integer, Point> pointMap,Map<Integer, SubmitHomework> submitHomeworkMap,Map<Integer,Schedule> scheduleMap, Map<Integer,Attendance> attendanceMap){
        int select = 0;
        boolean isContinue = true;
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
                    findClassroomList(scanner,users,user,userService,lessonMap,classroomMap,homeworkMap,pointMap,submitHomeworkMap,scheduleMap, attendanceMap);
                    break;
                case 2:
                    userService.infoMenu(scanner,user);
                    break;
                case 3:
                    userService.accountMenu( scanner, users,  user,  userService);
                    break;
                case 4:
                    userService.logOut(scanner,users,userService,lessonMap,classroomMap,homeworkMap,pointMap,submitHomeworkMap,scheduleMap,attendanceMap);
                    break;
            }
        }while (select!=0);
        return isContinue;
    }

    public void findClassroomList(Scanner scanner, ArrayList<User> users, User user, UserService userService,Map<Integer,Lesson> lessonMap,Map<Integer,Classroom> classroomMap,Map<Integer, Homework> homeworkMap,Map<Integer, Point> pointMap,Map<Integer, SubmitHomework> submitHomeworkMap,Map<Integer,Schedule> scheduleMap, Map<Integer,Attendance> attendanceMap){
        int countClass=0;
        ArrayList<Integer> myClassId=new ArrayList<>();
        System.out.println("Các lớp học bạn dạy là:");
        for (Map.Entry<Integer,Classroom> entry:classroomMap.entrySet()){
            if (user.getId()==entry.getValue().getTeacherId()){
                System.out.println(entry);
                myClassId.add(entry.getKey());
                countClass++;
            }
        }
        if (countClass==0){
            System.out.println("Bạn chưa dạy lớp nào");
            teacherMenu(scanner,users,user,userService,lessonMap,classroomMap,homeworkMap,pointMap,submitHomeworkMap,scheduleMap,attendanceMap);
        }else {
            classroomService.checkMyClassIdList(scanner,users,lessonMap,classroomMap,myClassId,homeworkMap,pointMap,submitHomeworkMap, attendanceMap);
        }
    }







}

