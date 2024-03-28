package dao;

import domain.board.Board;
import domain.board.Position;
import domain.piece.Piece;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class BoardDao {

    private static final String SERVER = "localhost:3306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void savePiece(Piece piece, Position position) {
        try (Connection connection = getConnection()) {
            String query = "insert into pieces values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, piece.asString());
            preparedStatement.setInt(2, position.file());
            preparedStatement.setInt(3, position.rank());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Board findAll() {
        try (Connection connection = getConnection()) {
            Map<Position, Piece> board = new HashMap<>();
            String query = "select * from pieces order by board_file and board_rank";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                board.put(
                        Position.of(resultSet.getInt("board_file"), resultSet.getInt("board_rank")),
                        PieceMapper.textToPiece(resultSet.getString("piece"))
                );
            }
            return Board.replaceBoardWith(board);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
