package domain.promocode;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import domain.DbException;
import domain.product.Product;
import domain.product.ProductDB;
/**
 * @author Polina Kozlova
 */
public class PromocodeDB {
	private String url;
	private Properties properties;
	private ProductDB productDB;

	public PromocodeDB(ProductDB productDB) throws SQLException {
		this.properties = new Properties();
		this.url = "jdbc:postgresql://gegevensbanken.khleuven.be:51516/2TX32";
		properties.setProperty("user", "r0372415");
		//properties.setProperty("password", System.getenv("OOOPASS"));
		properties.setProperty("password", "Nichaisubercool1");;
		properties.setProperty("ssl", "true");
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
		this.productDB = productDB;
	}

	public PromocodeRepository getRepo() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		PromocodeRepository pcr = new PromocodeRepository();

		try {
			Connection connection;
			Statement statement;
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, properties);
			statement = connection.createStatement();

			ResultSet result = statement.executeQuery("SELECT * FROM r0459898.\"PromoCode\"");
			while (result.next()) {
				int id = Integer.parseInt(result.getString("id"));
				PromocodeType type = PromocodeType.valueOf(result.getString("type"));
				double amount = Double.parseDouble(result.getString("amount"));
				Product product = productDB.getProductById(result.getString("productId"));
				double percentage = Double.parseDouble(result.getString("percentage"));
				double saleAmountNeeded = Double.parseDouble(result.getString("saleAmountNeeded"));	
				Promocode promocode = null;
				try {
					promocode = PromocodeFactory.makePromocode(type, id, amount, product, percentage, saleAmountNeeded);
				} catch (Exception e) {
				}
				if(promocode != null) {
					pcr.add(promocode);
				}
			}
			connection.close();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		} catch (ClassNotFoundException ex) {
			throw new DbException(ex.getMessage(), ex);
		}
		return pcr;
	}

	public void delete(Promocode promoCode) {
		if (promoCode == null) {
			throw new DbException("No promoCode found");
		}
		String sql = "DELETE FROM r0459898.\"PromoCode\" WHERE \"id\" LIKE '" + promoCode.getId() + "'";

		try {
			executeSQL(sql);
		} catch (SQLException e) {
			throw new DbException(e);
		}
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
		@SuppressWarnings("unused")
		int result = statement.executeUpdate(sql);
		connection.close();
	}

}
