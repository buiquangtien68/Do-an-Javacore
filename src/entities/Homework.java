package entities;

public class Homework {
    private static int autoId;
    private int id;
    private String name;
    private String content;
    private int idClassroom;

    public Homework( String name, String content, int idClassroom) {
        this.id = ++autoId;
        this.name = name;
        this.content = content;
        this.idClassroom = idClassroom;
    }

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        Homework.autoId = autoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdClassroom() {
        return idClassroom;
    }

    public void setIdClassroom(int idClassroom) {
        this.idClassroom = idClassroom;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", idClassroom=" + idClassroom +
                '}';
    }
}
