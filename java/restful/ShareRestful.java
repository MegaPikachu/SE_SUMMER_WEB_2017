package restful;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.ShareItem;
import model.UploadInfoStr;
import model.User;
import model.Comment;
import model.Saitem;
import model.SelectedShare;
import net.sf.json.JSONObject;
import service.CommentService;
import service.RouteService;
import service.RoutepicService;
import service.ShareItemService;
import service.SharepicService;
import service.NewRouteService;
import util.SpringContextUtil;

@Path("/share")

public class ShareRestful {
	private ShareItemService shareItemService = (ShareItemService) SpringContextUtil.getBean("shareItemService");
	private SharepicService sharepicService = (SharepicService) SpringContextUtil.getBean("sharepicService");
	private RouteService routeService = (RouteService) SpringContextUtil.getBean("routeService");
	private RoutepicService routepicService = (RoutepicService) SpringContextUtil.getBean("routepicService");
	private CommentService commentService = (CommentService) SpringContextUtil.getBean("commentService");
	private NewRouteService newRouteService = (NewRouteService) SpringContextUtil.getBean("newRouteService");

	@GET
    @Path("/getAll/{uid}/{pagenum}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<Saitem> getAll(@PathParam("uid") int uid, @PathParam("pagenum") int pagenum){
		 System.out.println("getall");
		 List<SelectedShare> get_Shares = shareItemService.getAll(pagenum, 1);
		 List<ShareItem> shareItems = new ArrayList<ShareItem>();
		 for (int i = 0; i < get_Shares.size(); i++){
			 ShareItem shareItem = new ShareItem(get_Shares.get(i));
			 shareItems.add(shareItem);
		 }
		 List<Saitem> sList = new ArrayList<>();
		 for(ShareItem si: shareItems){
			 String str = shareItemService.searchUpvote(uid, si.getSid());
			 sList.add(new Saitem(si,str));
		 }
		 return sList;
	}
	 
	@GET
    @Path("/friendGetAll/{uid}/{pagenum}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<Saitem> friendGetAll(@PathParam("uid") int uid,@PathParam("pagenum") int pagenum){
		 System.out.println("friendGetAll");
		 List<SelectedShare> get_Shares = shareItemService.getFriendAll(pagenum, 1, uid);
		 List<ShareItem> shareItems = new ArrayList<ShareItem>();
		 for (int i = 0; i < get_Shares.size(); i++){
			 ShareItem shareItem = new ShareItem(get_Shares.get(i));
			 shareItems.add(shareItem);
		 }
		 List<Saitem> sList = new ArrayList<>();
		 for(ShareItem si: shareItems){
			 String str =shareItemService.searchUpvote(uid, si.getSid());
			 sList.add(new Saitem(si,str));
		 }
		 return sList;
		 
	 }
	
	@GET
    @Path("/myGetAll/{uid}/{pagenum}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<Saitem> myGetAll(@PathParam("uid") int uid,@PathParam("pagenum") int pagenum){
		 System.out.println("myGetAll");
		 List<SelectedShare> get_Shares = shareItemService.getMyAll(pagenum, 1, uid);
		 List<ShareItem> shareItems = new ArrayList<ShareItem>();
		 for (int i = 0; i < get_Shares.size(); i++){
			 ShareItem shareItem = new ShareItem(get_Shares.get(i));
			 shareItems.add(shareItem);
		 }
		 List<Saitem> sList = new ArrayList<>();
		 for(ShareItem si: shareItems){
			 String str =shareItemService.searchUpvote(uid, si.getSid());
			 sList.add(new Saitem(si,str));
		 }
		 return sList;
	 }
	
	@GET
    @Path("/privateGetAll/{uid}/{pagenum}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<Saitem> privateGetAll(@PathParam("uid") int uid,@PathParam("pagenum") int pagenum){
		 System.out.println("myGetAll");
		 List<SelectedShare> get_Shares = shareItemService.getPrivateAll(pagenum, 1, uid);
		 List<ShareItem> shareItems = new ArrayList<ShareItem>();
		 for (int i = 0; i < get_Shares.size(); i++){
			 ShareItem shareItem = new ShareItem(get_Shares.get(i));
			 shareItems.add(shareItem);
		 }
		 List<Saitem> sList = new ArrayList<>();
		 for(ShareItem si: shareItems){
			 String str =shareItemService.searchUpvote(uid, si.getSid());
			 sList.add(new Saitem(si,str));
		 }
		 return sList;
	 }
	
	 @POST
     @Path("/addShare")
	 @Produces("text/html")
     public String addShare(Saitem item){
		 
		 System.out.println("addShare");
		 System.out.println(item.getUid());
		 System.out.println(item.getStarttime());
		 System.out.println(item.getEndtime());
		 ShareItem shareItem = new ShareItem(item);
		 ShareItem shareItem2 = shareItemService.getShareItem(shareItem.getSid());
		 if (shareItem2 != null) {
			 if (shareItem2.getType()== -3) { // wechat
				shareItemService.delete(shareItem2);
				shareItemService.addShareItem(shareItem);
				return "wechat";
			}
			 return "failed";
		 }
		 shareItemService.addShareItem(shareItem);
		 return "success";
     }
	 
	 @GET
     @Path("/delShare")
	 @Produces("text/html")
     public String delShare(@PathParam("sid") String sid){
		 System.out.println("addShare");
		 ShareItem shareItem = shareItemService.getShareItem(sid);
		 shareItemService.delete(shareItem);
		 return "success";
	 }
	 
	 @POST
     @Path("/chgShare")
	 @Produces("text/html")
     public String chgShare(Saitem item){
		 System.out.println("chgShare");
		 System.out.println(item.getUid());
		 ShareItem shareItem = new ShareItem(item);
		 shareItemService.update(shareItem);
		 return "success";
     }
	 
	 
	 
