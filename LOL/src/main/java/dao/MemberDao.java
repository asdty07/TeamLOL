package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.JdbcUtil;

public class MemberDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public MemberDao() {
		this.con = JdbcUtil.getConnection();
	}
	
	public boolean join(HashMap<String, String> hMap) {
		String sql = "insert into member values(?, ?, ?, ?)";
		// """
		// insert into member 
		// values(?, ?, ?, ?)
		// """
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hMap.get("id"));
			pstmt.setString(2, hMap.get("pw"));
			pstmt.setString(3, hMap.get("name"));
			pstmt.setString(4, hMap.get("gender"));
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("DB회원 완료");
				return true;
			} else {
				System.out.println("실페");
			}
			
		} catch(Exception e) {
			System.out.println("join 예외 발생");
			e.printStackTrace();
		}
		return false;
	}

	public void close() {
		JdbcUtil.dbClose(rs, pstmt, con);
	}

	public boolean login(HashMap<String, String> hMap) {
		String sql = "select * from member where id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hMap.get("id"));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// id 존재
				if(rs.getString("pw").equals(hMap.get("pw"))) {
					// pw 일지
//					hMap.put("id", rs.getString(1)); // 첫번쨰 컬럼
					
					return true;
				}
			} else {
				// id 부재
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<String> getMemberList() {
		ArrayList<String> mList = null;
		String sql = "SELECT ID FROM MEMBER";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			mList = new ArrayList<String>();
			while(rs.next()) {
				mList.add(rs.getString("id"));
			}
			
			return mList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // fail
	}

	public HashMap<String, String> getMemberInfo(String id) {	
		HashMap<String, String> mInfo = new HashMap<>();
		String sql = "SELECT * FROM MEMBER WHERE ID = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mInfo.put("userId", rs.getString("id"));
				mInfo.put("userPw", rs.getString("pw"));
				mInfo.put("userName", rs.getString("name"));
				mInfo.put("gender", rs.getString("gender"));
			}
			
			return mInfo;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void memberDelete(String id) {
		String sql = "DELETE FROM MEMBER WHERE ID = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
