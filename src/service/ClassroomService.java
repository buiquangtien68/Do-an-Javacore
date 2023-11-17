package service;

import entities.*;
import utils.Utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class ClassroomService {
    LessonService lessonService=new LessonService();
    UserService userService=new UserService();
    HomeworkService homeworkService=new HomeworkService();
    PointService pointService=new PointService();
    AttendanceService attendanceService=new AttendanceService();
    Utils utils=new Utils();
    public void checkMyClassIdList(Scanner scanner, ArrayList<User> users, Map<Integer, Lesson> lessonMap, Map<Integer,Classroom> classroomMap, ArrayList<Integer> myClassId, Map<Integer, Homework> homeworkMap, Map<Integer, Point> pointMap, Map<Integer, SubmitHomework> submitHomeworkMap, Map<Integer,Attendance> attendanceMap){
        boolean isError=false;
        do {
            System.out.println("Mời bạn nhập id lớp:");
            int idClassroom=utils.inputInteger(scanner);
            if (classroomMap.get(idClassroom)==null ){
                isError=true;
            }else {
                for (Integer integer:myClassId){
                    if (idClassroom==integer){
                        myClassMenuForTeacher(scanner,users,lessonMap,classroomMap,idClassroom,homeworkMap,pointMap,submitHomeworkMap, attendanceMap);
                        isError=false;
                        break;
                    }else {
                        isError=true;
                    }
                }
            }
            if (isError){
                System.out.println("Không tồn tai id này hoặc bạn không dạy lớp có id này, mời bạn nhập lại");
            }
        }while (isError);
    }
    //NỘI DUNG BÊN TRONG LỚP ĐÓ
    public void myClassMenuForTeacher(Scanner scanner,ArrayList<User> users,Map<Integer,Lesson> lessonMap, Map<Integer,Classroom> classroomMap, int idClassroom,Map<Integer, Homework> homeworkMap,Map<Integer, Point> pointMap,Map<Integer, SubmitHomework> submitHomeworkMap, Map<Integer,Attendance> attendanceMap){
        boolean isOut=false;
        do {
            System.out.println("Đây là lớp "+classroomMap.get(idClassroom).getName()+" gồm các nội dung sau:");
            System.out.println("1 - Khung chương trình");
            System.out.println("2 - Điểm danh");
            System.out.println("3 - Học viên");
            System.out.println("4 - Bảng xếp hạng");
            System.out.println("5 - Thoát lớp học này");
            System.out.println("Mời bạn chọn:");
            int select=utils.inputInteger(scanner);
            switch (select){
                case 1:
                    lessonService.lessonHomeworkMenu(scanner,lessonMap,idClassroom,homeworkMap,pointMap,classroomMap,submitHomeworkMap,users);
                    break;
                case 2:
                    attendanceService.attendanceMenu(scanner,idClassroom,attendanceMap,users,classroomMap);
                    break;
                case 3:
                    System.out.println(userService.findStudentList(users,classroomMap,idClassroom));
                    break;
                case 4:
                    pointService.rankings(pointMap,homeworkMap,idClassroom,users,classroomMap);
                    break;
                case 5:
                    isOut=true;
                    break;
            }
        }while (!isOut);
    }
    public void myClassMenuForStudent (Scanner scanner,ArrayList<User> users,Map<Integer,Lesson> lessonMap, Map<Integer,Classroom> classroomMap, int idClassroom,Map<Integer, Homework> homeworkMap,Map<Integer, Point> pointMap,Map<Integer, SubmitHomework> submitHomeworkMap,int idStudent){
        boolean isOut=false;
        do {
            System.out.println("Đây là lớp "+classroomMap.get(idClassroom).getName()+" gồm các nội dung sau:");
            System.out.println("1 - Khung chương trình");
            System.out.println("2 - Bài tập về nhà");
            System.out.println("3 - Bảng xếp hạng");
            System.out.println("4 - Thoát lớp học này");
            System.out.println("Mời bạn chọn:");
            int select=utils.inputInteger(scanner);
            switch (select){
                case 1:
                    lessonService.findLLessonList(lessonMap,idClassroom);
                    break;
                case 2:
                    homeworkService.homeworkMenuForStudent(scanner,homeworkMap,pointMap,idClassroom,submitHomeworkMap,idStudent);
                    break;
                case 3:
                    pointService.rankings(pointMap,homeworkMap,idClassroom,users,classroomMap);
                    break;
                case 4:
                    isOut=true;
                    break;
            }
        }while (!isOut);
    }

}
