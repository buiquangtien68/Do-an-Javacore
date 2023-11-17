package entities;

public class Point {
    private int id;
    private static int autoId;
    private int idClassroom;
    private int idHomework;
    private int idStudent;
    private double score;

    public Point() {
        this.id = ++autoId;
    }

    public Point(int idClassroom, int idHomework, int idStudent, double score) {
        this.id = ++autoId;
        this.idClassroom = idClassroom;
        this.idHomework = idHomework;
        this.idStudent = idStudent;
        this.score = score;
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
        Point.autoId = autoId;
    }

    public int getIdClassroom() {
        return idClassroom;
    }

    public void setIdClassroom(int idClassroom) {
        this.idClassroom = idClassroom;
    }

    public int getIdHomework() {
        return idHomework;
    }

    public void setIdHomework(int idHomework) {
        this.idHomework = idHomework;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", idClassroom=" + idClassroom +
                ", idHomework=" + idHomework +
                ", idStudent=" + idStudent +
                ", score=" + score +
                '}';
    }
}
