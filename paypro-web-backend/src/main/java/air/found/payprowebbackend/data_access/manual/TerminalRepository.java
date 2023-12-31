package air.found.payprowebbackend.data_access.manual;

import air.found.payprowebbackend.core.enums.StatusType;
import air.found.payprowebbackend.core.models.Merchant;
import air.found.payprowebbackend.core.models.Status;
import air.found.payprowebbackend.core.models.Terminal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static air.found.payprowebbackend.data_access.manual.DBRepository.*;

public class TerminalRepository {
    private static final String SELECT_ALL_TERMINALS = "SELECT * FROM terminals";
    private static final String SELECT_ALL_TERMINALS_FOR_MERCHANT = "SELECT * FROM terminals WHERE merchant_id = ?";
    private static final String SELECT_TERMINAL = "SELECT * FROM terminals WHERE terminal_id = ?";

    public static List<Terminal> getAllTerminals() {
        List<Terminal> terminals = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TERMINALS)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Terminal terminal = mapResultSetToTerminal(resultSet);
                    terminals.add(terminal);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw e;
        }

        return terminals;
    }

    public static List<Terminal> getAllTerminalsForMerchant(Integer merchantId) {
        List<Terminal> terminals = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TERMINALS_FOR_MERCHANT)) {

            preparedStatement.setInt(1, merchantId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Terminal terminal = mapResultSetToTerminal(resultSet);
                    terminals.add(terminal);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw e;
        }

        return terminals;
    }

    public static Terminal getTerminal(int tid) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TERMINAL)) {

            preparedStatement.setInt(1, tid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToTerminal(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw e;
        }

        return null;
    }

    private static Terminal mapResultSetToTerminal(ResultSet resultSet) throws SQLException {
        Terminal terminal = new Terminal();
        terminal.setTerminalId(resultSet.getInt("terminal_id"));
        terminal.setTerminalKey(resultSet.getString("terminal_key"));
        terminal.setType(resultSet.getInt("type"));
        terminal.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());

        StatusType statusType = StatusType.valueOfId(resultSet.getInt("status_id"));
        Status status = new Status();
        status.setStatusName(statusType.getName());
        status.setStatusId(status.getStatusId());
        terminal.setStatus(status);

        return terminal;
    }
}
