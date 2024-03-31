package dao;

import domain.board.Board;
import domain.board.Position;
import domain.piece.Piece;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDao {

    private static final String SERVER = "localhost:3306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final int MIN_FILE = 1;
    private static final int MAX_FILE = 8;
    private static final int MIN_RANK = 1;
    private static final int MAX_RANK = 8;

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("DB 연결 오류:" + e.getMessage());
        }
    }

    public void saveBoard(Board board) {
        for (int file = MIN_FILE; file <= MAX_FILE; file++) {
            savePiecesByFile(board, file);
        }
    }

    private void savePiecesByFile(Board board, int file) {
        for (int rank = MIN_RANK; rank <= MAX_RANK; rank++) {
            Position position = Position.of(file, rank);
            Piece piece = board.findPieceAt(position);
            savePiece(piece, position);
        }
    }

    private void savePiece(Piece piece, Position position) {
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
                board.put(Position.of(resultSet.getInt("board_file"), resultSet.getInt("board_rank")), PieceMapper.textToPiece(resultSet.getString("piece")));
            }
            return Board.replaceBoardWith(board);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeAll() {
        try (Connection connection = getConnection()) {
            String query = "delete from pieces";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
