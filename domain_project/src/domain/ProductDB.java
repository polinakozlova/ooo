package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author Polina Kozlova
 */
public class ProductDB {

	String url;
	Properties properties;

	public ProductDB() throws SQLException {
		this.properties = new Properties();
		this.url = "jdbc:postgresql://gegevensbanken.khleuven.be:51516/2TX32";
		properties.setProperty("user", "r0459898");
		properties.setProperty("password", "OOOPASS");
		properties.setProperty("ssl", "true");
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
	}

	public ProductRepository getRepo() {
		ProductRepository pr = new ProductRepository();
		try {
			Connection connection;
			Statement statement;
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, properties);
			statement = connection.createStatement();

			ResultSet result = statement.executeQuery("SELECT * FROM r0459898.\"Product\"");
			while (result.next()) {
				String id = result.getString("id");
				String description = result.getString("description");
				double price = Double.parseDouble(result.getString("price"));
				Product product = new Product(id, description, price);
				pr.add(product);
			}
			connection.close();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		} catch (ClassNotFoundException ex) {
			throw new DbException(ex.getMessage(), ex);
		}
		return pr;
	}

	public void add(Product product) throws ClassNotFoundException {
		if (product == null) {
			throw new DbException("Nothing to add.");
		}
		String sql = "INSERT INTO r0459898.\"Product\" (id, description, price)" + "VALUES ('" + product.getId()
				+ "', '" + product.getDescription() + "', " + product.getPrice() + ")";
		try {
			executeSQL(sql);
		} catch (SQLException e) {
			throw new DbException(e);
		}
	}

	public void update(Product product, String desc, double price) {
		if (product == null) {
			throw new DbException("No product found");
		}
		String sql = "UPDATE r0459898.\"Product\" SET description = '" + desc + "', price = " + price + " WHERE id= ' "
				+ product.getId() + "'";
		try {
			executeSQL(sql);
		} catch (SQLException e) {
			throw new DbException(e);
		}
	}

	public void delete(Product product) {
		if (product == null) {
			throw new DbException("No product found");
		}
		String sql = "DELETE FROM r0459898.\"Product\" WHERE id= '" + product.getId() + "'";
		try {
			executeSQL(sql);
		} catch (SQLException e) {
			throw new DbException(e);
		}
	}

	public Product getProductById(String id) {
		if (id == null) {
			throw new DbException("No id given");
		}
		String sql = "SELECT * FROM r0459898.\"Product\" WHERE id= '" + id + "'";
		ResultSet result;
		try {
			result = executeSQLSelect(sql);
			if(result.next()){
				String description = result.getString("description");
				double price = Double.parseDouble(result.getString("price"));
				Product product = new Product(id, description, price);
				return product;
			}
			else{
				throw new DbException("No product found");
			}
		} catch (SQLException e) {
		}
		return null;
	}

	public void executeSQL(String sql) throws SQLException {
		Connection connection;
		Statement statement;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		connection = DriverManager.getConnection(this.url, this.properties);
		statement = connection.createStatement();
		int result = statement.executeUpdate(sql);
		connection.close();
	}

	public ResultSet executeSQLSelect(String sql) throws SQLException {
		Connection connection;
		Statement statement;
		ResultSet result = null;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		connection = DriverManager.getConnection(this.url, this.properties);
		statement = connection.createStatement();
		boolean gelukt = statement.execute(sql);
		if(gelukt){
			result = statement.getResultSet();
		}
		else{
			throw new DbException("No product found");
		}
		connection.close();
		return result;
	}
}
