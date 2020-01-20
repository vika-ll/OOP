package sample;
import  java.sql.Connection;
import  java.sql.DriverManager;
import java.sql.PreparedStatement;
import  java.sql.SQLException;
import  java.sql.ResultSet;


public class DataBaseHandler extends Configs{
Connection dbconnection;
public Connection getDbconnection()throws ClassNotFoundException,SQLException{
    String connectionString = "jdbc:mysql://localhost:3306/lizunova?" +
            "useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
Class.forName("com.mysql.cj.jdbc.Driver");

dbconnection=DriverManager.getConnection(connectionString,dbUser,dbPass);

return dbconnection;
}
public  void signUpUser(User user){

    String insert="INSERT INTO"+Const.USER_TABLE+"("+
            Const.USER_FIRSTNAME+","+Const.USER_LASTNAME+","+
            Const.USER_USERNAME+","+Const.USER_PASSWORD+","+
            Const.USER_LOCATION+","+Const.USER_GENDER+")"+
            "VALUES(?,?,?,?,?,?)";
    try {
    PreparedStatement prSt=getDbconnection().prepareStatement(insert);

    prSt.setString(1,user.getFirstaname());
    prSt.setString(2,user.getLastaname());
    prSt.setString(3,user.getUsername());
    prSt.setString(4,user.getPassword());
    prSt.setString(5,user.getLocation());
    prSt.setString(6,user.getGender());

        prSt.executeUpdate();
    }catch (SQLException e)
    {
e.printStackTrace();
    }
    catch (ClassNotFoundException e){}
}
public  ResultSet getUser(User user){
ResultSet resultSet=null;
String select="SELECT * FROM "+Const.USER_TABLE+" WHERE "+
        Const.USER_USERNAME+"=? AND "+Const.USER_PASSWORD+"=?";
    try {
        PreparedStatement prSt=getDbconnection().prepareStatement(select);
        prSt.setString(1,user.getFirstaname());
        prSt.setString(2,user.getLastaname());
       resultSet= prSt.executeQuery();

    }catch (SQLException e)
    {
        e.printStackTrace();
    }catch (ClassNotFoundException e){}
    return resultSet;
}

}
