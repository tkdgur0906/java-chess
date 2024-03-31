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

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("데이터베이스의 커넥션을 반환하지 못하였습니다.");
        }
    }

    public static void saveBoard(Board board) {
        removeAll();
        for (int file = MIN_FILE; file <= MAX_FILE; file++) {
            savePiecesByFile(board, file);
        }
    }

    private static void removeAll() {
        String query = "delete from pieces";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("저장된 게임을 삭제하지 못하였습니다.");
        }
    }

    private static void savePiecesByFile(Board board, int file) {
        for (int rank = MIN_RANK; rank <= MAX_RANK; rank++) {
            Position position = Position.of(file, rank);
            Piece piece = board.findPieceAt(position);
            savePiece(piece, position);
        }
    }

    private static void savePiece(Piece piece, Position position) {
        String query = "insert into pieces values(?,?,?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, piece.asString());
            preparedStatement.setInt(2, position.file());
            preparedStatement.setInt(3, position.rank());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("게임을 저장하지 못하였습니다.");
        }
    }

    public static Board findAll() {
        Map<Position, Piece> board = new HashMap<>();
        String query = "select * from pieces order by board_file and board_rank";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                board.put(
                        Position.of(
                                resultSet.getInt("board_file"),
                                resultSet.getInt("board_rank")),
                        PieceMapper.textToPiece(
                                resultSet.getString("piece")
                        )
                );
            }
            return Board.replaceBoardWith(board);
        } catch (SQLException e) {
            throw new RuntimeException("저장된 게임을 찾지 못하였습니다.");
        }
    }
}
