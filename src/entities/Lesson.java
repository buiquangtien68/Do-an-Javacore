package entities;

import java.util.Map;

public class Lesson {
    private static int autoId;
    private int idLesson;
    private String name;
    private String content;
    private int idClassroom;

    public Lesson( String name, String content, int idClassroom) {
        this.idLesson = ++autoId;
        this.name = name;
        this.content = content;
        this.idClassroom = idClassroom;
    }

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        Lesson.autoId = autoId;
    }

    public int getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
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
        return "Lesson{" +
                "idLesson=" + idLesson +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}