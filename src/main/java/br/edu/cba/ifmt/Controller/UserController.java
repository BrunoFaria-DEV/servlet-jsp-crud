package br.edu.cba.ifmt.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.cba.ifmt.Model.User;
import br.edu.cba.ifmt.Model.City;
import br.edu.cba.ifmt.DAO.CityDAO;
import br.edu.cba.ifmt.DAO.UserDAO;

public class UserController extends HttpServlet{
	private UserDAO _userDAO;
	private CityDAO _cityDAO;
	
	@Override
	public void init() throws ServletException {
		_userDAO = new UserDAO();
		_cityDAO = new CityDAO();
	}
	
	protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = _userDAO.getAll();
		List<City> cities = _cityDAO.getAll();
		
		System.out.println(request.getRequestDispatcher(getServletInfo()));
		request.setAttribute("users", users);
		request.setAttribute("cities", cities);
		request.getRequestDispatcher("/resources/views/usuarios/index.jsp").forward(request, response);
	}
	
	protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<City> cities = _cityDAO.getAll();
		
		System.out.println(request.getRequestDispatcher(getServletInfo()));
		request.setAttribute("cities", cities);
		request.getRequestDispatcher("/resources/views/usuarios/create.jsp").forward(request, response);
	}
	
	protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nome        = request.getParameter("nome");
			String email       = request.getParameter("email");
			String cpf         = request.getParameter("cpf");
			int municipio_id   = Integer.parseInt(request.getParameter("municipio_id"));

			City city = _cityDAO.getById(municipio_id);
			if (city == null) {
				System.out.println("retornou na cidade");
				request.getSession().setAttribute("erros", "O município não foi encontrado na base de dados!");
				response.sendRedirect(request.getContextPath() + "/usuarios/novo");
				return;
			}

			User user = new User(nome, email, cpf, city);

			boolean result = _userDAO.add(user);
			if (result == false) {
				System.out.println("erro na persistencia");
				request.getSession().setAttribute("erros", "Erro ao criar o usuário.");
				response.sendRedirect(request.getContextPath() + "/usuarios/novo");
				return;
			}

			request.getSession().setAttribute("success", "Usuário criado com sucesso!");
			response.sendRedirect(request.getContextPath() + "/usuarios");

		} catch (Exception e) {
			System.err.println("Erro UserController.store(): " + e.getMessage());
			request.getSession().setAttribute("erros", "Ocorreu um erro inesperado ao processar o formulário.");
			response.sendRedirect(request.getContextPath() + "/usuarios");
		}
	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getPathInfo().substring(1));
		User user = _userDAO.getById(id);
		List<City> cities = _cityDAO.getAll();
		
		System.out.println("edit(): " + user.getCity().getNome());
		
		System.out.println(request.getRequestDispatcher(getServletInfo()));
		request.setAttribute("cities", cities);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/resources/views/usuarios/edit.jsp").forward(request, response);
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getPathInfo().substring(1));
		
		User user = _userDAO.getById(id);
		if (user == null) {
			request.getSession().setAttribute("erros", "O usuário não foi encontrado na base de dados!");
			response.sendRedirect(request.getContextPath() + "/usuarios");
			return;
		}
		
		String nome        = request.getParameter("nome");
		String email       = request.getParameter("email");
		String cpf         = request.getParameter("cpf");
		int municipio_id   = Integer.parseInt(request.getParameter("municipio_id"));
		
		City city = _cityDAO.getById(municipio_id);
		
		if (city == null) {
			request.getSession().setAttribute("erros", "O município não foi encontrado na base de dados!");
			response.sendRedirect(request.getContextPath() + "/usuarios/novo");
			return;
		}
		
		try {

			user.setNome(nome);
			user.setEmail(email);
			user.setCPF(cpf);
			user.setCity(city);
			
			boolean result = _userDAO.update(id, user);
			if (result == false) {
				System.out.println("erro na persistencia");
				request.getSession().setAttribute("erros", "Erro ao editar o usuário.");
				response.sendRedirect(request.getContextPath() + "/usuarios/novo");
				return;
			}

			request.getSession().setAttribute("success", "Usuário editado com sucesso!");
			response.sendRedirect(request.getContextPath() + "/usuarios");

		} catch (Exception e) {
			System.err.println("Erro UserController.update(): " + e.getMessage());
			request.getSession().setAttribute("erros", "Ocorreu um erro inesperado ao processar o formulário.");
			response.sendRedirect(request.getContextPath() + "/usuarios");
		}
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getPathInfo().substring(1));

		try {
			boolean result = _userDAO.delete(id);
			if (result == false) {
				System.out.println("erro na persistencia");
				request.getSession().setAttribute("erros", "Erro ao excluir o usuário.");
				response.sendRedirect(request.getContextPath() + "/usuarios");
				return;
			}

			request.getSession().setAttribute("success", "Usuário excluido com sucesso!");
			response.sendRedirect(request.getContextPath() + "/usuarios");

		} catch (Exception e) {
			System.err.println("Erro UserController.store(): " + e.getMessage());
			request.getSession().setAttribute("erros", "Ocorreu um erro inesperado ao processar a ação.");
			response.sendRedirect(request.getContextPath() + "/usuarios");
		}
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
			case "/usuarios/novo":
				create(request, response);
				break;
			case "/usuarios/editar": 
				edit(request, response);
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
			case "/usuarios/store": 
				store(request, response);
				break;
			case "/usuarios/update":
				update(request, response);
				break;
			case "/usuarios/excluir":
				delete(request, response);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + path);
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
}
