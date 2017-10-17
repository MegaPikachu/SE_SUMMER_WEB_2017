package restful;

import java.io.File;
import java.nio.file.FileStore;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.fileupload.FileItemHeaders;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.mongodb.util.Base64Codec;
import com.sun.jersey.core.util.Base64;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import dao.UserDao;
import model.Contacter;
import model.Friend;
import model.User;
import model.Userpic;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.ContacterService;
import service.UserService;
import service.UserpicService;
import util.ByteUtil;
import util.SpringContextUtil;

 
@Path("/user/")
public class UserRestful 
{

	private UserService userService=(UserService) SpringContextUtil.getBean("userService");
	private UserpicService userpicService = (UserpicService) SpringContextUtil.getBean("userpicService");

	
	@GET
	@Produces("text/html")
	public Response getStartingPage()
	{
		String output = "<h1>No Zuo No Die!<h1>";
		return Response.status(200).entity(output).build();
	}
	
	 @GET
     @Path("/test")
	 @Produces("text/html")
     public String test(){
		 System.out.println("test");
		 return "success";
     }	
	 
	 @POST
     @Path("/login")
	 @Produces("text/html")
     public String login(User user){
		 System.out.println("login");
		 System.out.println(user.getUsername());
		 System.out.println(user.getPassword());
		 return userService.login(user);
     }
	 
	 @POST
     @Path("/signup")
	 @Produces("text/html")
     public String signup(User user){
		 System.out.println("signup");
		 System.out.println(user.getUsername());
		 System.out.println(user.getPassword());
		 System.out.println(user.getPhone());
		 System.out.println(user.getEmail());
		 return userService.addUser(user);
     }
	 
	 @GET
     @Path("/json")
	 @Produces(MediaType.APPLICATION_JSON)
     public List<Friend> json(){
		 System.out.println("testjson");
		 Friend friend1 = new Friend(3,"qwer");
		 Friend friend2 = new Friend(4,"asdf");
		 List<Friend> result_friends = new ArrayList<Friend>();
		 result_friends.add(friend1);
		 result_friends.add(friend2);
		 return result_friends;  
     }	
	 
	 @POST
     @Path("/getUserByUid/{uid}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public User getUserByUid(@PathParam("uid") int uid){
		 System.out.println("getUserByUid");
		 System.out.println(uid);
		 User theUser = userService.getUserByUid(uid);
		 theUser.setPassword("");
		 return theUser;
	 }
	 
	 @POST
     @Path("/updateUser")
	 @Consumes("application/x-www-form-urlencoded")
	 @Produces("text/html")
     public String updateUser(@FormParam("uid")String uid,@FormParam("name")String name, @FormParam("email")String email){
		 System.out.println("updateUser");
		 System.out.println(uid);
		 System.out.println(name);
		 System.out.println(email);
		 int id = Integer.parseInt(uid);
		 User user = userService.getUserByUid(id);
		 user.setUsername(name);
		 user.setEmail(email);
		 return userService.updateUser(user);
	 }
	 
	 @POST
     @Path("/changePassword")
	 @Consumes("application/x-www-form-urlencoded")
	 @Produces("text/html")
     public String changePassword(@FormParam("uid")int uid,@FormParam("opwd")String opwd, @FormParam("npwd")String npwd){
		 System.out.println("changePassword");
		 System.out.println(uid);
		 System.out.println(opwd);
		 System.out.println(npwd);
		 User oldUser = userService.getUserByUid(uid);
		 if (oldUser.getPassword().equals(opwd)){
			 oldUser.setPassword(npwd);
			 userService.updateUser(oldUser);
			 return "success";
		 }
		 return "failed";
	 }
	 
	 @POST
     @Path("/changePhone")
	 @Consumes("application/x-www-form-urlencoded")
	 @Produces("text/html")
     public String changePhone(@FormParam("uid")int uid, @FormParam("phone")String phone){
		 System.out.println("changePhone");
		 System.out.println(uid);
		 System.out.println(phone);
		 User user = userService.getUserByUid(uid);
		 user.setPhone(phone);
		 userService.updateUser(user);
		 return "success";
	 }
	 
	 
	 @POST
     @Path("/getUserByUsername/{username}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public User getUserByUsername(@PathParam("username") String username){
		 System.out.println("getUserByUsername");
		 System.out.println(username);
		 User theUser = userService.getUserByUsername(username);
		 theUser.setPassword("");
		 System.out.println(theUser.getEmail());
		 return theUser;
	 }

