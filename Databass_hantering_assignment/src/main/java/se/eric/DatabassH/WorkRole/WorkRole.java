package se.eric.DatabassH.WorkRole;

import java.sql.Date;

public class WorkRole {
    int roleID;
    String title;
    String description;
    Double salary;
    Date creationDate;

    public WorkRole(int roleID, String title, String description, Double salary, Date creationDate) {
        this.roleID = roleID;
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.creationDate = creationDate;
    }                                               //work role class to mach assignment specification

    public WorkRole(String title, String description, Double salary, Date creationDate) {
        this.title = title;
        this.description = description;
        this.salary = salary;
        this.creationDate = creationDate;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "-------------------------------------"+
                "\nWorkRole " +
                "\nroleID = " + roleID +
                "\ntitle = " + title  +
                "\nsalary = " + salary +
                "\ncreationDate = " + creationDate +
                "\ndescription = " + description +
                "\n-------------------------------------" ;
    }
}
