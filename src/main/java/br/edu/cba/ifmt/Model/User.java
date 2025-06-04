package br.edu.cba.ifmt.Model;

public class User {
	private int id;
	private String nome;
	private String email;
	private String CPF;
	private City city;
	
	public User() {}
	
	public User(String nome, String email, String CPF, City city) {
		this.nome = nome;
		this.email = email;
		this.CPF = CPF;
		this.city = city;
	}
	
	public User(int id, String nome, String email, String CPF, City city) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.CPF = CPF;
		this.city = city;
	}

	public int getId() { return id;	}

	public void setId(int id) { this.id = id; }

	public String getNome() { return nome;	}

	public void setNome(String nome) { this.nome = nome; }

	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }

	public String getCPF() { return CPF; }

	public void setCPF(String cPF) { CPF = cPF;	}

	public City getCity() { return city; }

	public void setCity(City city) { this.city = city; }

}
