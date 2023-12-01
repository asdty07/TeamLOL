package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import common.JdbcUtil;

public class ChampDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public ChampDao() {
		this.con = JdbcUtil.getConnection();
	}

	public List<Map<String, String>> searchChampName(String search) {
	    String sql = "SELECT * FROM champ WHERE kr LIKE ? OR en LIKE ? limit 15";

	    List<Map<String, String>> result = new ArrayList<>();

	    try {
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, search);
	        pstmt.setString(2, search);

	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Map<String, String> row = new LinkedHashMap<>(); // 넣은 순서대로 정렬해준다.
	            // 데이터의 열을 가져옴
	            String kr = rs.getString("kr");
//	            String gId = rs.getString("gameId");
	            String lane = rs.getString("lane");
	            String win = rs.getString("win");
	            String kills = rs.getString("kills");
	            String assists = rs.getString("assists");
	            String deaths = rs.getString("deaths");

	            // 가져올 행
	            row.put("kr", kr);
//	            row.put("gId", gId);
	            row.put("lane", lane);
	            row.put("win", win);
	            row.put("kills", kills);
	            row.put("assists", assists);
	            row.put("deaths", deaths);
	            
	            result.add(row);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return result;
	}

	public void close() {
		JdbcUtil.dbClose(rs, pstmt, con);
	}

	public List<String> getAllChampions() {
		   String sql = "SELECT kr.champion_name_kr AS krName, en.champion_name_en AS enName "
	               + "FROM ch_kr AS kr JOIN cn_en AS en ON kr.championId = en.championId "
	               + "ORDER BY krName ASC, enName ASC";
		List<String> cList = new ArrayList<String>(); 
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String cName = rs.getString("krName");
				
				cList.add(cName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cList;
	}

	public HashMap<String, String> ChampNameKda(String name) {
		String sql = "SELECT t1.kr, ROUND((SUM(t1.kills) + SUM(t1.assists)) / SUM(t1.deaths), 2) AS kda\r\n"
				+ "FROM (SELECT champ.* FROM champ WHERE kr LIKE ?) t1\r\n"
				+ "GROUP BY t1.kr\r\n"
				+ "ORDER BY t1.kr";
		HashMap<String, String> totalKda = new HashMap<String, String>();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String kda = rs.getString("kda");
				
				totalKda.put("kda", kda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalKda;
	}

	public List<String> champPick(String name) {
		String sql = "SELECT concat(ROUND((SELECT COUNT(*)FROM champ WHERE kr = ?) / 100000 * 100, 2),'%') as pickrate";
		
		List<String> pickList = new ArrayList<String>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String pick = rs.getString("pickrate");
				
				pickList.add(pick);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pickList;
	}

	public List<String> champWins(String wins) {
		String sql = "SELECT concat(ROUND((SELECT COUNT(*) FROM champ WHERE kr = ? AND WIN = 'TRUE') / (SELECT COUNT(*) FROM champ WHERE kr = ?) * 100, 2),'%') AS WINRATE";
		
		List<String> win = new ArrayList<String>();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, wins);
			pstmt.setString(2, wins);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String cWin = rs.getString("WINRATE");
				
				win.add(cWin);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return win;
	}
}
