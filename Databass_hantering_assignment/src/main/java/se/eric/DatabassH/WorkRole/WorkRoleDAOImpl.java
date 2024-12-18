package se.eric.DatabassH.WorkRole;

import se.eric.DatabassH.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkRoleDAOImpl implements WorkRoleDAO {


    @Override
    public void insertWorkRole(WorkRole workRole) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql= "INSERT INTO WORK_ROLE (TITLE,DESKRIPTION,SALARY,CREATION_DATE)"+
                "VALUES(?,?,?,?)";
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1,workRole.getTitle());
            stmt.setString(2,workRole.getDescription());
            stmt.setDouble(3,workRole.getSalary());
            stmt.setDate(4,workRole.getCreationDate());

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
    public List<WorkRole> getWorkRols() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<WorkRole> workRoles = new ArrayList<WorkRole>();
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM WORK_ROLE");

            while(rs.next()){
                workRoles.add(buildWorkRole(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally{
            JDBCUtil.closeConnection(conn);
            JDBCUtil.closeStatement(stmt);
            JDBCUtil.closeResultSet(rs);
            if (workRoles.isEmpty()){
                System.out.println("No Work roles found");
            }
            return workRoles;
        }
    }

    @Override
    public WorkRole getWorkRole(Integer roleID) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        WorkRole workRole = null;

        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM WORK_ROLE WHERE ROLE_ID=?");
            stmt.setInt(1,roleID);
            rs = stmt.executeQuery();
            if(rs.next()){
                workRole = buildWorkRole(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }finally{
            JDBCUtil.closeConnection(conn);
            JDBCUtil.closeStatement(stmt);
            JDBCUtil.closeResultSet(rs);
        }
        return workRole;
    }

    @Override
    public void deleteWorkRole(Integer roleID) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement("DELETE FROM WORK_ROLE WHERE ROLE_ID=?" );
            stmt.setInt(1, roleID);
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
    public void updateWorkRole(WorkRole workRole, int roleID) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement("UPDATE WORK_ROLE SET TITLE=?,DESKRIPTION=?,SALARY=?,CREATION_DATE=? WHERE ROLE_ID=?");
            stmt.setString(1,workRole.getTitle());
            stmt.setString(2,workRole.getDescription());
            stmt.setDouble(3,workRole.getSalary());
            stmt.setDate(4,workRole.getCreationDate());
            stmt.setInt(5,roleID);
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

    private WorkRole buildWorkRole(ResultSet rs) throws SQLException {
        int roleId = rs.getInt("ROLE_ID");
        String title = rs.getString("TITLE");
        String deskription = rs.getString("DESKRIPTION");
        double salary = rs.getDouble("SALARY");
        Date creationDate = rs.getDate("CREATION_DATE");
        return new WorkRole(roleId,title,deskription,salary,creationDate);
    }
}
