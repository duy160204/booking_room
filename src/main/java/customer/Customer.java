package customer;

import java.sql.ResultSet;
import java.util.ArrayList;

import objects.CustomerObject;

public interface Customer {
    boolean addCustomer(CustomerObject item);
    boolean editCustomer(CustomerObject item);
    boolean delCustomer(int id);

    ArrayList<ResultSet> getCustomers(String multiSelect);
    ArrayList<ResultSet> getCustomers(CustomerObject similar, int at, byte total);
    ResultSet getCustomer(int id);
    ResultSet getCustomer(String Customername, String Customerpass);
}