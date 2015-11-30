package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PersonDB {
	String url;
    Properties properties;
	public PersonDB() throws SQLException {
		this.properties = new Properties();
		this.url = "jdbc:postgresql://gegevensbanken.khleuven.be:51516/2TX32";
		properties.setProperty("user", "r0459898");
		properties.setProperty("password", "1234Porno1234");
		properties.setProperty("ssl", "true");
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
		System.out.println("properties");

	}
	
	public PersonRepository getRepo(){
		PersonRepository pr = new PersonRepository();
		try{
			Connection connection;
			Statement statement;
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url,properties);
				System.out.println("connection");
				statement = connection.createStatement();
				System.out.println("statement");
				
				ResultSet result = statement.executeQuery( "SELECT * FROM r0459898.\"Person\"" );
				while(result.next()){
					String userId = result.getString("userId");
					String password = result.getString("password");
					String firstName = result.getString("firstName");
					String lastName = result.getString("lastName");
					Person person = new Person(userId, password, firstName, lastName);
					pr.add(person);
				}
				connection.close();
			}catch(SQLException e){
				throw new DbException(e.getMessage(),e);
			}catch(ClassNotFoundException ex){
				throw new DbException(ex.getMessage(),ex);
			}
		return pr;
	}
	

	
	public void add(Person person) throws ClassNotFoundException{
		if(person == null){
			throw new DbException("Nothing to add.");
		}
		String sql = "INSERT INTO r0459898.\"Person\" (\"firstName\", \"userId\", \"lastName\", password)"
				+ "VALUES ('"
				+ person.getFirstName() + "', '" + person.getUserId() + "', '"
				+ person.getLastName() + "', '" + person.getPassword() + "' )";
		try {
 			executeSQL(sql);
		} catch (SQLException e) {
		}
	}
	
	public void delete(Person person){
		if(person == null){
			throw new DbException("No person found");
		}
		String sql = "DELETE FROM r0459898.\"Person\" WHERE \"userId\" LIKE '" + person.getUserId() + "'";
		
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
