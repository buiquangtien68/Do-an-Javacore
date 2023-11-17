package entities;

import java.time.LocalDate;

public class Attendance {
    private int id;
    private static int autoId;
    private int idClassroom;
    private int idStudent;
    private LocalDate date;

    public Attendance( int idClassroom, int idStudent, LocalDate date) {
        this.id = ++autoId;
        this.idClassroom = idClassroom;
        this.idStudent = idStudent;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        Attendance.autoId = autoId;
    }

    public int getIdClassroom() {
        return idClassroom;
    }

    public void setIdClassroom(int idClassroom) {
        this.idClassroom = idClassroom;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", idClassroom=" + idClassroom +
                ", idStudent=" + idStudent +
                ", date='" + date + '\'' +
                '}';
    }
}
