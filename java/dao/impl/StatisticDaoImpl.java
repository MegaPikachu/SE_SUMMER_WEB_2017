package dao.impl;

import java.util.ArrayList;
import java.util.List;

import model.MonthShareCount;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.StatisticDao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;


public class StatisticDaoImpl extends HibernateDaoSupport implements StatisticDao {
	private JdbcRowSet rowset;
	private String url;
	private String username;
	private String password;

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void conn() {
		try {
			RowSetFactory rowSetFactory = RowSetProvider.newFactory();
			rowset = rowSetFactory.createJdbcRowSet();
			rowset.setUrl(url);
			rowset.setUsername(username);
			rowset.setPassword(password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Integer> getShareCount(int uid, int year){
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < 12; ++i)
			result.add(0);
		try {
			conn();
			rowset.setCommand("{call singlemonthshare(?,?)}");
			rowset.setInt(1, uid);
			rowset.setInt(2, year);
			rowset.execute();
			rowset.beforeFirst();
			while (rowset.next()) {
				for (int i = 0; i < 12; i++){
					result.set(i, rowset.getInt(i+1));
				}
			}
			rowset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		/*
		Session session = getSession();
		SQLQuery sqlquery = session.createSQLQuery("call singlemonthshare(?,?)");
		sqlquery.setInteger(0, uid);
		sqlquery.setInteger(1, year);
		sqlquery.executeUpdate();
		@SuppressWarnings("unchecked")
		List<MonthShareCount> get_sharecount = (List<MonthShareCount>) getHibernateTemplate()
				.find("from MonthShareCount");
		List<Integer> sharecount = new ArrayList<Integer>();
			sharecount.add(get_sharecount.get(0).getCount1());
			sharecount.add(get_sharecount.get(0).getCount2());
			sharecount.add(get_sharecount.get(0).getCount3());
			sharecount.add(get_sharecount.get(0).getCount4());
			sharecount.add(get_sharecount.get(0).getCount5());
			sharecount.add(get_sharecount.get(0).getCount6());
			sharecount.add(get_sharecount.get(0).getCount7());
			sharecount.add(get_sharecount.get(0).getCount8());
			sharecount.add(get_sharecount.get(0).getCount9());
			sharecount.add(get_sharecount.get(0).getCount10());
			sharecount.add(get_sharecount.get(0).getCount11());
			sharecount.add(get_sharecount.get(0).getCount12());
		return sharecount;
		*/
	}
	
	public List<Integer> getPicCount(int uid, int year){
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < 12; ++i)
			result.add(0);
		try {
			conn();
			rowset.setCommand("{call singlemonthpic(?,?)}");
			rowset.setInt(1, uid);
			rowset.setInt(2, year);
			rowset.execute();
			rowset.beforeFirst();
			while (rowset.next()) {
				for (int i = 0; i < 12; i++){
					result.set(i, rowset.getInt(i+1));
				}
			}
			rowset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		/*
		Session session = getSession();
		SQLQuery sqlquery = session.createSQLQuery("call singlemonthpic(?,?)");
		sqlquery.setInteger(0, uid);
		sqlquery.setInteger(1, year);
		sqlquery.executeUpdate();
		@SuppressWarnings("unchecked")
		List<MonthShareCount> get_sharecount = (List<MonthShareCount>) getHibernateTemplate()
				.find("from MonthShareCount");
		List<Integer> sharecount = new ArrayList<Integer>();
			sharecount.add(get_sharecount.get(0).getCount1());
			sharecount.add(get_sharecount.get(0).getCount2());
			sharecount.add(get_sharecount.get(0).getCount3());
			sharecount.add(get_sharecount.get(0).getCount4());
			sharecount.add(get_sharecount.get(0).getCount5());
			sharecount.add(get_sharecount.get(0).getCount6());
			sharecount.add(get_sharecount.get(0).getCount7());
			sharecount.add(get_sharecount.get(0).getCount8());
			sharecount.add(get_sharecount.get(0).getCount9());
			sharecount.add(get_sharecount.get(0).getCount10());
			sharecount.add(get_sharecount.get(0).getCount11());
			sharecount.add(get_sharecount.get(0).getCount12());
		return sharecount;
		*/
	}
	
	public List<Integer> getUpvoteCount(int uid, int year){
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < 12; ++i)
			result.add(0);
		try {
			conn();
			rowset.setCommand("{call singlemonthupvote(?,?)}");
			rowset.setInt(1, uid);
			rowset.setInt(2, year);
			rowset.execute();
			rowset.beforeFirst();
			while (rowset.next()) {
				for (int i = 0; i < 12; i++){
					result.set(i, rowset.getInt(i+1));
				}
			}
			rowset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		/*
		Session session = getSession();
		SQLQuery sqlquery = session.createSQLQuery("call singlemonthupvote(?,?)");
		sqlquery.setInteger(0, uid);
		sqlquery.setInteger(1, year);
		sqlquery.executeUpdate();
		@SuppressWarnings("unchecked")
		List<MonthShareCount> get_sharecount = (List<MonthShareCount>) getHibernateTemplate()
				.find("from MonthShareCount");
		List<Integer> sharecount = new ArrayList<Integer>();
			sharecount.add(get_sharecount.get(0).getCount1());
			sharecount.add(get_sharecount.get(0).getCount2());
			sharecount.add(get_sharecount.get(0).getCount3());
			sharecount.add(get_sharecount.get(0).getCount4());
			sharecount.add(get_sharecount.get(0).getCount5());
			sharecount.add(get_sharecount.get(0).getCount6());
			sharecount.add(get_sharecount.get(0).getCount7());
			sharecount.add(get_sharecount.get(0).getCount8());
			sharecount.add(get_sharecount.get(0).getCount9());
			sharecount.add(get_sharecount.get(0).getCount10());
			sharecount.add(get_sharecount.get(0).getCount11());
			sharecount.add(get_sharecount.get(0).getCount12());
		return sharecount;
		*/
	}
	
	public List<Integer> getCommentCount(int uid, int year){
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < 12; ++i)
			result.add(0);
		try {
			conn();
			rowset.setCommand("{call singlemonthcomment(?,?)}");
			rowset.setInt(1, uid);
			rowset.setInt(2, year);
			rowset.execute();
			rowset.beforeFirst();
			while (rowset.next()) {
				for (int i = 0; i < 12; i++){
					result.set(i, rowset.getInt(i+1));
				}
			}
			rowset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		/*
		Session session = getSession();
		SQLQuery sqlquery = session.createSQLQuery("call singlemonthcomment(?,?)");
		sqlquery.setInteger(0, uid);
		sqlquery.setInteger(1, year);
		sqlquery.executeUpdate();
		@SuppressWarnings("unchecked")
		List<MonthShareCount> get_sharecount = (List<MonthShareCount>) getHibernateTemplate()
				.find("from MonthShareCount");
		List<Integer> sharecount = new ArrayList<Integer>();
			sharecount.add(get_sharecount.get(0).getCount1());
			sharecount.add(get_sharecount.get(0).getCount2());
			sharecount.add(get_sharecount.get(0).getCount3());
			sharecount.add(get_sharecount.get(0).getCount4());
			sharecount.add(get_sharecount.get(0).getCount5());
			sharecount.add(get_sharecount.get(0).getCount6());
			sharecount.add(get_sharecount.get(0).getCount7());
			sharecount.add(get_sharecount.get(0).getCount8());
			sharecount.add(get_sharecount.get(0).getCount9());
			sharecount.add(get_sharecount.get(0).getCount10());
			sharecount.add(get_sharecount.get(0).getCount11());
			sharecount.add(get_sharecount.get(0).getCount12());
		return sharecount;
		*/
	}
	
	public List<Integer> getAllPicCount(int year){
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < 12; ++i)
			result.add(0);
		try {
			conn();
			rowset.setCommand("{call allmonthpic(?)}");
			rowset.setInt(1, year);
			rowset.execute();
			rowset.beforeFirst();
			while (rowset.next()) {
				for (int i = 0; i < 12; i++){
					result.set(i, rowset.getInt(i+1));
				}
			}
			rowset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	public List<Integer> getAllShareCount(int year){
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < 12; ++i)
			result.add(0);
		try {
			conn();
			rowset.setCommand("{call allmonthshare(?)}");
			rowset.setInt(1, year);
			rowset.execute();
			rowset.beforeFirst();
			while (rowset.next()) {
				for (int i = 0; i < 12; i++){
					result.set(i, rowset.getInt(i+1));
				}
			}
			rowset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Integer> getActiveUserCount(int year){
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < 12; ++i)
			result.add(0);
		try {
			conn();
			rowset.setCommand("{call monthactiveuser(?)}");
			rowset.setInt(1, year);
			rowset.execute();
			rowset.beforeFirst();
			while (rowset.next()) {
				for (int i = 0; i < 12; i++){
					result.set(i, rowset.getInt(i+1));
				}
			}
			rowset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Integer> getAllUpvoteCount(int year){
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < 12; ++i)
			result.add(0);
		try {
			conn();
			rowset.setCommand("{call allmonthupvote(?)}");
			rowset.setInt(1, year);
			rowset.execute();
			rowset.beforeFirst();
			while (rowset.next()) {
				for (int i = 0; i < 12; i++){
					result.set(i, rowset.getInt(i+1));
				}
			}
			rowset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Integer> getAllCommentCount(int year){
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < 12; ++i)
			result.add(0);
		try {
			conn();
			rowset.setCommand("{call allmonthcomment(?)}");
			rowset.setInt(1, year);
			rowset.execute();
			rowset.beforeFirst();
			while (rowset.next()) {
				for (int i = 0; i < 12; i++){
					result.set(i, rowset.getInt(i+1));
				}
			}
			rowset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
