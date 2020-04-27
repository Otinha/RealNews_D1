package service;

import java.util.List;

import dao.ComentarioDAO;
import model.Comentario;

public class ComentarioService {
	ComentarioDAO comentarioDAO;
	
	public ComentarioService() {
		comentarioDAO = new ComentarioDAO();
	}
	
	public List<Comentario> listarComentarios(int idNoticia) {
		return comentarioDAO.listarComentarios(idNoticia);
	}
	
	public boolean adicionarComentario(Comentario comentario) {
		if(comentario.getNome() == null || comentario.getNome().equals("") || comentario.getNome().length() > 126) {
			return false;
		}
		else if(comentario.getTexto() == null || comentario.getTexto().equals("") || comentario.getTexto().length() > 512) {
			return false;
		}
		comentarioDAO.adicionarComentario(comentario);
		return true;
	}
}
