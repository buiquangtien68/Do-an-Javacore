package service;

import entities.*;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HomeworkService {
    PointService pointService=new PointService();
    SubmitHomeworkService submitHomeworkService=new SubmitHomeworkService();
    Utils utils=new Utils();
    public void insertHomework (Scanner scanner, Map<Integer, Homework> homeworkMap, int idClassroom){
        System.out.println("Mời bạn nhập số vài tập về nhà:");
        int n=utils.inputInteger(scanner);
        for (int i = 0; i < n ; i++) {
            System.out.println("Mời bạn nhập thông tin bài tập về nhà thứ "+(i+1)+":");
            System.out.println("Mời bạn nhập tên bài tập về nhà:");
            String name=scanner.nextLine();
            System.out.println("Mời bạn nhập nội dung bài tập về nhà:");
            String content=scanner.nextLine();
            Homework homework=new Homework(name,content,idClassroom);
            homeworkMap.put(homework.getId(),homework);
        }
    }
    public List<Homework> findHomeworkList( Map<Integer, Homework> homeworkMap, int idClassroom){
        int countHomework=0;
        List<Homework> homeworkList=new ArrayList<>();
        for (Map.Entry<Integer,Homework> entry : homeworkMap.entrySet()){
            if (idClassroom==entry.getValue().getIdClassroom()){
                homeworkList.add(entry.getValue());
                countHomework++;
            }
        }
        if (countHomework==0){
            System.out.println("Danh sách bài tập về nhà đang rỗng");
        }
        return homeworkList;
    }
    public int checkInputHomeworkId(Scanner scanner, Map<Integer,Homework> homeworkMap, int idClassroom){
        boolean isError = false;
        int idHomework=0;
        do {
            System.out.println("Mời bạn nhập id bài tập về nhà:");
            idHomework = utils.inputInteger(scanner);
            if (homeworkMap.get(idHomework) == null) {
                isError = true;
            } else {
                for (Map.Entry<Integer, Homework> entry : homeworkMap.entrySet()) {
                    if (entry.getValue().getIdClassroom()==idClassroom) {
                        return idHomework;
                    }
                }
            }
            if (isError) {
                System.out.println("Không tồn tai id bài tập này trong lớp, mời bạn nhập lại");
            }
        } while (isError);
        return idHomework;

    }
    public void homeworkMenuForTeacher(Scanner scanner,Map<Integer,Homework> homeworkMap, int idClassroom,Map<Integer, Point> pointMap, Map<Integer, Classroom> classroomMap,Map<Integer, SubmitHomework> submitHomeworkMap,ArrayList<User> users){
        boolean isOut=false;
        do {
            System.out.println("Quản lý bài tập về nhà");
            System.out.println("1 - Thêm bài tập về nhà");
            System.out.println("2 - Danh sách bài tập về nhà");
            System.out.println("3 - Sửa nội dung bài tập về nhà");
            System.out.println("4 - Hiển thị bài tập về nhà của học sinh");
            System.out.println("5 - Cho điểm bài tập về nhà");
            System.out.println("6 - Cập nhật lại điểm bài tập về nhà");
            System.out.println("7 - Hiển thị điểm của hoc sinh");
            System.out.println("8 - Thoát quản lý bài tập về nhà");
            System.out.println("Mời bạn chọn:");
            int select=utils.inputInteger(scanner);
            switch (select){
                case 1:
                    insertHomework(scanner,homeworkMap,idClassroom);
                    break;
                case 2:
                    System.out.println("Danh sách bài tập về nhà:");
                    System.out.println(findHomeworkList(homeworkMap,idClassroom));
                    break;
                case 3:
                    System.out.println("Danh sách bài tập về nhà:");
                    System.out.println(findHomeworkList(homeworkMap,idClassroom));
                    updateHomeworkContent(scanner,homeworkMap,checkInputHomeworkId(scanner,homeworkMap,idClassroom));
                    break;
                case 4:
                    System.out.println("Danh sách bài tập về nhà:");
                    System.out.println(findHomeworkList(homeworkMap,idClassroom));
                    submitHomeworkService.showHomeworkSubmit(users, submitHomeworkMap, checkInputHomeworkId(scanner,homeworkMap,idClassroom));
                    break;
                case 5:
                    System.out.println("Danh sách bài tập về nhà:");
                    System.out.println(findHomeworkList(homeworkMap,idClassroom));
                    pointService.insertPoint(scanner,pointMap,classroomMap,idClassroom,checkInputHomeworkId(scanner,homeworkMap,idClassroom),users);
                    break;
                case 6:
                    System.out.println("Danh sách bài tập về nhà:");
                    System.out.println(findHomeworkList(homeworkMap,idClassroom));
                    pointService.updatePoint(scanner,idClassroom,checkInputHomeworkId(scanner,homeworkMap,idClassroom),pointMap,homeworkMap,users,classroomMap);
                    break;
                case 7:
                    System.out.println("Danh sách bài tập về nhà:");
                    System.out.println(findHomeworkList(homeworkMap,idClassroom));
                    pointService.showPointForTeacher(pointMap,users,classroomMap,idClassroom,checkInputHomeworkId(scanner,homeworkMap,idClassroom));
                    break;
                case 8:
                    isOut=true;
                    break;
            }
        }while (!isOut);
    }
    public void homeworkMenuForStudent(Scanner scanner,Map<Integer,Homework> homeworkMap,Map<Integer, Point> pointMap, int idClassroom,Map<Integer, SubmitHomework> submitHomeworkMap,int idStudent){
        boolean isOut=false;
        do {
            System.out.println("Bài tập về nhà:");
            System.out.println("1 - Hiển thị nội dung bài tập về nhà");
            System.out.println("2 - Nộp bài tập về nhà");
            System.out.println("3 - Xem điểm bài tập về nhà");
            System.out.println("4 - Thoát bài tập về nhà");
            System.out.println("Mời bạn chọn:");
            int select=utils.inputInteger(scanner);
            switch (select){
                case 1:
                    System.out.println("Danh sách bài tập về nhà:");
                    System.out.println(findHomeworkList(homeworkMap,idClassroom));
                    break;
                case 2:
                    System.out.println("Danh sách bài tập về nhà:");
                    System.out.println(findHomeworkList(homeworkMap,idClassroom));
                    submitHomeworkService.insertSubmitHomework(scanner,submitHomeworkMap,checkInputHomeworkId(scanner,homeworkMap,idClassroom),idStudent);
                    break;
                case 3:
                    System.out.println("Danh sách bài tập về nhà:");
                    System.out.println(findHomeworkList(homeworkMap,idClassroom));
                    pointService.showPointForStudent(pointMap,idClassroom,idStudent,checkInputHomeworkId(scanner,homeworkMap,idClassroom));
                    break;
                case 4:
                    isOut=true;
                    break;
            }
        }while (!isOut);
    }

    public void updateHomeworkContent(Scanner scanner, Map<Integer,Homework> homeworkMap,int idHomework){
        System.out.println("Mời bạn sửa nội dung bài tập về nhà:");
        homeworkMap.get(idHomework).setContent(scanner.nextLine());
        System.out.println("Đã sửa thành công");
    }
}
