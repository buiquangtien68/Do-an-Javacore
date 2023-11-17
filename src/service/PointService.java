package service;

import entities.*;
import utils.Utils;

import java.util.*;

public class PointService {
    UserService userService=new UserService();
    Utils utils=new Utils();
    public void insertPoint(Scanner scanner, Map<Integer, Point> pointMap, Map<Integer, Classroom> classroomMap, int idClassroom, int idHomework,ArrayList<User> users){
        Point point=new Point();
        point.setIdHomework(idHomework);
        point.setIdClassroom(idClassroom);
        boolean isError=false;
        do {
            System.out.println("Danh sách học sinh lớp:");
            System.out.println(userService.findStudentList(users,classroomMap,idClassroom));
            System.out.println("Mời bạn nhập id học sinh:");
            int idStudent = utils.inputInteger(scanner);
            if (classroomMap.get(idClassroom).getStudents().contains(idStudent)) {
                System.out.println("Mời bạn nhập điểm:");
                double score = utils.inputDouble(scanner);
                point.setIdStudent(idStudent);
                point.setScore(score);
                pointMap.put(point.getId(), point);
                isError=true;
            }
            if (!isError){
                System.out.println("Không tồn tại học sinh này trong lớp, mời bạn nhập lại");
            }
        }while (!isError);
    }
    public void updatePoint(Scanner scanner, int idClassroom, int idHomework, Map<Integer, Point> pointMap, Map<Integer,Homework> homeworkMap, ArrayList<User> users,Map<Integer,Classroom> classroomMap ){
        HomeworkService homeworkService=new HomeworkService();
        boolean isExist=false;
        System.out.println("Danh sách học sinh lớp:");
        System.out.println(userService.findStudentList(users,classroomMap,idClassroom));
        System.out.println("Mời bạn nhập id học sinh");
        int idStudent=utils.inputInteger(scanner);
        for (Map.Entry<Integer,Point> entry: pointMap.entrySet()){
            if (entry.getValue().getIdClassroom()==idClassroom && entry.getValue().getIdHomework()==idHomework ){
                //System.out.println(userService.findStudentList());
                if (entry.getValue().getIdStudent()==idStudent){
                    System.out.println("Mời bạn sửa điểm:");
                    entry.getValue().setScore(utils.inputDouble(scanner));
                    System.out.println("Sửa thành công");
                    isExist=true;
                    break;
                }
            }
        }
        if (!isExist){
            System.out.println("Không tồn tại học sinh này, vui lòng nhập lại");
            System.out.println("Danh sách bài tập về nhà:");
            System.out.println(homeworkService.findHomeworkList(homeworkMap,idClassroom));
            updatePoint(scanner,idClassroom,idHomework,pointMap,homeworkMap,users,classroomMap);
        }
    }
    public void showPointForStudent(Map<Integer, Point> pointMap, int idClassroom,int idStudent, int idHomework){
        for (Map.Entry<Integer, Point> entry:pointMap.entrySet()){
            if (entry.getValue().getIdHomework()==idHomework && entry.getValue().getIdStudent()==idStudent && entry.getValue().getIdClassroom()==idClassroom){
                System.out.println("Điểm của bạn là:");
                System.out.println(entry.getValue().getScore());
            }
        }
    }
    public void showPointForTeacher(Map<Integer, Point> pointMap, ArrayList<User> users,Map<Integer,Classroom> classroomMap, int idClassroom, int idHomework){
        for (Map.Entry<Integer, Point> entry:pointMap.entrySet()){
            if(entry.getValue().getIdClassroom()==idClassroom && entry.getValue().getIdHomework()==idHomework){
                for (User user:userService.findStudentList(users,classroomMap,idClassroom)){
                    if (entry.getValue().getIdStudent()==user.getId()){
                        System.out.println("Điểm của "+user.getName()+" là:");
                        System.out.println(entry.getValue().getScore());
                    }
                }
            }
        }
    }


    public Map<User,Double> calculatePointAverage(Map<Integer, Point> pointMap,Map<Integer, Homework> homeworkMap,  int idClassroom,ArrayList<User> users,Map<Integer,Classroom> classroomMap) {
        HomeworkService homeworkService=new HomeworkService();
        Map<User,Double> pointAverageMap=new HashMap<>();
        for (User user: userService.findStudentList(users,classroomMap,idClassroom)){
            double sum=0;
            for (Map.Entry<Integer, Point> pointEntry : pointMap.entrySet()) {
                if (pointEntry.getValue().getIdStudent()==user.getId()){
                    sum+=pointEntry.getValue().getScore();
                }
            }
            double pointAverage=sum/homeworkService.findHomeworkList(homeworkMap,idClassroom).size();
            //System.out.println("Điểm trung bình của "+user.getName()+" là:"+pointAverage);
            pointAverageMap.put(user,pointAverage);
        }
        return pointAverageMap;
    }
    public void rankings(Map<Integer, Point> pointMap,Map<Integer, Homework> homeworkMap,  int idClassroom,ArrayList<User> users,Map<Integer,Classroom> classroomMap){
        List<Map.Entry<User,Double>> entryList=new ArrayList<>(calculatePointAverage(pointMap,homeworkMap,idClassroom,users,classroomMap).entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<User,Double>>() {
            @Override
            public int compare(Map.Entry<User,Double> o1, Map.Entry<User,Double> o2) {
                return  (o2.getValue().compareTo(o1.getValue()));
            }
        });
        Map<User,Double> sortedMap=new HashMap<>();
        for (Map.Entry<User,Double> entry:entryList){
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<User,Double> entry:sortedMap.entrySet()){
            System.out.println("id học sinh: "+entry.getKey().getId()+", Họ tên: "+entry.getKey().getName()+", Điểm trung bình: "+entry.getValue());
        }
    }
}