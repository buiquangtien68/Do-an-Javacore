package entities;

import java.util.UUID;

public class User {
    private int id;
    private static int autoId;
    private String username;
    private String email;
    private String password;
    protected String name;
    protected int age;
    protected String address;
    private int role; //1:giáo viên 2:học sinh


    public User() {
        this.id = ++autoId;
    }

    public User(String username, String email, String password) {
        this.id = ++autoId;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, String name, int age, String address, int role) {
        this.id = ++autoId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.address = address;
        this.role = role;
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
        User.autoId = autoId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
