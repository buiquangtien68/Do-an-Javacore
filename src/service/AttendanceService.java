package service;

import entities.Attendance;
import entities.Classroom;
import entities.User;
import utils.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class AttendanceService {
    UserService userService=new UserService();
    Utils utils=new Utils();
    public void attendanceMenu(Scanner scanner, int idClassroom, Map<Integer,Attendance> attendanceMap, ArrayList<User> users, Map<Integer, Classroom> classroomMap){
        boolean isOut=false;
        do {
            System.out.println("Điểm danh:");
            System.out.println("1 - Điểm danh theo ngày học");
            System.out.println("2 - Cập nhật điểm danh theo ngày");
            System.out.println("3 - Hiện danh sách điểm danh theo ngày");
            System.out.println("4 - Thoát điểm danh");
            System.out.println("Mời bạn chọn:");
            int select=utils.inputInteger(scanner);
            switch (select){
                case 1:
                    insertAttendance(scanner,idClassroom,attendanceMap,users,classroomMap);
                    break;
                case 2:
                    updateAttendance(scanner,idClassroom,attendanceMap,users,classroomMap);
                    break;
                case 3:
                    showAttendance(scanner,idClassroom,attendanceMap,users,classroomMap);
                    break;
                case 4:
                    isOut=true;
                    break;
            }
        }while (!isOut);
    }
    public void insertAttendance(Scanner scanner, int idClassroom, Map<Integer,Attendance> attendanceMap, ArrayList<User> users, Map<Integer, Classroom> classroomMap){
        System.out.println("Mời bạn nhập ngày học(dd/MM/yyyy):");
        LocalDate date = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Danh sách học sinh lớp:");
        System.out.println(userService.findStudentList(users,classroomMap,idClassroom));
        System.out.println("Mời bạn nhập danh sách học sinh đi học (id  học sinh 1,id học sinh 2,...):");
        String idStudents=scanner.nextLine();
        String[] idStudentsArray=idStudents.split(",");
        for (String idStudent : idStudentsArray){
            Attendance attendance=new Attendance(idClassroom,Integer.parseInt(idStudent),date);
            attendanceMap.put(attendance.getId(),attendance);
        }
    }
    public void showAttendance(Scanner scanner, int idClassroom, Map<Integer,Attendance> attendanceMap, ArrayList<User> users, Map<Integer, Classroom> classroomMap){
        ArrayList<Integer> idStudentsAttendance=new ArrayList<>();
        System.out.println("Mời bạn nhập ngày học(dd/MM/yyyy):");
        LocalDate date = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        for (Map.Entry<Integer,Attendance> attendanceEntry : attendanceMap.entrySet()){
            if (attendanceEntry.getValue().getIdClassroom()==idClassroom && attendanceEntry.getValue().getDate().equals(date)){
                idStudentsAttendance.add(attendanceEntry.getValue().getIdStudent());
            }
        }
        System.out.println("Danh sách học sinh đi học ngày "+date);
        for (User student : userService.findStudentList(users,classroomMap,idClassroom)){
            for (Integer idStudentAttendance : idStudentsAttendance){
                if (idStudentAttendance==student.getId()){
                    System.out.println(student);
                }
            }
        }
    }
    public void updateAttendance(Scanner scanner, int idClassroom, Map<Integer,Attendance> attendanceMap, ArrayList<User> users, Map<Integer, Classroom> classroomMap){
        System.out.println("Mời bạn nhập ngày học(dd/MM/yyyy):");
        LocalDate date = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        for (Map.Entry<Integer,Attendance> attendanceEntry : attendanceMap.entrySet()){
            if (attendanceEntry.getValue().getIdClassroom()==idClassroom && attendanceEntry.getValue().getDate().equals(date)){
                attendanceMap.remove(attendanceEntry);
            }
        }
        System.out.println("Danh sách học sinh lớp:");
        System.out.println(userService.findStudentList(users,classroomMap,idClassroom));
        System.out.println("Mời bạn nhập danh sách học sinh đi học (id  học sinh 1,id học sinh 2,...):");
        String idStudents=scanner.nextLine();
        String[] idStudentsArray=idStudents.split(",");
        for (String idStudent : idStudentsArray){
            Attendance attendance=new Attendance(idClassroom,Integer.parseInt(idStudent),date);
            attendanceMap.put(attendance.getId(),attendance);
        }
    }
}
