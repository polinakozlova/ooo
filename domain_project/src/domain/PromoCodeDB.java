package domain;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class PromoCodeDB {
		String url;
	    Properties properties;
		public PromoCodeDB() throws SQLException {
			this.properties = new Properties();
			this.url = "jdbc:postgresql://gegevensbanken.khleuven.be:51516/2TX32";
			properties.setProperty("user", "r0459898");
			properties.setProperty("password", "OOOPASS");
			properties.setProperty("ssl", "true");
			properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
			System.out.println("properties");

		}
		
		public PromoCodeRepository getRepo() throws NoSuchAlgorithmException, UnsupportedEncodingException{
			PromoCodeRepository pcr = new PromoCodeRepository();
			try{
				Connection connection;
				Statement statement;
					Class.forName("org.postgresql.Driver");
					connection = DriverManager.getConnection(url,properties);
					System.out.println("connection");
					statement = connection.createStatement();
					System.out.println("statement");
					
					ResultSet result = statement.executeQuery( "SELECT * FROM r0459898.\"PromoCode\"" );
					while(result.next()){
						int id = Integer.parseInt(result.getString("id"));
						int type = Integer.parseInt(result.getString("type"));
						int percentage = Integer.parseInt(result.getString("percentage"));
						double amount = Double.parseDouble(result.getString("amount"));
						double saleAmountNeeded = Double.parseDouble(result.getString("saleAmountNeeded"));
						PromoCode promoCode = new PromoCode(id, type, percentage, amount, saleAmountNeeded);
						pcr.add(promoCode);
					}
					connection.close();
				}catch(SQLException e){
					throw new DbException(e.getMessage(),e);
				}catch(ClassNotFoundException ex){
					throw new DbException(ex.getMessage(),ex);
				}
			return pcr;
		}
		

		
		public void delete(PromoCode promoCode){
			if(promoCode == null){
				throw new DbException("No promoCode found");
			}
			String sql = "DELETE FROM r0459898.\"PromoCode\" WHERE \"id\" LIKE '" + promoCode.getId() + "'";
			
			try {
	 			executeSQL(sql);
			} catch (SQLException e) {
				throw new DbException(e);
			}
		}

	  
	  public void executeSQL(String sql) throws SQLException{
	      Connection connection;
	      Statement statement;
	      try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	      connection = DriverManager.getConnection(this.url,this.properties);
	      statement = connection.createStatement();
	      int result = statement.executeUpdate(sql);
	      connection.close();
	  }
		
	}


