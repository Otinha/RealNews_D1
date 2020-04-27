package service;

import java.util.ArrayList;
import java.util.List;

import dao.NoticiaDAO;
import model.Noticia;

public class NoticiaService {
	NoticiaDAO noticiaDAO;
	
	public NoticiaService() {
		this.noticiaDAO = new NoticiaDAO();
	}
	
	public ArrayList<Noticia> listarNoticias() {
		ArrayList<Noticia> listaNoticias = noticiaDAO.listarNoticias();
		return listaNoticias;
	}
	
	public Noticia consultar(int id){
		Noticia noticia = noticiaDAO.consultar(id);
		return noticia;
	}
	
	public boolean cadastrar(Noticia noticia) {
		if(noticia.getTitulo().length() > 126 || noticia.getTitulo() == null || noticia.getTitulo().equals("")) {
			return false;
		}
		else if(noticia.getDescricao().length() > 512 || noticia.getTitulo() == null || noticia.getTitulo().equals("")) {
			return false;
		}
		else if(noticia.getTexto() == null || noticia.getTexto().equals("")) {
			return false;
		}
		noticiaDAO.cadastrar(noticia);
		return true;
	}
	
	public boolean alterar(Noticia noticia) {
		if(noticia.getTitulo().length() > 126 || noticia.getTitulo() == null || noticia.getTitulo().equals("")) {
			return false;
		}
		else if(noticia.getDescricao().length() > 512 || noticia.getTitulo() == null || noticia.getTitulo().equals("")) {
			return false;
		}
		else if(noticia.getTexto() == null || noticia.getTexto().equals("")) {
			return false;
		}
		noticiaDAO.alterar(noticia);
		return true;
	}
	
	public boolean excluir(Noticia noticia) {
		noticiaDAO.excluir(noticia);
		return true;
	}
}
