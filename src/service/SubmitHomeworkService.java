package service;

import entities.SubmitHomework;
import entities.User;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class SubmitHomeworkService {
    public void showHomeworkSubmit(ArrayList<User> users, Map<Integer, SubmitHomework> submitHomeworkMap, int idHomework){
        for (Map.Entry<Integer,SubmitHomework> entry:submitHomeworkMap.entrySet()){
            if (entry.getValue().getIdHomework()==idHomework){
                for (User user: users){
                    if (user.getId()==entry.getValue().getIdStudent()) {
                        System.out.println("Bài tập về nhà của "+user.getName()+":");
                        System.out.println(entry.getValue().getContent());
                    }
                }
            }
        }
    }
    public void insertSubmitHomework(Scanner scanner, Map<Integer, SubmitHomework> submitHomeworkMap, int idHomework, int idStudent){
        for (Map.Entry<Integer,SubmitHomework> entry:submitHomeworkMap.entrySet()){
            if (entry.getValue().getIdHomework()==idHomework && entry.getValue().getIdStudent()==idStudent) {
                System.out.println("Mời bạn nộp bài:");
                entry.getValue().setContent(scanner.nextLine());
                System.out.println("Đã nộp thành công");
            }
        }
    }
}

