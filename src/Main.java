import entities.*;
import service.SubmitHomeworkService;
import service.UserService;
import view.Menu;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        //role 1:giáo viên, 2:học sinh
        User user=new User("admin","admin@gmail.com","Admin@123");
        User user1=new User("tien1","tien1@gmail.com","Tien1@123","Tiến1",23,"Hà Nội",2);
        User user2=new User("tien2","tien2@gmail.com","Tien2@123","Tiến2",23,"Hà Nội",2);
        User user3=new User("tien3","tien3@gmail.com","Tien3@123","Tiến3",23,"Hà Nội",2);
        User user4=new User("yen1","yen1@gmail.com","Yen1@123","Yến1",30,"Hà Nội",1);
        User user5=new User("yen2","yen2@gmail.com","Yen2@123","Yến2",30,"Hà Nội",1);
        ArrayList<User> users=new ArrayList<>(Arrays.asList(user,user1,user2,user3,user4,user5));
        Map<Integer, Classroom > classroomMap =new HashMap<>();
        Classroom classroom=new Classroom("class1", user4.getId(),new ArrayList<>(Arrays.asList(user1.getId(),user2.getId())) ,LocalDate.of(2023,01,01), LocalDate.of(2023,02,01));
        Classroom classroom1=new Classroom("class2", user5.getId(),new ArrayList<>(Arrays.asList(user3.getId())) ,LocalDate.of(2023,03,01), LocalDate.of(2023,04,01));
        classroomMap.put(classroom.getIdClassroom(),classroom);
        classroomMap.put(classroom1.getIdClassroom(),classroom1);
        Map<Integer,Lesson> lessonMap=new HashMap<>();
        Lesson lesson=new Lesson("Bài 1","Đề bài bài 1", classroom.getIdClassroom());
        Lesson lesson1=new Lesson("Bài 2","Đề bài bài 2", classroom.getIdClassroom());
        Lesson lesson2=new Lesson("Bài 1","Đề bài bài 1", classroom1.getIdClassroom());
        lessonMap.put(lesson.getIdLesson(),lesson);
        lessonMap.put(lesson1.getIdLesson(),lesson1);
        lessonMap.put(lesson2.getIdLesson(),lesson2);
        Map<Integer, Homework> homeworkMap=new HashMap<>();
        Homework homework=new Homework("Bài tập 1", "Đề bài tập 1", classroom.getIdClassroom());
        Homework homework1=new Homework("Bài tập 2", "Đề bài tập 2", classroom.getIdClassroom());
        Homework homework2=new Homework("Bài tập 1", "Đề bài tập 1", classroom1.getIdClassroom());
        homeworkMap.put(homework.getId(),homework);
        homeworkMap.put(homework1.getId(),homework1);
        homeworkMap.put(homework2.getId(),homework2);
        Map<Integer,Point> pointMap=new HashMap<>();
        Point point=new Point(classroom.getIdClassroom(),homework.getId(),user1.getId(),7.5);
        Point point1=new Point(classroom.getIdClassroom(),homework.getId(),user2.getId(),6);
        Point point2=new Point(classroom.getIdClassroom(),homework1.getId(),user1.getId(),8);
        Point point3=new Point(classroom.getIdClassroom(),homework1.getId(),user2.getId(),8);
        Point point4=new Point(classroom1.getIdClassroom(),homework2.getId(),user3.getId(),8);
        pointMap.put(point.getId(),point);
        pointMap.put(point1.getId(),point1);
        pointMap.put(point2.getId(),point2);
        pointMap.put(point3.getId(),point3);
        pointMap.put(point4.getId(),point4);
        Map<Integer,SubmitHomework> submitHomeworkMap=new HashMap<>();
        SubmitHomework submitHomework=new SubmitHomework(user1.getId(),"Bài làm bài 1 tiến 1",homework.getId());
        SubmitHomework submitHomework1=new SubmitHomework(user2.getId(),"Bài làm bài 1 tiến 2",homework.getId());
        SubmitHomework submitHomework2=new SubmitHomework(user1.getId(),"Bài làm bài 2 tiến 1",homework1.getId());
        SubmitHomework submitHomework3=new SubmitHomework(user2.getId(),"Bài làm bài 2 tiến 2",homework1.getId());
        submitHomeworkMap.put(submitHomework.getId(),submitHomework);
        submitHomeworkMap.put(submitHomework1.getId(),submitHomework1);
        submitHomeworkMap.put(submitHomework2.getId(),submitHomework2);
        submitHomeworkMap.put(submitHomework3.getId(),submitHomework3);
        Map<Integer,Schedule> scheduleMap=new HashMap<>();
        Schedule schedule=new Schedule(classroom.getIdClassroom(),1,1);
        Schedule schedule1=new Schedule(classroom1.getIdClassroom(),2,2);
        scheduleMap.put(schedule.getIdSchedule(),schedule);
        scheduleMap.put(schedule1.getIdSchedule(),schedule1);
        Map<Integer,Attendance> attendanceMap=new HashMap<>();
        Attendance attendance=new Attendance(classroom.getIdClassroom(),user1.getId(), LocalDate.of(2023,01,01));
        Attendance attendance1=new Attendance(classroom.getIdClassroom(),user2.getId(), LocalDate.of(2023,01,01));
        attendanceMap.put(attendance.getId(),attendance);
        attendanceMap.put(attendance1.getId(),attendance1);
        UserService userService=new UserService();
        Menu menu=new Menu();
        menu.optionMenu(scanner,users,userService,lessonMap,classroomMap,homeworkMap,pointMap,submitHomeworkMap,scheduleMap, attendanceMap);
    }
}
