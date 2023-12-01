package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ChampDao;


@WebServlet("/wins")
public class WinsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String wins = request.getParameter("wins");
        
        System.out.println("wins : " + wins);
        
        ChampDao cd = new ChampDao();
        List<String> list = cd.champWins(wins);
        
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writeValueAsString(list);
        
        response.getWriter().write(jsonData);
        
        cd.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
