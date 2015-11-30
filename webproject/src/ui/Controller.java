package ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Person;
import domain.PersonDB;
import domain.PersonRepository;
import domain.Product;
import domain.ProductDB;
import domain.ProductRepository;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductDB productDB;
	ProductRepository productRepository;
	PersonDB personDB;
	PersonRepository personRepository;
	
       
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public Controller() throws SQLException {
        super();
        productDB = new ProductDB();
        productRepository = productDB.getRepo();
        personDB = new PersonDB();
        personRepository = personDB.getRepo();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesRequest(request, response);
	}
	
	private void procesRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String destination = "";
		if(action == null){
			action = "home";
		}
		switch(action) {
			case "personOverview":
				destination = personOverview(request,response);
				break;
			case "signUp":
				destination = createPerson(request, response);
				break;
			case "home":
				destination = "index.html";
				break;
			case "productOverview":
				destination = productOverview(request, response);
				break;
			case "addProduct":
				destination = addProduct(request, response);
				break;
			case "productInfo":
				destination = productInfo(request, response);
				break;
			case "updateProductInfo":
				destination = updateProductInfo(request, response);
				break;
			case "deleteProduct":
				destination = deleteProduct(request, response);
				break;
			case "deleteProductConf":
				destination = deleteProductConf(request, response);
				break;
			case "deletePerson":
				destination = deletePerson(request, response);
				break;
			case "deletePersonConf":
				destination = deletePersonConf(request, response);
				break;
			default:
				break;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(destination);
		view.forward(request, response);
	}

	private String productOverview(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("products", productRepository.getAll());
		return "productOverview.jsp";
	}

	private String personOverview(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("persons", personRepository.getAll());
		return "personOverview.jsp";
		
	}
	
	private String addProduct(HttpServletRequest request, HttpServletResponse response) {
		Product product = new Product();
		ArrayList<String> result = new ArrayList<String>();
		getProductId(product, request, result);
		getProductDescription(product, request, result);
		getProductPrice(product, request, result);
		if(result.size() > 0){
			request.setAttribute("result", result);
			return "addProduct.jsp";
		}
		productRepository.add(product);
		try{
			productDB.add(product);
		}catch(ClassNotFoundException e){
		}
		return productOverview(request, response);
	}


	private void getProductId(Product p, HttpServletRequest request, ArrayList<String> result){
		String id = request.getParameter("id");
		try{
			p.setId(id);
			request.setAttribute("id", "has-success");
		}catch(Exception e){
			result.add(e.getMessage());
			request.setAttribute("id", "has-error");
		}
	}
	
	private void getProductDescription(Product product, HttpServletRequest request, ArrayList<String> result) {
		String productDesc=request.getParameter("description");
		try{
			product.setDescription(productDesc);
			request.setAttribute("description", "has-success");
		}catch(Exception e){
			result.add(e.getMessage());
			request.setAttribute("description", "has-error");
		}
	}
	
	private void getProductPrice(Product product, HttpServletRequest request, ArrayList<String> result) {
		double price= Double.parseDouble(request.getParameter("price"));
		try{
			product.setPrice(price);
			request.setAttribute("price", "has-success");
		}catch(Exception e){
			result.add(e.getMessage());
			request.setAttribute("price", "has-error");
		}
	}
	
	private String productInfo(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("product", productRepository.getProductById(request.getParameter("id")));
		return "productInfo.jsp";
	}
	
	private String updateProductInfo(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Product product = productRepository.getProductById(id);
		String desc = request.getParameter("description");
		double price = Double.parseDouble(request.getParameter("price"));
		productRepository.updateDesc(product, desc);
		productRepository.updatePrice(product, price);
		productDB.update(product, desc, price);
		return productInfo(request, response);
	}
	
	private String deleteProduct(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("product", productRepository.getProductById(request.getParameter("id")));
		return "deleteProductConf.jsp";
	}
	
	private String deleteProductConf(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		productDB.delete(productRepository.getProductById(id));
		productRepository.delete(id);
		return productOverview(request, response);
	}
	
	private String deletePerson(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("person", personRepository.get(request.getParameter("id")));
		return "deletePersonConf.jsp";
	}
	
	private String deletePersonConf(HttpServletRequest request, HttpServletResponse response){
		String userId = request.getParameter("userId");
		personDB.delete(personRepository.get(userId));
		personRepository.delete(userId);
		
		return personOverview(request, response);
}
	
	
	private String createPerson(HttpServletRequest request, HttpServletResponse response) {
		Person person = new Person();
		ArrayList<String> result = new ArrayList<String>();
		getFirstname(person, request, result);
		getLastname(person, request, result);
		getEmail(person, request, result);
		getPassword(person, request, result);
		
		if(result.size() > 0){
			request.setAttribute("result", result);
			return "signUp.jsp";
		}
		
		personRepository.add(person);
		try {
			personDB.add(person);
		} catch (ClassNotFoundException e) {
		}
		return personOverview(request, response);
	}

	private void getFirstname(Person p, HttpServletRequest request, ArrayList<String> result){
		String firstName = request.getParameter("firstName");
		try{
			p.setFirstName(firstName);
			request.setAttribute("firstNameClass", "has-success");
		}catch(Exception e){
			result.add(e.getMessage());
			request.setAttribute("firstNameClass", "has-error");
		}
	}

	private void getLastname(Person p, HttpServletRequest request, ArrayList<String> result){
		String lastName=request.getParameter("lastName");
		try{
			p.setLastName(lastName);
			request.setAttribute("lastNameClass", "has-success");
		}catch(Exception e){
			result.add(e.getMessage());
			request.setAttribute("lastNameClass", "has-error");
		}
	}
	
	private void getEmail(Person p, HttpServletRequest request, ArrayList<String> result) {
		String email=request.getParameter("email");
		try{
			p.setUserId(email);
			request.setAttribute("emailClass", "has-success");
		}catch(Exception e){
			result.add(e.getMessage());
			request.setAttribute("emailClass", "has-error");
		}
	}
	
	private void getPassword(Person p, HttpServletRequest request, ArrayList<String> result){
		String password = request.getParameter("password");
		try{
			p.setPassword(password);
			request.setAttribute("passwordClass", "has-success");
		}catch(Exception e){
			result.add(e.getMessage());
			request.setAttribute("passwordClass", "has-error");
		}
	}
	
	//TEST

}







