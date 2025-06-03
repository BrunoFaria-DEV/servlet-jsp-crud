package br.edu.cba.ifmt.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.cba.ifmt.Model.User;

public class UserController extends HttpServlet{
	//private IUserDAO _userDAO;
	
	//public UserController() {
		//_userDAO = new UserDAO();
	//}
	
	@Override
	public void init() throws ServletException {
		//_userDAO = new UserDAO();
	}
	
	protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = new ArrayList<User>();
		
		request.setAttribute("users", users);
		
		request.getRequestDispatcher("resources/views/usuarios/index.jsp").forward(request, response);
	}
	
	protected void create(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("usuarios/create.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		System.out.println(path);
		
		try {
			switch (path) {
			case "/usuarios": 
				index(request, response);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + path);
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		System.out.println(path);
		
		try {
			switch (path) {
			case "/usuarios/novo": 
				index(request, response);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + path);
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String path = request.getServletPath();
		
		System.out.println(path);
		
		try {
			switch (path) {
			case "/usuarios/editar":
				index(request, response);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + path);
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		
		System.out.println(path);
		
		try {
			switch (path) {
			case "/usuarios/excluir?id":
				index(request, response);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + path);
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
}
