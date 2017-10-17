package action;
import java.io.IOException;
import java.util.List;

import com.mchange.v2.ser.IndirectlySerialized;

import model.ShareItem;
import net.sf.json.JSONArray;
import service.StatisticService;

public class StatisticAction extends BaseAction{

	private int uid;
	private int year;
	private StatisticService statisticService;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public StatisticService getStatisticService() {
		return statisticService;
	}
	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}
	
	
	public String get(){
		return "success";
	}
	
	public void singleshare(){
		List<Integer> shareresult = statisticService.personalShareCount(uid, year);		
		try {			
			JSONArray jsonArray = JSONArray.fromObject(shareresult);
			response().setCharacterEncoding("utf-8");
			response().getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public void singlepic(){
		List<Integer> shareresult = statisticService.personalPicCount(uid, year);	
		try {			
			JSONArray jsonArray = JSONArray.fromObject(shareresult);
			response().setCharacterEncoding("utf-8");
			response().getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public void singleupvote(){
		List<Integer> shareresult = statisticService.personalUpvoteCount(uid, year);	
		try {			
			JSONArray jsonArray = JSONArray.fromObject(shareresult);
			response().setCharacterEncoding("utf-8");
			response().getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public void singlecomment(){
		List<Integer> shareresult = statisticService.personalCommentCount(uid, year);	
		try {			
			JSONArray jsonArray = JSONArray.fromObject(shareresult);
			response().setCharacterEncoding("utf-8");
			response().getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public void allshare(){
		List<Integer> shareresult = statisticService.allShareCount(year);		
		try {			
			JSONArray jsonArray = JSONArray.fromObject(shareresult);
			response().setCharacterEncoding("utf-8");
			response().getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	public void allpic(){
		List<Integer> shareresult = statisticService.allPicCount(year);		
		try {			
			JSONArray jsonArray = JSONArray.fromObject(shareresult);
			response().setCharacterEncoding("utf-8");
			response().getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public void allupvote(){
		List<Integer> shareresult = statisticService.allUpvoteCount(year);		
		try {			
			JSONArray jsonArray = JSONArray.fromObject(shareresult);
			response().setCharacterEncoding("utf-8");
			response().getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public void allcomment(){
		List<Integer> shareresult = statisticService.allCommentCount(year);		
		try {			
			JSONArray jsonArray = JSONArray.fromObject(shareresult);
			response().setCharacterEncoding("utf-8");
			response().getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public void activeuser(){
		List<Integer> shareresult = statisticService.activeUserCount(year);	
		try {			
			JSONArray jsonArray = JSONArray.fromObject(shareresult);
			response().setCharacterEncoding("utf-8");
			response().getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
}
