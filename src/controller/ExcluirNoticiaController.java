package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Noticia;
import service.NoticiaService;

@WebServlet("/ExcluirNoticia.do")
public class ExcluirNoticiaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Noticia noticia = new Noticia();
		noticia.setId(id);
		NoticiaService noticiaService = new NoticiaService();
		
		if(noticiaService.excluir(noticia)) {
			out.println("Not�cia exclu�da com sucesso! <br>");
			out.println("<a href='ListarNoticias.do'>Voltar ao in�cio</a>");
		}
	}

}
