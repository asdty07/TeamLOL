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

@WebServlet("/data")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        // 데이터베이스 조회 및 데이터 가져오기
	        ChampDao cd = new ChampDao();
	        List<String> champions = cd.getAllChampions(); // 예시: 모든 챔피언 목록을 가져오는 메서드

	        // 데이터를 JSON 형태로 변환
	        
	        ObjectMapper objectMapper = new ObjectMapper();
	        String jsonData = objectMapper.writeValueAsString(champions);

	        // 응답으로 JSON 데이터 보내기
	        response.getWriter().write(jsonData);
	        
	        cd.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
