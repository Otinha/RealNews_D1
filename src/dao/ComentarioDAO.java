package dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Comentario;

public class ComentarioDAO {
	Connection conexao;
	
	public ComentarioDAO() {
		conexao = ConnectionFactory.conectar();
	}
	
	public List<Comentario> listarComentarios(int idNoticia) {
		List<Comentario> listaComentarios = new ArrayList<Comentario>();
		String sql = "SELECT nome, texto FROM comentario WHERE fk_noticia_id = ?";
		
		try(PreparedStatement stm = conexao.prepareStatement(sql)) {
			stm.setInt(1, idNoticia);
			
			try(ResultSet rs = stm.executeQuery()) {
				while(rs.next()) {
					Comentario comentario = new Comentario();
					comentario.setNome(rs.getString("nome"));
					comentario.setTexto(rs.getString("texto"));
					listaComentarios.add(comentario);
				}
				
				return listaComentarios;
			}	
			catch(SQLException e1) {
				throw new RuntimeException(e1);
			}
		}
		catch(SQLException e2) {
			throw new RuntimeException(e2);
		}
	}
	
	public void adicionarComentario(Comentario comentario) {
		String sql = "SELECT MAX(id) AS 'id' FROM comentario";
		
		try(PreparedStatement stm = conexao.prepareStatement(sql)) {
			
			try(ResultSet rs = stm.executeQuery()) {
				if(rs.next()) {
					int ultimoId = rs.getInt("id");
					comentario.setId(ultimoId + 1);
				}
				else {
					comentario.setId(1);
				}
				
				sql = "INSERT INTO comentario (id, nome, texto, fk_noticia_id) VALUES (?, ?, ?, ?)";
				
				try(PreparedStatement stmInsert = conexao.prepareStatement(sql)) {
					stmInsert.setInt(1, comentario.getId());
					stmInsert.setString(2, comentario.getNome());
					stmInsert.setString(3, comentario.getTexto());
					stmInsert.setInt(4, comentario.getIdNoticia());
					stmInsert.execute();
				}
			}	
			catch(SQLException e1) {
				throw new RuntimeException(e1);
			}
		}
		catch(SQLException e2) {
			throw new RuntimeException(e2);
		}
	}
}
