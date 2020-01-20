package sample;

public class User {
    private String firstaname;
    private String lastaname;
    private String username;
    private String password;
    private String location;
    private String gender;
public  User(){}
    public User(String firstaname, String lastaname, String username, String password, String location, String gender) {
        this.firstaname = firstaname;
        this.lastaname = lastaname;
        this.username = username;
        this.password = password;
        this.location = location;
        this.gender = gender;
    }

    public String getFirstaname() {
        return firstaname;
    }

    public void setFirstaname(String firstaname) {
        this.firstaname = firstaname;
    }

    public String getLastaname() {
        return lastaname;
    }

    public void setLastaname(String lastaname) {
        this.lastaname = lastaname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
