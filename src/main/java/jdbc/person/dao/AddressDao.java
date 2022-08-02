package jdbc.person.dao;

import jdbc.person.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDao {


    public List<Address> getAllAddresses() {
        List<Address> addressList = new ArrayList();
        try (Connection connection = ConnectionDB.getInstance().getConnection()) {

            PreparedStatement statement = connection.prepareStatement("select*from Address");
            ResultSet resultSet = statement.executeQuery();

            Address address = new Address();

            while (resultSet.next()) {
                address.setId(resultSet.getInt("id"));
                address.setStreet(resultSet.getString("street"));
                address.setCity(resultSet.getString("city"));
                address.setCountry(resultSet.getString("country"));

                addressList.add(address);
            }

        } catch (SQLException sqlException) {
            System.out.println("address select error: " + sqlException.getMessage());
        }
        return addressList;
    }
}
