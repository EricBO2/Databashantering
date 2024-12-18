package se.eric.DatabassH.WorkRole;

import java.sql.SQLException;
import java.util.List;

public interface WorkRoleDAO {
    public void insertWorkRole(WorkRole person) throws SQLException;
    public List<WorkRole> getWorkRols() throws SQLException;
    public WorkRole getWorkRole(Integer roleID) throws SQLException;
    public void deleteWorkRole(Integer roleID) throws SQLException;
    public void updateWorkRole(WorkRole person, int ID) throws SQLException;
}
