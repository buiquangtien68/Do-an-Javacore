package service;

import entities.*;
import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LessonService {
    HomeworkService homeworkService=new HomeworkService();
    Utils utils=new Utils();
    public void lessonHomeworkMenu(Scanner scanner, Map<Integer,Lesson> lessonMap, int idClassroom, Map<Integer, Homework> homeworkMap, Map<Integer, Point> pointMap, Map<Integer, Classroom> classroomMap, Map<Integer, SubmitHomework> submitHomeworkMap, ArrayList<User> users){
        boolean isOut=false;
        do {
            System.out.println("Khung chương trình:");
            System.out.println("1 - Quản lý bài giảng");
            System.out.println("2 - Quản lý bài tập về nhà");
            System.out.println("3 - Thoát khung chương trình");
            System.out.println("Mời bạn chọn:");
            int select=utils.inputInteger(scanner);
            switch (select){
                case 1:
                    findLLessonList(lessonMap,idClassroom);
                    lessonMenu(scanner,lessonMap,idClassroom);
                    break;
                case 2:
                    System.out.println("Danh sách bài tập về nhà:");
                    System.out.println(homeworkService.findHomeworkList(homeworkMap,idClassroom));
                    homeworkService.homeworkMenuForTeacher(scanner,homeworkMap,idClassroom,pointMap,classroomMap,submitHomeworkMap,users);
                    break;
                case 3:
                    isOut=true;
                    break;
            }
        }while (!isOut);
    }


    //MENU QUẢN LÝ BÀI GIẢNG
    public void lessonMenu(Scanner scanner, Map<Integer,Lesson> lessonMap,int idClassroom){
        boolean isOut=false;
        do {
            System.out.println("Quản lý bài giảng:");
            System.out.println("1 - Thêm bài giảng");
            System.out.println("2 - Danh sách bài giảng");
            System.out.println("3 - Sửa nội dung bài giảng");
            System.out.println("4 - Thoát quản lý bài giảng");
            System.out.println("Mời bạn chọn:");
            int select=utils.inputInteger(scanner);
            switch (select){
                case 1:
                    insertLesson(scanner,lessonMap,idClassroom);
                    break;
                case 2:
                    findLLessonList(lessonMap,idClassroom);
                    break;
                case 3:
                    updateLessonList(scanner,lessonMap);
                    break;
                case 4:
                    isOut=true;
                    break;
            }
        }while (!isOut);
    }

    //THÊM BÀI GIẢNG
    public void insertLesson(Scanner scanner, Map<Integer,Lesson> lessonMap, int idClassroom){
        System.out.println("Mời bạn nhập số bài giảng:");
        int n=utils.inputInteger(scanner);
        for (int i = 0; i < n ; i++) {
            System.out.println("Mời bạn nhập thông tin bài giảng thứ "+(i+1)+":");
            System.out.println("Mời bạn nhập tên Bài giảng:");
            String name=scanner.nextLine();
            System.out.println("Mời bạn nhập nội dung bài giảng:");
            String content=scanner.nextLine();
            Lesson lesson=new Lesson(name,content,idClassroom);
            lessonMap.put(lesson.getIdLesson(),lesson);
        }
    }
    //TÌM DANH SÁCH BÀI GIẢNG
    public void findLLessonList(Map<Integer,Lesson> lessonMap, int idClassroom){
        int countClass=0;
        System.out.println("Danh sách bài giảng:");
        for (Map.Entry<Integer,Lesson> entry : lessonMap.entrySet()){
            if (idClassroom==entry.getValue().getIdClassroom()){
                System.out.println(entry);
                countClass++;
            }
        }
        if (countClass==0){
            System.out.println("Danh sách bài giảng hiện đang rỗng");
        }

    }
    //SỬA NỘI DUNG BÀI GIẢNG
    public void updateLessonList(Scanner scanner, Map<Integer,Lesson> lessonMap) {
        boolean isError = false;
        do {
            System.out.println("Mời bạn nhập id bài giảng:");
            int idLesson = utils.inputInteger(scanner);
            if (lessonMap.get(idLesson) == null) {
                isError = true;
            } else {
                for (Map.Entry<Integer, Lesson> entry : lessonMap.entrySet()) {
                    if (idLesson == entry.getKey()) {
                        System.out.println("Mời bạn sửa nội dung bài giảng:");
                        entry.getValue().setContent(scanner.nextLine());
                        System.out.println("Đã sửa thành công");
                        isError = false;
                    }
                }
            }
            if (isError) {
                System.out.println("Không tồn tai id này, mời bạn nhập lại");
            }
        } while (isError);
    }

}
