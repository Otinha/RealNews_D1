package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comentario;
import model.Noticia;
import service.ComentarioService;
import service.NoticiaService;

@WebServlet("/LerNoticia.do")
public class NoticiaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter saida = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		NoticiaService noticiaService = new NoticiaService();
		Noticia noticia = noticiaService.consultar(id);
		ComentarioService comentarioService = new ComentarioService();
		ArrayList<Comentario> listaComentarios = (ArrayList<Comentario>) comentarioService.listarComentarios(id);
		request.setAttribute("noticia", noticia);
		request.setAttribute("listaComentarios", listaComentarios);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("lerNoticia.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int idNoticia = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String texto = request.getParameter("texto");
		Comentario comentario = new Comentario();
		comentario.setNome(nome);
		comentario.setTexto(texto);
		comentario.setIdNoticia(idNoticia);
		
		ComentarioService comentarioService = new ComentarioService();
		
		if(comentarioService.adicionarComentario(comentario)) {
			doGet(request, response);
		}
		else {
			out.println("Comentário inválido! Verifique se preencheu corretamente os campos nome e texto!<br>");
			out.println("<a href='LerNoticia.do?id=" + idNoticia + "'>Voltar para a notícia</a>");
		}	
	}
}
