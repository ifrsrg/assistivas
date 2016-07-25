package modelos;

public class Usuario {
	
	private String nome, email, login, senha;
	private int id, num_jogos;
	private long duracao;
	
	public Usuario(String nome, String email, String login, String senha){
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.num_jogos = 0;
		this.duracao = 0;
	}
	
	public Usuario(String user){
		String[] vetor = user.split(",");
		nome = vetor[0];
		email = vetor[1];
		login = vetor[2];
		senha = vetor[3];
		id = Integer.parseInt(vetor[4]);
		num_jogos = Integer.parseInt(vetor[5]);
		duracao = Integer.parseInt(vetor[6]);
	}
	
	public Usuario() {
	}

	public String toInsert(){
		return "'" + nome + "','" + email + "','" + login + "','" + senha + "'," + num_jogos + "," + duracao;
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

	public int getNum_jogos() {
		return num_jogos;
	}

	public void setNum_jogos(int num_jogos) {
		this.num_jogos = num_jogos;
	}

	public long getDuracao() {
		return duracao;
	}

	public void setDuracao(long duracao) {
		this.duracao = duracao;
	}

	@Override
	public String toString() {
		return nome + "," + email + "," + login + "," + senha + "," + id + "," + num_jogos + "," + duracao;
	}
	
}