	 @POST
	 @Path("/addRoute/{sid}")
	 @Produces("text/html")
	 public String addRoute(@PathParam("sid") String sid, String routedetail){
		 System.out.println("addRoute");
		 routeService.addShareRoute(sid, routedetail);
		 return "success";
	 }
	 
	 @GET
	 @Path("/getRoute/{sid}")
	 @Produces("text/html")
	 public String getRoute(@PathParam("sid") String sid){
		 System.out.println("getRoute");
		 return routeService.getShareRoute(sid);
	 }
	 
	 @GET
	 @Path("/getPics/{sid}")
	 @Consumes(MediaType.APPLICATION_OCTET_STREAM)
	 @Produces(MediaType.APPLICATION_OCTET_STREAM)
	 public List<byte[]> getPics(@PathParam("sid") String sid, byte[] pics){
		 System.out.println("getPics");
		 return routepicService.getPicsById(sid);
	 }
	 
	 @GET
	 @Path("/addPics/{sid}")
	 @Consumes(MediaType.APPLICATION_OCTET_STREAM)
	 @Produces("text/html")
	 public String addPics(@PathParam("sid") String sid, byte[] pics){
		 System.out.println("addPics");
		 routepicService.save(sid, pics);
		 return "success";
	 }
	 
	 @GET
	 @Path("/upvote/{sid}/{uid}")
	 @Produces("text/html")
	 public String upvote(@PathParam("sid") String sid,@PathParam("uid") int uid){
		 System.out.println("upvote");
		 return shareItemService.upvote(uid,sid);
	 }
	 
	 @GET
	 @Path("/cancelUpvote/{sid}/{uid}")
	 @Produces("text/html")
	 public String cancelUpvote(@PathParam("sid") String sid,@PathParam("uid") int uid){
		 System.out.println("cancelUpvote");
		 shareItemService.cancelUpvote(uid,sid);
		 return "success";
	 }
	 
	 @GET
	 @Path("/searchUpvote/{sid}/{uid}")
	 @Produces("text/html")
	 public String searchUpvote(@PathParam("sid") String sid,@PathParam("uid") int uid){
		 System.out.println("searchUpvote");
		 return shareItemService.searchUpvote(uid, sid);
	 }
	 
	 @GET
	 @Path("/browse/{sid}")
	 @Produces("text/html")
	 public String browse(@PathParam("sid") String sid){		 
		 return shareItemService.browse(sid);
	 }
	 
	 @GET
	 @Path("/allComment/{sid}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<Comment> allComment(@PathParam("sid") String sid ){
		 System.out.println("allComment");
		 List<Comment> comments = commentService.getCommentBySid(sid);
		 return comments;
	 }
	 
	 @POST
     @Path("/addComment")
	 @Produces("text/html")
     public String allComment(Comment comment){
		 System.out.println("addComment");
		 System.out.println(comment.getSid());
		 System.out.println(comment.getComment());
		 commentService.save(comment);
		 shareItemService.addComment(comment.getSid());
		 return "c"+String.valueOf(comment.getCid());
	 }
	 
	 @GET
     @Path("/delComment/{cid}/{sid}")
	 @Produces("text/html")
     public String delComment(@PathParam("cid")int cid, @PathParam("sid") String sid){
		 System.out.println("delComment");
		 System.out.println(cid+":"+sid);
		 commentService.delete(cid);
		 shareItemService.delComment(sid);
		 return "success";
	 }
	 
	
	 
	 @POST
	 @Path("/addPicFile/{sid}")
	 @Consumes(MediaType.APPLICATION_OCTET_STREAM)
	 @Produces("text/html")
	 public String addPicFile(@PathParam("sid") String sid, File pics){
		 System.out.println("addPicFile");
		 System.out.println(pics);
		 if (routeService.getShareRoutePic(sid) != null) {
			 return "failed";
		 }
		 routeService.addShareRoutePic(sid, pics);
		 return "success";
	 }
	 
	 @POST
	 @Path("/addTrace")
	 @Produces("text/html")
	 public String addTrace(UploadInfoStr str){
		 System.out.println("addTrace");
		 System.out.println(str.getTraceid());
		 newRouteService.save(str);
		 return "success";
	 }
	 
	 
	 @GET
	 @Path("/getPicFile/{sid}")
	 @Produces(MediaType.APPLICATION_OCTET_STREAM)
	 public File getPicFile(@PathParam("sid") String sid){
		 System.out.println("getPicFile");
		 System.out.println(sid);
		 return routeService.getShareRoutePic(sid);	 
	}	 	 
	 
	 @GET
     @Path("/getPhoto/{sid}/{idx}")
	 @Produces(MediaType.APPLICATION_OCTET_STREAM)
	 public byte[] getPicture(@Context HttpServletRequest request, @Context HttpServletResponse response, 
			 @PathParam("sid") String sid, @PathParam("idx") int idx){
		 System.out.println("getPhoto:"+sid+"  "+idx);
		 response.setContentType("application/octet-stream");
		 response.addHeader("Content-Disposition", "attachment; filename=\"acc.jpg\"");
		 return sharepicService.getSharepic(sid, idx);
	 }
	 
	 @POST
	 @Path("/savePhoto/{sid}/{idx}")
	 @Consumes(MediaType.APPLICATION_OCTET_STREAM)
	 @Produces("text/html")
	 public String savePhoto(@PathParam("sid") String sid, @PathParam("idx") int idx, byte[] pic){
		 System.out.println("savePhoto:"+sid+"  "+idx);
//		 if (sharepicService.getSharepic(sid, 0)!=null) {
//			 return "failed";
//		 }
		 sharepicService.save(pic, sid, idx);
		 return "success";
	 }
	 
}
