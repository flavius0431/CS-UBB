package com.example.practic.repository;

import com.example.practic.domain.Entity;
import com.example.practic.domain.validators.Validator;



public abstract class AbstractDBRepository<ID, E extends Entity<ID>> {
    protected String url;
    protected String username;
    protected String password;
    protected Validator<E> validator;

    public AbstractDBRepository(String url, String username, String password, Validator<E> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;

    }

    public abstract E findOne(ID id) ;
    /*Utilizator user = null;

    String sql="SELECT * from users WHERE id = (?)";
        try (
    Connection connection= DriverManager.getConnection(url,username,password)){
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setLong(1, aLong);

        ResultSet resultSet = preparedStatement.executeQuery();

        if(!resultSet.next()) {
            return null;
        }

        Long idUser = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");

        user = new Utilizator(firstName, lastName,email,password);
        user.setId(idUser);

    }catch (
    SQLException e)
    {
        e.printStackTrace();
    }
        return user;*/

    public abstract Iterable<E> findAll() ;
    /*Set<Utilizator> users = new HashSet<>();
        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery())
        {
            while(resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                Utilizator u = new Utilizator(firstName, lastName,email,password);
                u.setId(id);
                users.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e){
            e.getMessage();
        }

        return users;*/

    public abstract E save(E entity) ;
    /* String sql = "INSERT INTO users(first_name,last_name,email,password) VALUES(?,?,?,?)";
        try(Connection connection= DriverManager.getConnection(url,username,password)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,entity.getFirstName());
            preparedStatement.setString(2,entity.getLastName());
            preparedStatement.setString(3,entity.getEmail());
            preparedStatement.setString(4,entity.getPassword());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;*/


    public abstract E delete(ID id) ;
    /*Utilizator user = this.findOne(aLong);
        if(user == null)
            return null;

        String sql = "DELETE FROM users WHERE id = (?)";
        try (Connection connection= DriverManager.getConnection(url,username,password)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, aLong);
            preparedStatement.executeUpdate();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;*/


    public abstract E update(E entity);
    /*Utilizator user = this.findOne(entity.getId());
        if(user == null)
            return entity;

        String sql = "UPDATE users SET first_name = (?), last_name = (?)  WHERE id = (?)";
        try (Connection connection= DriverManager.getConnection(url,username,password)){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }


        return null;*/
}
