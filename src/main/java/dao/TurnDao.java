package dao;

import domain.game.Turn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnDao {

    private static final String SERVER = "localhost:3306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("데이터베이스의 커넥션을 반환하지 못하였습니다.");
        }
    }

    public static void saveTurn(Turn turn) {
        removeAll();
        String query = "insert into turn values(?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, turn.getColor().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("턴을 저장하지 못하였습니다.");
        }
    }

    private static void removeAll() {
        String query = "delete from turn";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("저장된 턴을 삭제하지 못하였습니다.");
        }
    }

    public static Turn findTurn() {
        String query = "select * from turn";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return TurnMapper.textToTurn(resultSet.getString("current_turn"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("저장된 턴을 찾지 못하였습니다.");
        }
        return null;
    }
}
