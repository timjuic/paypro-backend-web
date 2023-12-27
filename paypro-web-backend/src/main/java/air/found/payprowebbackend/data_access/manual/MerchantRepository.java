package air.found.payprowebbackend.data_access.manual;

import air.found.payprowebbackend.core.enums.StatusType;
import air.found.payprowebbackend.core.models.Merchant;
import air.found.payprowebbackend.core.models.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static air.found.payprowebbackend.data_access.manual.DBRepository.*;

public class MerchantRepository {
    private static final String SELECT_ALL_MERCHANTS =
            "SELECT * FROM merchants";

    public static List<Merchant> getAllMerchants() {
        List<Merchant> merchants = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MERCHANTS)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Merchant merchant = mapResultSetToMerchant(resultSet);
                    merchants.add(merchant);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw e;
        }

        return merchants;
    }

    private static Merchant mapResultSetToMerchant(ResultSet resultSet) throws SQLException {
        Merchant merchant = new Merchant();
        merchant.setId(resultSet.getInt("merchant_id"));
        merchant.setMerchantName(resultSet.getString("full_name"));
        Status status = new Status();
        status.setStatusId(resultSet.getInt("status_id"));
        status.setStatusName(StatusType.getNameById(resultSet.getInt("status_id")));
        merchant.setStatus(status);
        Merchant.Address address = new Merchant.Address();
        address.setStreetName(resultSet.getString("street_name"));
        address.setCity(resultSet.getString("city_name"));
        address.setPostalCode(resultSet.getInt("postal_code"));
        address.setStreetNumber(resultSet.getString("street_number"));
        merchant.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        merchant.setAddress(address);
        return merchant;
    }
}
