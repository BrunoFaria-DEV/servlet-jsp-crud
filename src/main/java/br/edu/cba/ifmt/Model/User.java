package br.edu.cba.ifmt.Model;

public class User {
	private int id;
	private String nome;
	private String email;
	private String CPF;
	private int municipio_id;
	
	public User() {}
	
	public User(String nome, String email, int municipio_id) {
		this.nome = nome;
		this.email = email;
		this.municipio_id = municipio_id;
	}
	
	public User(int id, String nome, String email, int municipio_id) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.municipio_id = municipio_id;
	}

	public int getId() { return id;	}

	public void setId(int id) { this.id = id; }

	public String getNome() { return nome;	}

	public void setNome(String nome) { this.nome = nome; }

	public String getEmail() { return email; }

	public void setEmail(String email) { this.email = email; }

	public String getCPF() { return CPF; }

	public void setCPF(String cPF) { CPF = cPF;	}

	public int getMunicipio_id() { return municipio_id; }

	public void setMunicipio_id(int municipio_id) { this.municipio_id = municipio_id; }
}
