package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Noticia;
import service.NoticiaService;

@WebServlet("/ListarNoticias.do")
public class ListarNoticiaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset-UTF-8");
		
		NoticiaService noticiaService = new NoticiaService();
		ArrayList<Noticia> listaNoticias = noticiaService.listarNoticias();
		request.setAttribute("listaNoticias", listaNoticias);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("gerenciarNoticia.jsp");
		dispatcher.forward(request, response);
	}

}
