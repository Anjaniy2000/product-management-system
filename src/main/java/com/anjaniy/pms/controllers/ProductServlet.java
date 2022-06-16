package com.anjaniy.pms.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anjaniy.pms.dao.ProductDAO;
import com.anjaniy.pms.models.Product;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProductDAO productDAO;
	
	public void init() throws ServletException {
		productDAO = new ProductDAO();
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		switch(action) {
		
		case "/new":
			showNewForm(request, response);
			break;
			
		case "/insert":
			insertProduct(request, response);
			break;
			
		case "/delete":
			deleteProduct(request, response);
			break;
			
		case "/edit":
			showEditForm(request, response);
			break;
			
		case "/update":
			updateProduct(request, response);
			break;
			
		default:
			listProducts(request, response);
			break;
		}
		
	}


	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("insert-product-form.jsp");
		dispatcher.forward(request, response);
	}
	

	private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		long price = Long.parseLong(request.getParameter("price"));
		
		Product product = new Product(name, description, category, price);
		productDAO.insertProduct(product);
		response.sendRedirect("list");
	}
	


	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try{
			productDAO.deleteProduct(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("list");
	}
	

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Product existingProduct;
		
		try {
			existingProduct = productDAO.getProductById(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("insert-product-form.jsp");
			request.setAttribute("product", existingProduct);
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		long price = Long.parseLong(request.getParameter("price"));
		
		Product product = new Product(id, name, description, category, price);
		productDAO.updateProduct(product);
		response.sendRedirect("list");
	}
	
	private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		
		try {
			List<Product> products = productDAO.getAllProducts();
			request.setAttribute("products", products);
			RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


}
