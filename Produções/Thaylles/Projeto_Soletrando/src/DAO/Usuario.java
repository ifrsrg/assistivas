package DAO;

public class Usuario {
	private String login;
	private String email;
	private String senha;
	
	public Usuario(String login, String email, String senha) {
		super();
		this.login = login;
		this.email = email;
		this.senha = senha;
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
	
}
