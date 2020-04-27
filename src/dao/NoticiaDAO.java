package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Noticia;

public class NoticiaDAO {
	Connection conexao;
	
	public NoticiaDAO() {
		this.conexao = ConnectionFactory.conectar();		
	}
	
	public ArrayList<Noticia> listarNoticias() {
		String sql = "SELECT * FROM noticia";
		
		try(PreparedStatement stm = conexao.prepareStatement(sql)) {
			ResultSet rs = stm.executeQuery();
			ArrayList<Noticia> listaNoticias = new ArrayList<Noticia>();
				
			while(rs.next()) {
				Noticia noticia = new Noticia();
				noticia.setId(rs.getInt("id"));
				noticia.setDescricao(rs.getString("descricao"));
				noticia.setTitulo(rs.getString("titulo"));
				noticia.setTexto(rs.getString("texto"));
				listaNoticias.add(noticia);
			}
			return listaNoticias;
		} 
		catch(SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public Noticia consultar(int id) {
		String sql = "SELECT * FROM noticia WHERE id=?";
		Noticia noticia = new Noticia();
		try(PreparedStatement stm = conexao.prepareStatement(sql)) {
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			ArrayList<Noticia> listaNoticias = new ArrayList<Noticia>();
				
			if(rs.next()) {		
				noticia.setId(rs.getInt("id"));
				noticia.setDescricao(rs.getString("descricao"));
				noticia.setTitulo(rs.getString("titulo"));
				noticia.setTexto(rs.getString("texto"));
				listaNoticias.add(noticia);
			}
			return noticia;
		} 
		catch(SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public void cadastrar(Noticia noticia) {
		String sql = "INSERT INTO noticia (id, descricao, titulo, texto) VALUES (?, ?, ?, ?)";
		
		try(PreparedStatement stm = conexao.prepareStatement(sql)) {
			stm.setInt(1, noticia.getId());
			stm.setString(2, noticia.getDescricao());
			stm.setString(3, noticia.getTitulo());
			stm.setString(4, noticia.getTexto());
			stm.execute();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void alterar(Noticia noticia) {
		String sql = "UPDATE noticia set descricao = ?, titulo = ?, texto = ? WHERE id=?";
		
		try(PreparedStatement stm = conexao.prepareStatement(sql)) {		
			stm.setString(1, noticia.getDescricao());
			stm.setString(2, noticia.getTitulo());
			stm.setString(3, noticia.getTexto());
			stm.setInt(4, noticia.getId());
			stm.execute();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void excluir(Noticia noticia) {
		String sql = "DELETE FROM noticia WHERE ID = ?";
		
		try(PreparedStatement stm = conexao.prepareStatement(sql)) {
			stm.setInt(1, noticia.getId());
			stm.execute();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
