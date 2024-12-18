package se.eric.DatabassH.Employee;

import se.eric.DatabassH.JDBCUtil;
import se.eric.DatabassH.WorkRole.WorkRole;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDOAImpl implements EmployeeDOA{
    @Override
    public void insertEmployee(Employee employee) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql= "INSERT INTO EMPLOYEE (NAME,EMAIL,PASSWORD,ROLE_ID)"+
                "VALUES(?,?,?,?)";
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,employee.getName());
            stmt.setString(2,employee.getEmail());
            stmt.setString(3,employee.getPassword());
            stmt.setInt(4,employee.getWorkRole().getRoleID());

            stmt.executeUpdate();
            JDBCUtil.commit(conn);
        }
        catch(SQLException e){
            JDBCUtil.rollback(conn);
            throw e;
        }
        finally {
            JDBCUtil.closeConnection(conn);
            JDBCUtil.closeStatement(stmt);
        }
    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Employee> employee = new ArrayList<Employee>();
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM EMPLOYEE e LEFT JOIN WORK_ROLE W ON e.ROLE_ID = W.ROLE_ID");

            while(rs.next()){
                employee.add(buildEmployee(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally{
            JDBCUtil.closeConnection(conn);
            JDBCUtil.closeStatement(stmt);
            JDBCUtil.closeResultSet(rs);
            if (employee.isEmpty()){
                System.out.println("No Employee found");
            }
            return employee;
        }
    }

    @Override
    public Employee getEmployee(Integer employeeID) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Employee employee = null;

        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM EMPLOYEE e LEFT JOIN WORK_ROLE w ON e.ROLE_ID=w.ROLE_ID WHERE ROLE_ID=?");
            stmt.setInt(1,employeeID);
            rs = stmt.executeQuery();
            if(rs.next()){
                employee = buildEmployee(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally{
            JDBCUtil.closeConnection(conn);
            JDBCUtil.closeStatement(stmt);
            JDBCUtil.closeResultSet(rs);
        }
        return employee;
    }

    @Override
    public void deleteEmployee(Integer employeeID) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement("DELETE FROM EMPLOYEE WHERE EMPLOYEE_ID=?" );
            stmt.setInt(1, employeeID);
            stmt.executeUpdate();
            JDBCUtil.commit(conn);
        }catch (SQLException e){
            JDBCUtil.rollback(conn);
            e.printStackTrace();
            throw e;
        }
        finally{
            JDBCUtil.closeConnection(conn);
            JDBCUtil.closeStatement(stmt);
        }
    }

    @Override
    public void updateEmployee(Employee employee, int employeeID) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement("UPDATE EMPLOYEE SET NAME=?, EMAIL=?, PASSWORD=?,ROLE_ID=? WHERE ROLE_ID=?");
            stmt.setString(1,employee.getName());
            stmt.setString(2,employee.getEmail());
            stmt.setString(3,employee.getPassword());
            stmt.setInt(4,employee.getWorkRole().getRoleID());
            stmt.setInt(5,employeeID);
            stmt.executeUpdate();
            JDBCUtil.commit(conn);
        }catch (SQLException e){
            JDBCUtil.rollback(conn);
            e.printStackTrace();
            throw e;
        }finally{
            JDBCUtil.closeConnection(conn);
            JDBCUtil.closeStatement(stmt);
        }
    }

    private Employee buildEmployee(ResultSet rs) throws SQLException {
        int employeeID = rs.getInt("EMPLOYEE_ID");
        String name = rs.getString("NAME");
        String email = rs.getString("EMAIL");
        String password = rs.getString("PASSWORD");

        int roleId = rs.getInt("ROLE_ID");
        String title = rs.getString("TITLE");
        String deskription = rs.getString("DESKRIPTION");
        double salary = rs.getDouble("SALARY");
        Date creationDate = rs.getDate("CREATION_DATE");
        WorkRole workRole= new WorkRole(roleId,title,deskription,salary,creationDate);
        return new Employee(employeeID,name,email,password,workRole);
    }
}
