package modelos;

public class Par {
	
	private Integer id;
	private String nome;
	private String data;
	private String form_img;
	private String form_vid;
	private Integer nivel;

	public Par(){
	}	
	
	public Par(Integer id, String nome, String data, String form_img, String form_vid, Integer nivel){
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.form_img = form_img;
		this.form_vid = form_vid;
		this.nivel = nivel;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}	
	public String getForm_img() {
		return form_img;
	}
	public void setForm_img(String form_img) {
		this.form_img = form_img;
	}
	public String getForm_vid() {
		return form_vid;
	}
	public void setForm_vid(String form_vid) {
		this.form_vid = form_vid;
	}
	
	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}
	
	@Override
	public String toString() {
		return "Par [id=" + id + ", nome=" + nome + ", data=" + data + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Par other = (Par) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
