package service;

import entities.*;
import utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdminService {
    UserService userService =new UserService();
    Utils utils=new Utils();
    public boolean adminMenu(Scanner scanner, ArrayList<User> users, User user, UserService userService, Map<Integer, Lesson> lessonMap, Map<Integer,Classroom> classroomMap, Map<Integer, Homework> homeworkMap, Map<Integer, Point> pointMap, Map<Integer, SubmitHomework> submitHomeworkMap,Map<Integer,Schedule> scheduleMap, Map<Integer,Attendance> attendanceMap ){
        int select = 0;
        boolean isContinue = true;
        do {
            System.out.println("Chào mừng "+user.getUsername()+", bạn có thể thực hiện các công việc sau:");
            System.out.println("1 - Tạo lớp học mới");
            System.out.println("2 - Cập nhật thông tin lớp học");
            System.out.println("3 - Hiển thị danh sách lớp học");
            System.out.println("4 - Hiển thị lịch học theo id lớp học");
            System.out.println("5 - Cài đặt tài khoản");
            System.out.println("6 - Đăng xuất");
            System.out.println("0 - Thoát chương trình ");
            System.out.println("Mời bạn chọn:");
            select=utils.inputInteger(scanner);
            switch (select){
                case 0:
                    isContinue=false;
                    return isContinue;
                case 1:
                    insertClassroom(scanner,classroomMap,users,scheduleMap);
                    break;
                case 2:
                    updateClassroomMenu(scanner,users,classroomMap,scheduleMap);
                    break;
                case 3:
                    System.out.println(classroomMap);
                    break;
                case 4:
                    showSchedule(scanner,classroomMap,scheduleMap);
                    break;
                case 5:
                    userService.accountMenu( scanner, users,  user,  userService);
                    break;
                case 6:
                    userService.logOut(scanner,users,userService,lessonMap,classroomMap,homeworkMap,pointMap,submitHomeworkMap,scheduleMap,attendanceMap);
                    break;
            }
        }while (select!=0);
        return isContinue;
    }

    public void insertClassroom(Scanner scanner, Map<Integer, Classroom > classroomMap, ArrayList<User> users, Map<Integer,Schedule> scheduleMap){
        System.out.println("Mời bạn nhập thông tin lớp học:");
        Classroom classroom=new Classroom();
        //Nhập lịch trình
        System.out.println("Lớp học sẽ diễn ra vào buổi nào trong ngày:");
        System.out.println("1 - Sáng");
        System.out.println("2 - Chiều");
        System.out.println("Mời bạn chọn:");
        int timeFrame=utils.inputInteger(scanner);
        System.out.println("Lớp học sẽ diễn ra vào các thứ nào trong tuần:");
        System.out.println("1 - Thứ 2, 4, 6");
        System.out.println("2 - Thứ 3, 5, 7");
        System.out.println("Mời bạn chọn:");
        int timeSchedule=utils.inputInteger(scanner);
        Schedule schedule=new Schedule(classroom.getIdClassroom(),timeFrame,timeSchedule);
        scheduleMap.put(schedule.getIdSchedule(),schedule);
        System.out.println("Mời bạn nhập ngày bắt đầu lớp học:");
        LocalDate startDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Mời bạn nhập ngày kết thúc lớp học:");
        LocalDate finishDate=LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        classroom.setStartDate(startDate);
        classroom.setFinishDate(finishDate);
        //Nhập thông tin lớp
        System.out.println("Mời bạn nhập tên lớp học:");
        classroom.setName(scanner.nextLine());
        System.out.println("Danh sách giáo viên:");
        checkInputTeacher(scanner,timeFrame,timeSchedule,classroomMap,users,scheduleMap,classroom);
        ArrayList<Integer> students =new ArrayList<>();
        System.out.println("Danh sách học sinh:");
        System.out.println(userService.getAllUserByRole(2,users));
        System.out.println("Mời bạn nhập số học sinh muốn thêm vào lớp:");
        int nStudent=utils.inputInteger(scanner);
        for (int i = 0; i < nStudent ; i++) {
            System.out.println("Mời bạn nhập id học sinh thứ "+(i+1)+":");
            while (true){
                int idStudent = utils.inputInteger(scanner);
                if(isStudentAttendClass(idStudent, startDate, finishDate, classroomMap)){
                    System.out.println("Học sinh chưa hoàn thành lớp trước đó, mời bạn nhập lại");
                }else {
                    students.add(idStudent);
                    break;
                }
            }
        }
        classroom.setStudents(students);
        classroomMap.put(classroom.getIdClassroom(),classroom);
    }
    private boolean isStudentAttendClass(int newIdStudent, LocalDate newClassStartDate,LocalDate newClassFinishDate, Map<Integer, Classroom> classroomMap){
        for (Map.Entry<Integer,Classroom> classroomEntry : classroomMap.entrySet()){
            Classroom currentClass = classroomEntry.getValue();
            if(isDateInRange(newClassStartDate, currentClass.getStartDate(), currentClass.getFinishDate())
                    || isDateInRange(newClassFinishDate, currentClass.getStartDate(), currentClass.getFinishDate())
                    || isDateInRange(currentClass.getStartDate(), newClassStartDate, newClassFinishDate)
                    || isDateInRange(currentClass.getFinishDate(), newClassStartDate, newClassFinishDate)){
                for (Integer idStudent : currentClass.getStudents()){
                    if(idStudent == newIdStudent){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    protected boolean isDateInRange(LocalDate dateToCheck, LocalDate startDate, LocalDate endDate) {
        return !(dateToCheck.isBefore(startDate) || dateToCheck.isAfter(endDate));
    }

    private int checkTeacherSchedule(int idTeacher, int timeFrame, int timeSchedule,Map<Integer,Schedule> scheduleMap, Map<Integer, Classroom > classroomMap){
        int countClassSameSchedule=0;
        List<Integer> myClassList=new ArrayList<>();
        for (Map.Entry<Integer,Classroom> classroomEntry:classroomMap.entrySet()){
            if (idTeacher==classroomEntry.getValue().getTeacherId()){
                myClassList.add(classroomEntry.getKey());
            }
        }
        for (Map.Entry<Integer,Schedule> scheduleEntry:scheduleMap.entrySet()){
            for (Integer idClassroom: myClassList){
                if (scheduleEntry.getValue().getIdClassroom()==idClassroom){
                    if (timeSchedule==scheduleEntry.getValue().getTimeSchedule() && timeFrame==scheduleEntry.getValue().getTimeFrame()){
                        System.out.println("Giáo viên đã bị trùng lịch trình");
                        countClassSameSchedule++;
                    }
                }
            }
        }
        return countClassSameSchedule;
    }
    private void checkInputTeacher(Scanner scanner, int timeFrame, int timeSchedule, Map<Integer, Classroom > classroomMap, ArrayList<User> users, Map<Integer,Schedule> scheduleMap,Classroom classroom){
        boolean isError=false;
        do {
            System.out.println(userService.getAllUserByRole(1,users));
            System.out.println("Mời bạn nhập id của giáo viên dạy lớp này:");
            int idTeacher=utils.inputInteger(scanner);
            if (checkTeacherSchedule(idTeacher,timeFrame,timeSchedule,scheduleMap,classroomMap)==0){
                classroom.setTeacherId(idTeacher);
                isError=false;
            }else {
                isError=true;
            }
        }while (isError);
    }

    public void updateClassroomMenu(Scanner scanner, ArrayList<User> users, Map<Integer,Classroom> classroomMap,Map<Integer,Schedule> scheduleMap) {
        boolean isOut=false;
        do {
            System.out.println("Cập nhật thông tin lớp học:");
            System.out.println("1 - Cập nhật id giáo viên");
            System.out.println("2 - Cập nhật danh sách học sinh");
            System.out.println("3 - Cập nhật lịch học");
            System.out.println("4 - Thoát hồ sơ cá nhân");
            System.out.println("Mời bạn chọn:");
            int select=utils.inputInteger(scanner);
            switch (select){
                case 1:
                    updateIdTeacherForClassroom(scanner,classroomMap,users,scheduleMap);
                    break;
                case 2:
                    updateStudentListForClassroom(scanner,classroomMap,users);
                    break;
                case 3:
                    updateScheduleForClassroom(scanner,classroomMap,scheduleMap);
                    break;
                case 4:
                    isOut=true;
                    break;
            }
        }while (!isOut);
    }
    public void updateIdTeacherForClassroom(Scanner scanner, Map<Integer, Classroom > classroomMap, ArrayList<User> users, Map<Integer,Schedule> scheduleMap){
        boolean isError=false;
        int idClassroom=0;
        do {
            System.out.println("Mời bạn nhập id lớp học:");
            idClassroom=utils.inputInteger(scanner);
            if (classroomMap.get(idClassroom)==null){
                isError=true;
            }
        }while (isError);
        for (Map.Entry<Integer,Schedule> scheduleEntry: scheduleMap.entrySet()){
            if (scheduleEntry.getValue().getIdClassroom()==idClassroom){
                for (Map.Entry<Integer,Classroom> classroomEntry: classroomMap.entrySet()){
                    if (classroomEntry.getKey()==idClassroom){
                        checkInputTeacher(scanner,scheduleEntry.getValue().getTimeFrame(),scheduleEntry.getValue().getIdSchedule(),classroomMap,users,scheduleMap,classroomEntry.getValue());
                    }
                }
            }
        }
    }
    public void updateScheduleForClassroom(Scanner scanner, Map<Integer, Classroom > classroomMap, Map<Integer,Schedule> scheduleMap){
        boolean isError=false;
        int idClassroom=0;
        do {
            System.out.println("Mời bạn nhập id lớp học:");
            idClassroom=utils.inputInteger(scanner);
            if (classroomMap.get(idClassroom)==null){
                isError=true;
            }
        }while (isError);
        for (Map.Entry<Integer,Schedule> entry:scheduleMap.entrySet()){
            if (entry.getValue().getIdClassroom()==idClassroom){
                System.out.println();
                System.out.println("Lớp học sẽ diễn ra vào các thứ nào trong tuần:");
                System.out.println("1 - Thứ 2, 4, 6");
                System.out.println("2 - Thứ 3, 5, 7");
                System.out.println("Mời bạn chọn:");
                entry.getValue().setTimeSchedule(utils.inputInteger(scanner));
                System.out.println("Lớp học sẽ diễn ra vào buổi nào trong ngày:");
                System.out.println("1 - Sáng");
                System.out.println("2 - Chiều");
                System.out.println("Mời bạn chọn:");
                entry.getValue().setTimeFrame(utils.inputInteger(scanner));
            }
        }
    }
    public void updateStudentListForClassroom(Scanner scanner, Map<Integer, Classroom > classroomMap, ArrayList<User> users){
        boolean isError=false;
        int idClassroom=0;
        do {
            System.out.println("Mời bạn nhập id lớp học:");
            idClassroom=utils.inputInteger(scanner);
            if (classroomMap.get(idClassroom)==null){
                isError=true;
            }
        }while (isError);
        ArrayList<Integer> students =new ArrayList<>();
        System.out.println("Danh sách học sinh:");
        System.out.println(userService.getAllUserByRole(2,users));
        System.out.println("Mời bạn nhập số học sinh muốn thêm vào lớp:");
        int nStudent=utils.inputInteger(scanner);
        for (int i = 0; i < nStudent ; i++) {
            System.out.println("Mời bạn nhập id học sinh thứ "+(i+1)+":");
            while (true){
                int idStudent = utils.inputInteger(scanner);
                if(isStudentAttendClass(idStudent, classroomMap.get(idClassroom).getStartDate(), classroomMap.get(idClassroom).getFinishDate(), classroomMap)){
                    System.out.println("Học sinh chưa hoàn thành lớp trước đó, mời bạn nhập lại");
                }else {
                    students.add(idStudent);
                    break;
                }
            }
        }
        classroomMap.get(idClassroom).setStudents(students);
    }

    public void showSchedule(Scanner scanner, Map<Integer, Classroom > classroomMap,Map<Integer,Schedule> scheduleMap){
        System.out.println("Danh sách lớp học:");
        System.out.println(classroomMap);
        boolean isError=false;
        int idClassroom=0;
        do {
            System.out.println("Mời bạn nhập id lớp học:");
            idClassroom=utils.inputInteger(scanner);
            if (classroomMap.get(idClassroom)==null){
                isError=true;
            }
        }while (isError);
        for (Map.Entry<Integer,Schedule> scheduleEntry : scheduleMap.entrySet()){
            if (scheduleEntry.getValue().getIdClassroom()==idClassroom){
                System.out.println("Lịch học của lớp này là:");
                System.out.println("Lớp học sẽ diễn ra vào các thứ trong tuần:");
                switch (scheduleEntry.getValue().getTimeSchedule()){
                    case 1:
                        System.out.println("Thứ 2, 4, 6");
                        break;
                    case 2:
                        System.out.println("Thứ 3, 5, 7");
                        break;
                }
                System.out.println("Lớp học sẽ diễn ra vào buổi:");
                switch (scheduleEntry.getValue().getTimeFrame()){
                    case 1:
                        System.out.println("Sáng");
                        break;
                    case 2:
                        System.out.println("Chiều");
                        break;
                }
            }

        }
    }
}
