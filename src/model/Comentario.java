package model;

public class Comentario {
	private int id;
	private String nome;
	private String texto;
	private int idNoticia;
	
	public Comentario() {}

	public Comentario(int id, String nome, String texto, int idNoticia) {
		setId(id);
		setNome(nome);
		setTexto(texto);
		setIdNoticia(idNoticia);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getIdNoticia() {
		return idNoticia;
	}

	public void setIdNoticia(int idNoticia) {
		this.idNoticia = idNoticia;
	}
	
}
