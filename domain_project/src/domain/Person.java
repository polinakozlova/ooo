package domain;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	private String salt;
	private String userPassword;

	public Person(String userId, String password, String firstName, String lastName) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		setUserId(userId);
		setFirstName(firstName);
		setLastName(lastName);
		SecureRandom random = new SecureRandom();
		salt = new BigInteger(1, random.generateSeed(20)).toString(16);
		setSalt(salt);
		setHashedPassword(password);
	}
	
	public Person(String userId, String password, String firstName, String lastName, String salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		setUserId(userId);
		setFirstName(firstName);
		setLastName(lastName);
		setSalt(salt);
		setHashedPassword(password);
	}
	
	
	public Person() {
	}

	public void setUserId(String userId) {
		if(userId.isEmpty()){
			throw new IllegalArgumentException("No id given");
		}
		String USERID_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(userId);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean isCorrectPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		return getPassword().equals(password);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.isEmpty()){
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.isEmpty()){
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}	

	public void setPassword(String password) {
        if(password.isEmpty()){
                throw new IllegalArgumentException("No password given");
        }
        this.userPassword = password;
	}
	
	public void setHashedPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		 this.password = hashPassword(password, getSalt().getBytes(StandardCharsets.UTF_8));
	}
	
	private String getSalt(){
		return this.salt;
	}
	
	private void setSalt(String salt){
		this.salt = salt;
	}
	
	private static String hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		crypt.reset();
		// salt
		crypt.update(salt);
		crypt.update(password.getBytes("UTF-8"));
		return new BigInteger(1, crypt.digest()).toString(16);
	}
}
