package da.da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.time.format.DateTimeFormatter;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;


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
    public void readAllData(int id, BarChart<String, Number> phChart, BarChart<String, Number> lichtChart, BarChart<String, Number> feuchtigkeitChart) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:plantmanagementsystem.db");
            System.out.println("Veritabanına bağlandı.");

            // Veri okuma
            statement = connection.createStatement();
            String selectQuery = String.format("SELECT * FROM umweltWerte WHERE id == %d", id);
            resultSet = statement.executeQuery(selectQuery);

            // Verileri grafiklere ekleme
            XYChart.Series<String, Number> phSeries = new XYChart.Series<>();
            XYChart.Series<String, Number> lichtSeries = new XYChart.Series<>();
            XYChart.Series<String, Number> feuchtigkeitSeries = new XYChart.Series<>();

            while (resultSet.next()) {
                int idx = resultSet.getInt("id");
                Timestamp timestamp = resultSet.getTimestamp("zeit");
                double ph = resultSet.getDouble("ph");
                double licht = resultSet.getDouble("licht");
                double feuchtigkeit = resultSet.getDouble("feuchtigkeit");

                // Timestamp'i string formatına dönüştürme
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                String formattedDateTime = localDateTime.format(DateTimeFormatter.ofPattern("MM-dd HH:mm:ss"));

                // Verileri serilere ekleme
                phSeries.getData().add(new XYChart.Data<>(formattedDateTime, ph));
                lichtSeries.getData().add(new XYChart.Data<>(formattedDateTime, licht));
                feuchtigkeitSeries.getData().add(new XYChart.Data<>(formattedDateTime, feuchtigkeit));
            }

            // Grafiklere serileri ekleme
            phChart.getData().add(phSeries);
            lichtChart.getData().add(lichtSeries);
            feuchtigkeitChart.getData().add(feuchtigkeitSeries);
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

    public void readOptimalData(plant p) { //optimalWerte (id INT PRIMARY KEY, maxph DOUBLE, minph DOUBLE, maxlicht DOUBLE, minlicht DOUBLE, maxfeuchtigkeit DOUBLE, minfeuchtigkeit DOUBLE)
		Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:plantmanagementsystem.db");
            System.out.println("Veritabanına bağlandı.");

            // En son kaydedilen satırı okuma
            statement = connection.createStatement();
            String selectQuery = String.format("SELECT * FROM optimalWerte WHERE id == %d", p.id);
            resultSet = statement.executeQuery(selectQuery);

            // Sonucu işleme
            if (resultSet.next()) {
                double maxph = resultSet.getDouble("maxph");
                double minph = resultSet.getDouble("minph");
                double maxlicht = resultSet.getDouble("maxlicht");
                double minlicht = resultSet.getDouble("minlicht");
                double maxfeuchtigkeit = resultSet.getDouble("maxfeuchtigkeit");
                double minfeuchtigkeit = resultSet.getDouble("minfeuchtigkeit");

                p.opphmax = maxph;
                p.opphmin = minph;
                p.opfeuchtigkeitmax = maxfeuchtigkeit;
                p.opfeuchtigkeitmin = minfeuchtigkeit;
                p.oplightmax = maxlicht;
                p.oplightmin = minlicht;
                
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
	public void writeOptimal(int id, double maxphnew, double minphnew, double maxlichtnew, double minlichtnew, double maxfeuchtigkeitnew, double minfeuchtigkeitnew) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:plantmanagementsystem.db");
            

            String insertDataSQL = "UPDATE optimalWerte SET maxph = ?, minph = ?, maxlicht = ?, minlicht = ?, maxfeuchtigkeit = ?, minfeuchtigkeit = ? WHERE id == ?";
            preparedStatement = connection.prepareStatement(insertDataSQL);
            preparedStatement.setDouble(1, maxphnew);
            preparedStatement.setDouble(2, minphnew);
            preparedStatement.setDouble(3, maxlichtnew);
            preparedStatement.setDouble(4, minlichtnew);
            preparedStatement.setDouble(5, maxfeuchtigkeitnew);
            preparedStatement.setDouble(6, minfeuchtigkeitnew);
            preparedStatement.setInt(7, id);
            
            
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
    public int getNumOfPlants() {
        int maxId = -1;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:plantmanagementsystem.db");
            System.out.println("Veritabanına bağlandı.");

            // En son kaydedilen satırı okuma
            statement = connection.createStatement();
            String selectQuery = String.format("SELECT id FROM umweltWerte ORDER BY id DESC LIMIT 1");
            resultSet = statement.executeQuery(selectQuery);

            // Sonucu işleme
            if (resultSet.next()) {
                maxId = resultSet.getInt("id");

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
        return maxId;
    }}
