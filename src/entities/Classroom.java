package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class Classroom {
    private static int autoId;
    private int idClassroom;
    private String name;
    private int teacherId;
    private ArrayList<Integer> students;
    private LocalDate startDate;
    private LocalDate finishDate;

    public Classroom() {
        this.idClassroom = ++autoId;
    }

    public Classroom( String name, int teacherId, ArrayList<Integer> students, LocalDate startDate, LocalDate finishDate) {
        this.idClassroom = ++autoId;
        this.name = name;
        this.teacherId = teacherId;
        this.students = students;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }
    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        Classroom.autoId = autoId;
    }

    public int getIdClassroom() {
        return idClassroom;
    }

    public void setIdClassroom(int idClassroom) {
        this.idClassroom = idClassroom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public ArrayList<Integer> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Integer> students) {
        this.students = students;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "idClassroom=" + idClassroom +
                ", name='" + name + '\'' +
                ", teacherId=" + teacherId +
                ", students=" + students +
                '}';
    }
}
