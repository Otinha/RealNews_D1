package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Noticia;
import service.NoticiaService;

@WebServlet("/AlterarNoticia.do")
public class AtualizarNoticiaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		NoticiaService noticiaService = new NoticiaService();
		Noticia noticia = noticiaService.consultar(id);
		request.setAttribute("noticia", noticia);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("alterarNoticia.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String texto = request.getParameter("texto");
		
		Noticia noticia = new Noticia(id, descricao, titulo, texto);
		NoticiaService noticiaService = new NoticiaService();
		
		if(noticiaService.alterar(noticia)) {
			out.println("Notícia alterada com sucesso! <br>");
			out.println("<a href='ListarNoticias.do'>Retornar ao início</a>");
		}
		else {
			out.println("<script>alert('Dados preenchidos de maneira inválida!')</script>");
			request.setAttribute("noticia", noticia);
			RequestDispatcher dispatcher = request.getRequestDispatcher("alterarNoticia.jsp?id=" + id);
			dispatcher.forward(request, response);
		}
	}

}
