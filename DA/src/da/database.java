package da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;

public class database {
	public void createDBforfirsttime() {
		Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:plantmanagementsystem.db");

            statement = connection.createStatement();
            String createTableSQL = "CREATE TABLE umweltWerte (id INT, zeit TIME, ph DOUBLE, licht DOUBLE, feuchtigkeit DOUBLE)";
            statement.executeUpdate(createTableSQL);
            String createTable2SQL = "CREATE TABLE optimalWerte (id INT PRIMARY KEY, maxph DOUBLE, minph DOUBLE, maxlicht DOUBLE, minlicht DOUBLE, maxfeuchtigkeit DOUBLE, minfeuchtigkeit DOUBLE)";
            statement.executeUpdate(createTable2SQL);


            String insertDataSQL = "INSERT INTO optimalWerte (id, maxph, minph, maxlicht, minlicht, maxfeuchtigkeit, minfeuchtigkeit) VALUES (1, 7.5 , 6.5, 80, 50, 50, 40)";
            statement.executeUpdate(insertDataSQL);
            System.out.println("Veri eklendi.");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC sürücüsü bulunamadı.");
        } catch (SQLException e) {
            System.out.println("Veritabanına erişirken hata oluştu: " + e.getMessage());
        } finally {
            try {
                // Bağlantıyı ve diğer kaynakları kapatma
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println("Kaynakları kapatırken hata oluştu: " + e.getMessage());
            }
        }
	}
	public void writeData(String s) { //id-ph-light-water parse and add to db
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String[] parameters = s.split("-");
		
		
        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:plantmanagementsystem.db");
            

            String insertDataSQL = "INSERT INTO umweltWerte (id , zeit , ph , licht, feuchtigkeit) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertDataSQL);
            preparedStatement.setInt(1, Integer.valueOf(parameters[0]));
            
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            preparedStatement.setTimestamp(2, currentTime);
            
            preparedStatement.setDouble(3, Double.valueOf(parameters[1]));
            preparedStatement.setDouble(4, Double.valueOf(parameters[2]));
            preparedStatement.setDouble(5, Double.valueOf(parameters[3]));
            
            
            preparedStatement.executeUpdate();
            
            System.out.println("Veri eklendi.");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC sürücüsü bulunamadı.");
        } catch (SQLException e) {
            System.out.println("Veritabanına erişirken hata oluştu: " + e.getMessage());
        } finally {
            try {
                // Bağlantıyı ve diğer kaynakları kapatma
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println("Kaynakları kapatırken hata oluştu: " + e.getMessage());
            }
        }
	}
	
	public void readLastData(plant p) {
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:plantmanagementsystem.db");
            System.out.println("Veritabanına bağlandı.");

            // En son kaydedilen satırı okuma
            statement = connection.createStatement();
            String selectQuery = String.format("SELECT * FROM umweltWerte WHERE id == %d ORDER BY zeit DESC LIMIT 1", p.id);
            resultSet = statement.executeQuery(selectQuery);

            // Sonucu işleme
            if (resultSet.next()) {
                double feuchtigkeit = resultSet.getDouble("feuchtigkeit");
                double ph = resultSet.getDouble("ph");
                double licht = resultSet.getDouble("licht");

                p.pH = ph;
                p.feuchtigkeit = feuchtigkeit;
                p.licht = licht;
                
            } else {
                System.out.println("Tabloda kayıt bulunamadı.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC sürücüsü bulunamadı.");
        } catch (SQLException e) {
            System.out.println("Veritabanına erişirken hata oluştu: " + e.getMessage());
        } finally {
            try {
                // Kaynakları kapatma
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println("Kaynakları kapatırken hata oluştu: " + e.getMessage());
            }
        }
	}
	public void readlAllData(int id) {
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:plantmanagementsystem.db");
            System.out.println("Veritabanına bağlandı.");

            // Veri okuma
            statement = connection.createStatement();
            String selectQuery = String.format("SELECT * FROM umweltWerte WHERE id == %d",id);
            resultSet = statement.executeQuery(selectQuery);

            // Sonuçları işleme
            while (resultSet.next()) {
                int idx = resultSet.getInt("id");
                Timestamp name = resultSet.getTimestamp("zeit");
                double ph = resultSet.getDouble("ph");
                double licht = resultSet.getDouble("licht");
                double feuchtig = resultSet.getDouble("feuchtigkeit");
                
                //BU KISIM GRAFİKLER İÇİN YAZILACAK
            }
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC sürücüsü bulunamadı.");
        } catch (SQLException e) {
            System.out.println("Veritabanına erişirken hata oluştu: " + e.getMessage());
        } finally {
            try {
                // Kaynakları kapatma
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.out.println("Kaynakları kapatırken hata oluştu: " + e.getMessage());
            }
        }
	}
	public void writeOptimalPh() {}
	public void writeOptimalLicht(){}
}
