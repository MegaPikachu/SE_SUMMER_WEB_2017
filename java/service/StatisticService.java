package service;

import java.util.List;

import model.Admin;

public interface StatisticService {

	public List<Integer> personalShareCount(int uid,int year);
	
	public List<Integer> personalPicCount(int uid, int year);
	
	public List<Integer> personalUpvoteCount(int uid,int year);
	
	public List<Integer> personalCommentCount(int uid,int year);
	
	public List<Integer> allShareCount(int year);
	
	public List<Integer> allPicCount(int year);
	
	public List<Integer> allUpvoteCount(int year);
	
	public List<Integer> allCommentCount(int year);
	
	public List<Integer> activeUserCount(int year);

}
