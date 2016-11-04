package model;

public class Usuario {
	private int id;
	private String login;
	private String email;
	private String senha;
	
	@Override
	public String toString() {
		return "Usuario [login=" + login + ", email=" + email + ", senha=" + senha + "]";
	}

	public String getLogin() {
		return login;
	}
	
	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
