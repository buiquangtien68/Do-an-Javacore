package entities;

public class Schedule {
    private static int autoId;
    private int idSchedule;
    private int idClassroom;
    private int timeFrame;
    //1: sáng - 2: chiều
    private int timeSchedule;
    // 1: thứ 2,4,6 - 2: thứ 3,5,7


    public Schedule( int idClassroom, int timeFrame, int timeSchedule) {
        this.idSchedule = ++autoId;
        this.idClassroom = idClassroom;
        this.timeFrame = timeFrame;
        this.timeSchedule = timeSchedule;
    }

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        Schedule.autoId = autoId;
    }

    public int getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(int idSchedule) {
        this.idSchedule = idSchedule;
    }

    public int getIdClassroom() {
        return idClassroom;
    }

    public void setIdClassroom(int idClassroom) {
        this.idClassroom = idClassroom;
    }

    public int getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }

    public int getTimeSchedule() {
        return timeSchedule;
    }

    public void setTimeSchedule(int timeSchedule) {
        this.timeSchedule = timeSchedule;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "idSchedule=" + idSchedule +
                ", idClassroom=" + idClassroom +
                ", timeFrame=" + timeFrame +
                ", timeSchedule=" + timeSchedule +
                '}';
    }
}
