package action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.mysql.jdbc.ReflectiveStatementInterceptorAdapter;
import com.sun.org.apache.bcel.internal.generic.ReturnaddressType;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.struts2.ServletActionContext;

import dao.ProfileDao;
import dao.UserDao;
import model.Comment;
import model.Contacter;
import model.Photo;
import model.ShareItem;
import model.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.UserService;
import service.UserpicService;
import service.CommentService;
import service.ContacterService;
import service.NewRouteService;
import service.RouteService;
import service.ShareItemService;
import service.SharepicService;

public class ShareAction extends BaseAction{
	
	private RouteService routeService;
	private CommentService commentService;
	private ShareItemService shareItemService;
	private SharepicService sharepicService;
	private String sid;
	private NewRouteService newRouteService;
	
	public RouteService getRouteService() {
		return routeService;
	}

	public void setRouteService(RouteService routeService) {
		this.routeService = routeService;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	public ShareItemService getShareItemService() {
		return shareItemService;
	}

	public void setShareItemService(ShareItemService shareItemService) {
		this.shareItemService = shareItemService;
	}
	
	public SharepicService getSharepicService() {
		return sharepicService;
	}

	public void setSharepicService(SharepicService sharepicService) {
		this.sharepicService = sharepicService;
	}
	
	public NewRouteService getNewRouteService() {
		return newRouteService;
	}

	public void setNewRouteService(NewRouteService newRouteService) {
		this.newRouteService = newRouteService;
	}

	public String getSid(){
		return sid;
	}

	public void setSid(String sid){
		this.sid = sid;
	}
	
	public void getMaxIndex() throws IOException{
		String id = request().getParameter("id");
		ShareItem shareItem = shareItemService.getShareItem(id);
		//int num = shareItem.getPicnum();
		//System.out.println(num);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.getWriter().write(Integer.toString(shareItem.getPicnum()));
	}
	
	public void getRoute() throws IOException {
		String id = request().getParameter("id");
		String route = newRouteService.getRouteById(id);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.getWriter().write(route);
	}
	
	public void getComment(){
		
		List<Comment> result = commentService.getCommentBySid(sid);
		try {
			JSONArray jsonArray = JSONArray.fromObject(result);
			response().setCharacterEncoding("utf-8");
			response().getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void getPic() throws IOException {
		String sid = request().getParameter("id");
		int index = Integer.parseInt(request().getParameter("index"));
		System.out.println(sid);
		System.out.println(index);
		byte[] pic = sharepicService.getSharepic(sid, index);
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream outputStream = response.getOutputStream();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment; filename=\"acc.jpg\"");
		outputStream.write(pic);
		outputStream.flush();
		outputStream.close();
	}

	public void getPosition() throws IOException {
		String id = request().getParameter("id");
		int index = Integer.parseInt(request().getParameter("index"));
		Photo photo = newRouteService.getPicPosition(id, index);
		JSONObject jsonObject = JSONObject.fromObject(photo);
		response().getWriter().println(jsonObject.toString());
	}

	

	
}
