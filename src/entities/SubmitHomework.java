package entities;

public class SubmitHomework {
    private static int autoId;
    private int id;
    private int idStudent;
    private String content;
    private int idHomework;

    public SubmitHomework( int idStudent, String content, int idHomework) {
        this.id = ++autoId;
        this.idStudent = idStudent;
        this.content = content;
        this.idHomework = idHomework;
    }

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        SubmitHomework.autoId = autoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdHomework() {
        return idHomework;
    }

    public void setIdHomework(int idHomework) {
        this.idHomework = idHomework;
    }

    @Override
    public String toString() {
        return "SubmitHomework{" +
                "id=" + id +
                ", idStudent=" + idStudent +
                ", content='" + content + '\'' +
                ", idHomework=" + idHomework +
                '}';
    }
}
