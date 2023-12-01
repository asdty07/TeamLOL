package bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.Forward;
import dao.ChampDao;

public class SearchChamp {
	HttpServletRequest request;
	HttpServletResponse response;

	public SearchChamp(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public Forward search() throws JsonProcessingException {
		ChampDao cd = new ChampDao();
		Forward fw = new Forward();

		String search = request.getParameter("search");

		System.out.println(search);

		List<Map<String, String>> searchChamp = cd.searchChampName(search);

		cd.close();

		ObjectMapper objM = new ObjectMapper();
		String json = objM.writeValueAsString(searchChamp);

		request.setAttribute("searchResult", json);

		System.out.println("json : " + json);

		fw.setPath("/searchFrm.jsp");
		fw.setRedirect(false);
		return fw;
	}


	public Forward kda() throws JsonProcessingException {
		ChampDao cd = new ChampDao();
		Forward fw = new Forward();

		String name = request.getParameter("champName");

		HashMap<String, String> hm = cd.ChampNameKda(name);

		System.out.println("name : " + name);

		cd.close();

		ObjectMapper objM = new ObjectMapper();
		String json = objM.writeValueAsString(hm);
		
		request.setAttribute("name", name);
		request.setAttribute("champKda", json);

		System.out.println("json : " + json);

		fw.setPath("/champInfo.jsp");
		fw.setRedirect(false);
		return fw;
	}

}
