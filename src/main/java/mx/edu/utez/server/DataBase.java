package mx.edu.utez.server;

import mx.edu.utez.database.ConnectionMySQL;
import mx.edu.utez.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    Connection con;
    PreparedStatement pstm;
    Statement statement;
    ResultSet rs;

    public List<Employee> readAll (){
        List<Employee> listEmployee = new ArrayList<>();
        try{
            con = ConnectionMySQL.getConnection();
            String query ="SELECT `employees`.`employeeNumber`, `employees`.`firstName`\n" +
                    "FROM `employees`;";
            statement = con.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()){
                Employee employee = new Employee();
                employee.setEmployeeNumber(rs.getInt("employeeNumber"));
                employee.setFirstName(rs.getString("firstName"));
                listEmployee.add(employee);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return listEmployee;
    }
    public Employee readOne (int number){
        Employee employee = new Employee();
        try{
            con = ConnectionMySQL.getConnection();
            String query ="SELECT `employees`.`employeeNumber`, `employees`.`firstName`\n" +
                    "FROM `employees` WHERE employeeNumber = ?";
            pstm = con.prepareStatement(query);
            pstm.setInt(1,number);
            rs = pstm.executeQuery();

            while (rs.next()){

                employee.setEmployeeNumber(rs.getInt("employeeNumber"));
                employee.setFirstName(rs.getString("firstName"));

            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
       return employee;
    }

    public boolean  addEmployee (Employee employee ){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            String query = "INSERT INTO employees (employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) VALUES (?,?,?,?,?,?,?,?) ";
            pstm = con.prepareStatement(query);
            pstm.setObject(1,employee);
            pstm.setObject(2,employee);
            pstm.setObject(3,employee);
            pstm.setObject(4,employee);
            pstm.setObject(5,employee);
            pstm.setObject(6,employee);
            pstm.setObject(7,employee);
            pstm.setObject(8,employee);
            flag = pstm.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return flag;
    }

    public boolean updateEmployee ( String lastname, String firstName, String extension, String email,
                                 String officeCode, int reports, String jobTitle, int empNumber ){
        Employee employee = new Employee();
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            String query = "UPDATE `employees` SET lastName = ? , firstName = ?, extension = ?, email = ?, officeCode = ?, reportsTo = ?, jobTitle = ? WHERE employeeNumber = ?  ";
            pstm = con.prepareStatement(query);

            pstm.setString(1,lastname);
            pstm.setString(2,firstName );
            pstm.setString(3,extension);
            pstm.setString(4,email);
            pstm.setString(5,officeCode );
            pstm.setInt(6,reports );
            pstm.setString(7,jobTitle);
            pstm.setInt(8,empNumber);
            flag = pstm.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return flag;

    }

    public boolean delete (int id){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            String query = "DELETE FROM employees WHERE  employeeNumber = ? ";
            pstm = con.prepareStatement(query);
            pstm.setInt(1,id);
            flag= pstm.executeUpdate() == 1;
        }catch (SQLException ex){
        ex.printStackTrace();
        }finally {
        closeConnection();
        }
        return flag;
    }




    public static void main (String []args){
        DataBase db = new DataBase();
        List<Employee> list = db.readAll();
        for (Employee employee : list){
            System.out.println(employee.getEmployeeNumber());
            System.out.println(employee.getFirstName());

        }
    }

    public void closeConnection(){
        try{
            if (con != null){
                con.close();
            }
            if (pstm != null){
                pstm.close();
            }
            if (rs != null){
                rs.close();
            }
            if (statement != null){
                rs.close();
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
