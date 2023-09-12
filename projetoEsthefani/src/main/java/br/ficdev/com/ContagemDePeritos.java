package br.ficdev.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestMapping;

public class ContagemDePeritos {
	private static final String jdbcURL = "jdbc:postgresql://localhost:5433/siteKpop";
    private static final String username = "postgres";
    private static final String password = "22132317";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(jdbcURL, username, password);
    }
    
    
	public static int countPeritos() {
        try (Connection connection = connect()) {
            String sql = "SELECT COUNT(*) AS total_peritos FROM peritos";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("total_peritos");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Retornar 0 em caso de erro ou nenhum resultado
    }
}
