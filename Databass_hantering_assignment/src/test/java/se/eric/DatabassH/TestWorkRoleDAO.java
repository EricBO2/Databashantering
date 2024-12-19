package se.eric.DatabassH;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.eric.DatabassH.WorkRole.WorkRole;
import se.eric.DatabassH.WorkRole.WorkRoleDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestWorkRoleDAO {

    WorkRoleDAOImpl workRoleDAO;

    @BeforeEach                                 //sets upp a work role DAO to prepare for the test
    public void setUp() throws SQLException {
        workRoleDAO = new WorkRoleDAOImpl();
    }

    @Test
    public void TestInsertWorkRole(){           //test if insert work role works
        WorkRole testWorkRole = new WorkRole("Koder","A koder builds programs using different programing language lick jav, c++, c# ect.",
                30000.4,java.sql.Date.valueOf("2023-12-09"));
        List<WorkRole> testWorkRoles = new ArrayList<WorkRole>();
        try{
            workRoleDAO.insertWorkRole(testWorkRole);

            testWorkRoles = workRoleDAO.getWorkRols();

            for(WorkRole workRole : testWorkRoles){
                System.out.println(workRole.getTitle());
            }

            Assertions.assertTrue(testWorkRoles.size() > 0);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @AfterEach                                  //cleans upp the database after the test
    public void clean(){
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            stmt.execute("DROP TABLE IF EXISTS WORK_ROLE");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.closeConnection(conn);
            JDBCUtil.closeStatement(stmt);
        }
    }
}
