package controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.SearchChamp;
import common.Forward;

@WebServlet({"/search", "/champList", "/kda"})
public class HomeContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String cmd = request.getServletPath();
		System.out.println("cmd=" + cmd);
		
		Forward fw = null;
		
		// 챔피언 검색
		SearchChamp sc = new SearchChamp(request, response);
		
		if(cmd.equals("/search")) {
			fw = sc.search();
		} else if(cmd.equals("/kda")) {
			fw = sc.kda();
		}
		
		
		if(fw != null) {
			if(fw.isRedirect()) { //true: redirect
				response.sendRedirect(fw.getPath()); //새로운 req, res객체,주소창 새url
			}else {
				request.getRequestDispatcher(fw.getPath()).forward(request, response); //기존 req, res객체사용,주소창 갱신안됨
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
