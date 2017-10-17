package service.impl;

import java.util.List;

import dao.AdminDao;
import dao.StatisticDao;
import model.Admin;
import service.StatisticService;;

public class StatisticServiceImpl implements StatisticService {

	private StatisticDao statisticDao;

	public StatisticDao getStatisticDao() {
		return statisticDao;
	}

	public void setStatisticDao(StatisticDao statisticDao) {
		this.statisticDao = statisticDao;
	}

	public List<Integer> personalShareCount(int uid, int year) {

		return statisticDao.getShareCount(uid, year);
	}
	
	
	public List<Integer> personalPicCount(int uid, int year) {

		return statisticDao.getPicCount(uid, year);
	}
	
	public List<Integer> personalUpvoteCount(int uid, int year) {
		return statisticDao.getUpvoteCount(uid, year);
	}

	public List<Integer> personalCommentCount(int uid, int year) {
		return statisticDao.getCommentCount(uid, year);
	}
	
	public List<Integer> allShareCount(int year) {
		return statisticDao.getAllShareCount(year);
	}
	
	public List<Integer> allPicCount(int year) {
		return statisticDao.getAllPicCount(year);
	}
	
	public List<Integer> allUpvoteCount(int year) {
		return statisticDao.getAllUpvoteCount(year);
	}

	public List<Integer> allCommentCount(int year) {
		return statisticDao.getAllCommentCount(year);
	}
	
	public List<Integer> activeUserCount(int year) {
		return statisticDao.getActiveUserCount(year);
	}
	
}
