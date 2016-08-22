package modelos;

public class Usuario {
	
	private String nome, email, login, senha;
	private Integer id;
	
	public Usuario(String nome, String email, String login, String senha){
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario(String user){
		String[] vetor = user.split(",");
		nome = vetor[0];
		email = vetor[1];
		login = vetor[2];
		senha = vetor[3];
		id = Integer.parseInt(vetor[4]);
	}
	
	public Usuario() {
	}

	public void setId(int num) {
		id = num;
		
	}
	
	public int getId(){
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return nome + "," + email + "," + login + "," + senha + "," + id;
	}
	
}
