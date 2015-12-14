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
import domain.product.ProductDB;

public class PromocodeDB {
	private String url;
	private Properties properties;
	private ProductDB productDB;

	public PromocodeDB(ProductDB productDB) throws SQLException {
		this.properties = new Properties();
		this.url = "jdbc:postgresql://gegevensbanken.khleuven.be:51516/2TX32";
		properties.setProperty("user", "r0459898");
		properties.setProperty("password", "OOOPASS");
		properties.setProperty("ssl", "true");
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
		System.out.println("properties");
		this.productDB = productDB;
	}

	public PromocodeRepository getRepo() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		PromocodeRepository pcr = new PromocodeRepository();

		try {
			Connection connection;
			Statement statement;
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(url, properties);
			System.out.println("connection");
			statement = connection.createStatement();
			System.out.println("statement");

			ResultSet result = statement.executeQuery("SELECT * FROM r0459898.\"PromoCode\"");
			while (result.next()) {
				int id = Integer.parseInt(result.getString("id"));
				int type = Integer.parseInt(result.getString("type"));
				switch (type) {
				case 1:
					Promocode promocodeType1 = new PromocodeProductAmount(id,
							Double.parseDouble(result.getString("amount")),
							productDB.getProductById(result.getString("productId")));
					pcr.add(promocodeType1);
					break;
				case 2:
					Promocode promocodeType2 = new PromocodeProductPercentage(id, Integer.parseInt("percentage"),
							productDB.getProductById(result.getString("productId")));
					pcr.add(promocodeType2);
					break;
				case 3:
					Promocode promocodeType3 = new PromocodeSaleAmount(id,
							Double.parseDouble(result.getString("saleAmountNeeded")),
							Double.parseDouble(result.getString("amount")));
					pcr.add(promocodeType3);
					break;
				case 4:
					Promocode promocodeType4 = new PromocodeSalePercentage(id,
							Integer.parseInt(result.getString("percentage")));
					pcr.add(promocodeType4);
					break;
				default:
					break;
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
