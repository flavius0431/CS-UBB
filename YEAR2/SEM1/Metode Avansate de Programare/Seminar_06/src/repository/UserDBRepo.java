package repository;

import domain.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public  class UserDBRepo implements Repository<Long, User>{
    private String urlDB;
    private String usernameDB;
    private String passwordDB;

    public UserDBRepo(String urlDB, String usernameDB, String passwordDB) {
        this.urlDB = urlDB;
        this.usernameDB = usernameDB;
        this.passwordDB = passwordDB;
    }

    public String getUrlDB() {
        return urlDB;
    }

    public void setUrlDB(String urlDB) {
        this.urlDB = urlDB;
    }

    public String getUsernameDB() {
        return usernameDB;
    }

    public void setUsernameDB(String usernameDB) {
        this.usernameDB = usernameDB;
    }

    public String getPasswordDB() {
        return passwordDB;
    }

    public void setPasswordDB(String passwordDB) {
        this.passwordDB = passwordDB;
    }

    @Override
    public User findOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        Set<User> userSet = new HashSet<>();
        try(Connection connection = DriverManager.getConnection(urlDB,usernameDB,passwordDB)){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String lastName = resultSet.getString("lastName");
                String firstName = resultSet.getString("firstName");
                User user = new User(lastName,firstName);
                userSet.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userSet;
    }

    public User save( User user){
        String sql = "INSERT INTO User (lastName, firstName) VALUES (?,?)";
        try(Connection connection = DriverManager.getConnection(urlDB,usernameDB,passwordDB);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.executeUpdate();



        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User delete(Long aLong) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }


}
