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
	
	public String toInsert(){
		return "'" + nome + "','" + email + "','" + login + "','" + senha + "'," + num_jogos + "," + duracao;
	}

	public void setId(int num) {
		id = num;
		
	}
	
	public int getId(){
		return id;
	}

	@Override
	public String toString() {
		return nome + "," + email + "," + login + "," + senha + "," + id + "," + num_jogos + "," + duracao;
	}
	
}
