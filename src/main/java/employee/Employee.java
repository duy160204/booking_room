package employee;

import java.sql.ResultSet;
import java.util.ArrayList;

import objects.EmployeeObject;

public interface Employee {
    boolean addEmployee(EmployeeObject item);
    boolean editEmployee(EmployeeObject item);
    boolean delEmployee(int id);

    ArrayList<ResultSet> getEmployees(String multiSelect);
    ArrayList<ResultSet> getEmployees(EmployeeObject similar, int at, byte total);
    ResultSet getEmployee(int id);
    ResultSet getEmployee(String Employeename, String Employeepass);
}