	 @GET
     @Path("/getPicture/{uid}")
	 @Produces(MediaType.APPLICATION_OCTET_STREAM)
	 public byte[] getPicture(@Context HttpServletRequest request, @Context HttpServletResponse response, @PathParam("uid") int uid){
		 //int uid = 1;
	//	 System.out.println("getpicture");
	//	 System.out.println(uid);

		 
		 response.setContentType("application/octet-stream");
		 response.addHeader("Content-Disposition", "attachment; filename=\"acc.jpg\"");
		 Userpic userpic = userpicService.getUserPicById(uid);
		 if(userpic == null) {
			 File file = new File("C:/Users/Administrator/Desktop/bzbp-server/src/main/webapp/bzbp/image/default.jpg");
			 return ByteUtil.FileToString(file);
		 }else {
			 return userpic.getPic();
		 }
	 }
	 
	 @GET
     @Path("/getPictureById/{uid}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public JSONArray getPictureById(@PathParam("uid") int uid){
		 System.out.println("getPictureById");
		 System.out.println(uid);
		 JSONObject jsonObject = new JSONObject();
		 JSONArray jsonArray = new JSONArray();
		 //String string = new String(userpicService.getUserPicById(uid).getPic());
		byte[] bs = userpicService.getUserPicById(uid).getPic();
		 jsonObject.put("pic", bs);
		 jsonObject.put("uid", uid);
		 jsonObject.put("picname", "23333");
		 jsonArray.add(jsonObject);
		 
		 //System.out.println(string);
		 
		 return jsonArray;
		 //String string = new String();
		 //return userpicService.getUserPicById(uid);
		 
		
		 
	 }
	 
	 @GET
     @Path("/getPic/{uid}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public Userpic getPic(@Context HttpServletRequest request, @Context HttpServletResponse response, @PathParam("uid") int uid){
		 System.out.println("getPictureById");
		 System.out.println(uid);
		 JSONObject jsonObject = new JSONObject();
		 //String string = new String(userpicService.getUserPicById(uid).getPic());
		// jsonObject.put("pic", string);
		// jsonObject.put("uid", uid);
		// jsonObject.put("picname", null);
		 return userpicService.getUserPicById(uid);
		 //String string = new String();
		 //return userpicService.getUserPicById(uid);
		 
	 }
	 
	 @GET
     @Path("/testPicture/{uid}")
	 @Produces(MediaType.APPLICATION_OCTET_STREAM)
	 public byte[] testPicture(@Context HttpServletRequest request, @Context HttpServletResponse response, @PathParam("uid") int uid){
		 //int uid = 1;
		 System.out.println("getpicture");
		 System.out.println(uid);

		 
		 response.setContentType("application/octet-stream");
		 response.addHeader("Content-Disposition", "attachment; filename=\"acc.jpg\"");
		// return userpicService.getPicById(uid);
		 String str = "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";
		 return ByteUtil.StringToByte(str) ;
	 }
	 
	 
	 @POST
	 @Path("/saveUserPicture/{uid}")
	 @Consumes(MediaType.APPLICATION_OCTET_STREAM)
	 public void saveUserPicture(File userPic, @PathParam("uid") int uid){
		 //int uid = 1;
		 System.out.println("saveUserPicture");
		 System.out.println(uid);
		 byte[] userpic = ByteUtil.FileToString(userPic);
		 userpicService.userupdate(userpic, uid);
	 }
	 
	 public class SendMsg{
		 String title;
		 int uid;
		 public SendMsg(String title, int uid) {
			 this.title = title;
			 this.uid = uid;
		 }
		 
		 public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getUid() {
			return uid;
		}
		public void setUid(int uid) {
			this.uid = uid;
		}
	 }

}