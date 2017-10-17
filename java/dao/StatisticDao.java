package dao;

import java.util.List;

import model.SelectedShare;
import model.ShareItem;;

public interface StatisticDao {
	public List<Integer> getShareCount(int uid,int year);
	
	public List<Integer> getAllShareCount(int year);
	
	public List<Integer> getPicCount(int uid, int year);
	
	public List<Integer> getUpvoteCount(int uid, int year);
	
	public List<Integer> getCommentCount(int uid, int year);
	
	public List<Integer> getAllPicCount(int year);
	
	public List<Integer> getActiveUserCount(int year);
	
	public List<Integer> getAllUpvoteCount(int year);
	
	public List<Integer> getAllCommentCount(int year);
	
}
