package service;

import entities.*;
import utils.Utils;
import view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserService {
    Utils utils=new Utils();
    public void inputRegister(Scanner scanner, ArrayList<User> users){
        System.out.println("=======ĐĂNG KÝ=======");
        System.out.println("Mời bạn nhập username:");
        String username=scanner.nextLine();
        User user=new User();
        user.setUsername(username);
        if (checkUsername(username,users)){
            inputRegister(scanner,users);
        }else {
            System.out.println("Mời bạn nhập email:");
            String email = scanner.nextLine();
            if (checkEmail(email,users)){
                inputRegister(scanner,users);
            }else {
                user.setEmail(email);
                System.out.println("Mời bạn nhập password:");
                String password=scanner.nextLine();
                if(checkPassword(password)){
                    inputRegister(scanner,users);
                }else{
                    user.setPassword(password);
                    users.add(user);
                    chooseRole(scanner,user);
                    System.out.println("Đăng ký thành công!!!");
                    System.out.println("Bạn có muốn nhập thêm không: ");
                    String select = scanner.nextLine();
                    if (select.equalsIgnoreCase("Y")) {
                        inputRegister(scanner,users);
                    }
                }
            }
        }
    }
    private void chooseRole(Scanner scanner, User user){
        System.out.println("Bạn muốn đăng ký dưới vai trò là:");
        System.out.println("1 - Giáo viên");
        System.out.println("2 - Học sinh");
        System.out.println("Mời bạn chọn:");
        int role=utils.inputInteger(scanner);
        System.out.println("Mời bạn nhập tên:");
        user.setName(scanner.nextLine());
        System.out.println("Mời bạn nhập tuổi:");
        user.setAge(utils.inputInteger(scanner));
        System.out.println("Mời bạn nhập địa chỉ:");
        user.setAddress(scanner.nextLine());
        switch (role){
            case 1:
                user.setRole(1);
                break;
            case 2:
                user.setRole(2);
                break;
        }
    }
    private boolean checkUsername(String userName, ArrayList<User> users){
        boolean isError =false;
        for (User userValue : users){
            if (userValue.getUsername().equals(userName)){
                isError=true;
                System.out.println("Username đã tồn tại");
                break;
            }
        }
        return isError;
    }
    public boolean checkEmail(String email, ArrayList<User> users){
        boolean isError =false;
        String regex = "^(.+)@(.+)$";
        if(email.matches(regex)){
            for (User userValue : users){
                if (userValue.getEmail().equals(email)){
                    isError = true;
                    System.out.println("Email này đã được sử dụng!");
                    break;
                }
            }
        }else{
            System.out.println("Email sai định dạng !");
            isError = true;
        }
        return isError;
    }

    private boolean checkPassword(String password){
        String regex = "^(?=.*[A-Z])(?=.*[.,-_;])[A-Za-z.,-_;]{7,15}$";
        boolean isError = false;
        if(!password.matches(regex)) {
            System.out.println("Mật khẩu sai định dạng");
            isError=true;
        }
        return isError;
    }
    public boolean inputLogin(Scanner scanner, ArrayList<User> users, UserService userService, Map<Integer,Lesson> lessonMap, Map<Integer, Classroom> classroomMap, Map<Integer, Homework> homeworkMap, Map<Integer, Point> pointMap,Map<Integer, SubmitHomework> submitHomeworkMap,Map<Integer,Schedule> scheduleMap, Map<Integer,Attendance> attendanceMap) {
        TeacherService teacherService=new TeacherService();
        StudentService studentService=new StudentService();
        AdminService adminService=new AdminService();
        String selectLoop="";
        boolean isUserNameRight = false;
        boolean isContinue = true;
        do {
            System.out.println("=======ĐĂNG NHẬP=======");
            System.out.println("Mời bạn nhập username:");
            String username = scanner.nextLine();
            for (User userValue : users) {
                if (username.equals(userValue.getUsername())) {
                    System.out.println("Mời bạn nhập password:");
                    String password = scanner.nextLine();
                    isUserNameRight = true;
                    if (password.equals(userValue.getPassword())) {
                        System.out.println("Đăng nhập thành công!!!");
                        if (userValue.getRole()==1){
                            isContinue = teacherService.teacherMenu(scanner,users,userValue,userService,lessonMap,classroomMap,homeworkMap,pointMap,submitHomeworkMap,scheduleMap,attendanceMap);
                        }else if (userValue.getRole()==2){
                            isContinue = studentService.studentMenu(scanner,users,userValue,userService,lessonMap,classroomMap,homeworkMap,pointMap,submitHomeworkMap,scheduleMap,attendanceMap);
                        }else {
                            isContinue = adminService.adminMenu(scanner,users,userValue,userService,lessonMap,classroomMap,homeworkMap,pointMap,submitHomeworkMap,scheduleMap,attendanceMap);
                        }
                        break;
                    } else {
                        System.out.println("Mật khẩu sai!");
                        System.out.println("Mời bạn chọn:");
                        System.out.println("1-Đăng nhập lại");
                        System.out.println("2-Quên mật khẩu");
                        System.out.println("Mời bạn chọn:");
                        int select = utils.inputInteger(scanner);
                        switch (select) {
                            case 1:
                                isContinue = inputLogin(scanner,users,userService,lessonMap,classroomMap,homeworkMap,pointMap,submitHomeworkMap,scheduleMap,attendanceMap);
                                break;
                            case 2:
                                forgetPassword(scanner,users);
                                break;
                        }
                        break;
                    }
                }
            }
            if(!isUserNameRight){
                System.out.println("Kiểm tra lại username");
                System.out.println("Bạn có muốn nhập lại không(Y/N): ");
                selectLoop = scanner.nextLine();
            }
        }while (selectLoop.equalsIgnoreCase("Y"));
        return isContinue;
    }
    private void forgetPassword(Scanner scanner, ArrayList<User> users){
        boolean isEmailRight =false;
        System.out.println("Mời bạn nhập email:");
        String email= scanner.nextLine();
        for (User userValue : users){
            if (email.equals(userValue.getEmail())){
                System.out.println("Mời bạn nhập lại password:");
                String password= scanner.nextLine();
                userValue.setPassword(password);
                System.out.println("Đổi mật khẩu thành công!!!");
                isEmailRight = true;
                break;
            }
        }
        if (!isEmailRight){
            System.out.println("Kiểm tra lại email");
            System.out.println("Bạn có muốn nhập lại không (Y/N): ");
            String selectLoop = scanner.nextLine();
            if (selectLoop.equalsIgnoreCase("y")){
                forgetPassword(scanner,users);
            }
        }
    }

    public void accountMenu(Scanner scanner,ArrayList<User> users, User user, UserService userService){
        int select = 0;
        boolean isSettingOut=false;
        do {
            System.out.println("Cài đặt tài khoản:");
            System.out.println("1 - Thay đổi username");
            System.out.println("2 - Thay đổi email");
            System.out.println("3 - Thay đổi mật khẩu");
            System.out.println("4 - Thoát cài đặt tài khoản");
            System.out.println("Mời bạn lựa chọn:");
            select=utils.inputInteger(scanner);
            switch (select){
                case 1:
                    updateUsername(scanner,users,user);
                    break;
                case 2:
                    updateEmail(scanner,users,user);
                    break;
                case 3:
                    updatePassword(scanner,user);
                    break;
                case 4:
                    isSettingOut=true;
            }
        }while (!isSettingOut);

    }
    public void updateUsername(Scanner scanner,ArrayList<User> users, User user){
        System.out.println("Mời bạn nhập username mới:");
        String username = scanner.nextLine();
        boolean isError = checkUsername(username, users);
        if (!isError){
            user.setUsername(username);
        }
    }
    public void updateEmail(Scanner scanner,ArrayList<User> users, User user){
        System.out.println("Mời bạn nhập email mới:");
        String email = scanner.nextLine();
        boolean isError = checkEmail(email, users);
        if (!isError){
            user.setEmail(email);
        }
    }
    public void updatePassword(Scanner scanner, User user){
        System.out.println("Mời bạn nhập password mới:");
        String password= scanner.nextLine();
        boolean isError = checkPassword(password);
        if (!isError) {
            user.setPassword(password);
        }
    }
    public void logOut(Scanner scanner, ArrayList<User> users, UserService userService,Map<Integer,Lesson> lessonMap, Map<Integer,Classroom> classroomMap,Map<Integer, Homework> homeworkMap,Map<Integer, Point> pointMap,Map<Integer, SubmitHomework> submitHomeworkMap, Map<Integer,Schedule> scheduleMap, Map<Integer,Attendance> attendanceMap){
        Menu menu=new Menu();
        menu.optionMenu(scanner,users,userService,lessonMap, classroomMap,homeworkMap,pointMap,submitHomeworkMap,scheduleMap,attendanceMap);
    }
    public void infoMenu(Scanner scanner, User user){
        boolean isOut=false;
        do {
            System.out.println("Hồ sơ cá nhân:");
            System.out.println("1 - Thông tin cá nhân");
            System.out.println("2 - Cập nhật thông tin cá nhân");
            System.out.println("3 - Thoát hồ sơ cá nhân");
            System.out.println("Mời bạn chọn:");
            int select=utils.inputInteger(scanner);
            switch (select){
                case 1:
                    printInfo(user);
                    break;
                case 2:
                    updateInfoMenu(scanner,user);
                    break;
                case 3:
                    isOut=true;
                    break;
            }
        }while (!isOut);
    }
    public void printInfo(User user){
        System.out.println("Tên của bạn là:");
        System.out.println(user.getName());
        System.out.println("Tuổi của bạn là:");
        System.out.println(user.getAge());
        System.out.println("Địa chỉ của bạn là:");
        System.out.println(user.getAddress());
    }
    public void updateInfoMenu(Scanner scanner, User user){
        boolean isOut=false;
        do {
            System.out.println("Cập nhật thông tin cá nhân:");
            System.out.println("1 - Cập nhật tên");
            System.out.println("2 - Cập nhật tuổi");
            System.out.println("3 - Cập nhật địa chỉ");
            System.out.println("4 - Thoát hồ sơ cá nhân");
            System.out.println("Mời bạn chọn:");
            int select=Integer.parseInt(scanner.nextLine());
            switch (select){
                case 1:
                    updateName(scanner,user);
                    break;
                case 2:
                    updateAge(scanner,user);
                    break;
                case 3:
                    updateAddress(scanner,user);
                    break;
                case 4:
                    isOut=true;
                    break;
            }
        }while (!isOut);
    }
    public void updateName(Scanner scanner, User user){
        System.out.println("Mời bạn nhập tên mới:");
        user.setName(scanner.nextLine());
    }
    public void updateAge(Scanner scanner, User user){
        System.out.println("Mời bạn cập nhật tuổi: ");
        user.setAge(utils.inputInteger(scanner));
    }
    public void updateAddress(Scanner scanner, User user){
        System.out.println("Mời bạn cập nhật địa chỉ:");
        user.setAddress(scanner.nextLine());
    }
    //tìm danh sách học sinh cho user giáo viên
    public List<User> findStudentList(ArrayList<User> users, Map<Integer,Classroom> classroomMap, int idClassroom){
        List<User> studentList = new ArrayList<>();
        for (Integer idStudent:classroomMap.get(idClassroom).getStudents()){
            for (User user:getAllUserByRole(2,users)){
                if(user.getId()==idStudent){
                    //System.out.println(user);
                    studentList.add(user);
                }
            }
        }
        return studentList;
    }

    public List<User> getAllUserByRole(int role, ArrayList<User> users){
        List<User> userList = new ArrayList<>();
        for (User user: users){
            if(user.getRole()== role){
                userList.add(user);
            }
        }
        return userList;
    }
}
