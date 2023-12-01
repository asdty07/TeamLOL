package controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MemberInfo;
import bean.SearchChamp;
import common.Forward;

@WebServlet({"/search", "/champList", "/kda", "/loginFrm", "/login", "/join", "/joinFrm", "/logout"})
public class MainContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String cmd = request.getServletPath();
		System.out.println("cmd=" + cmd);
		
		Forward fw = null;
		
		// 챔피언 검색
		SearchChamp sc = new SearchChamp(request, response);
		
		// 회원
		MemberInfo mi = new MemberInfo(request, response);
		
		if(cmd.equals("/search")) {
			fw = sc.search();
		} else if(cmd.equals("/kda")) {
			fw = sc.kda();
		} else if(cmd.equals("/loginFrm")) {
			fw = mi.loginFrm();
		} else if(cmd.equals("/login")) {
			fw = mi.login();
		} else if(cmd.equals("/join")) {
			fw = mi.join();
		} else if(cmd.equals("/joinFrm")) {
			fw = mi.joinFrm();
		} else if(cmd.equals("/logout")) {
			fw = mi.logOut();
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
