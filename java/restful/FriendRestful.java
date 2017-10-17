package restful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;
import model.ApplyInfo;
import model.Contacter;
import model.Friend;
import model.User;
import service.ContacterService;
import service.UserService;
import util.SpringContextUtil;

@Path("/friend")
public class FriendRestful 
{
	private ContacterService contacterService=(ContacterService) SpringContextUtil.getBean("contacterService");
	private UserService userService=(UserService) SpringContextUtil.getBean("userService");

	 
	 @POST
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
	 
	 @GET
     @Path("/getAll/{uid}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<Friend> getAll(@PathParam("uid") int uid){
		 //int uid = 1;
		 System.out.println("getall");
		 System.out.println(uid);
		 List<Contacter> contacters = contacterService.getContacterById(uid);
		 List<Friend> result_friends = new ArrayList<Friend>();
		 int size = contacters.size();
		 for (int i = 0; i < size; i++){
			 result_friends.add(new Friend(contacters.get(i)));
			 System.out.println(contacters.get(i).getRemark());
		 }
		 
		 return result_friends;
	 }
	 

	 
	 
	 @POST
     @Path("/applyFriend")
	 @Produces("text/html")
     public String applyFriend(ApplyInfo applyInfo){
		 String msg="failed";
		 System.out.println("applyfriend");
		 System.out.println(applyInfo.getUida());
		 System.out.println(applyInfo.getUidb());
		 
		 JPushClient jpushClient = new JPushClient("8f67430666436fc867d53b6d",
				 "8a89bc898d108d3e7aacdd6c", null, ClientConfig.getInstance());

		 // For push, all you need do is to build PushPayload object.
		 PushPayload payload = buildPushObject_android_tag_alertWithTitle(applyInfo);
		 try {
			 PushResult result = jpushClient.sendPush(payload);
			 System.out.println("Got result - " + result);
			 msg = "success";
		 } catch (APIConnectionException e) {
			 // Connection error, should retry later
			 System.out.println("Connection error, should retry later: "+e.getMessage());
			 msg="Connection error";
		 } catch (APIRequestException e) {
			 // Should review the error, and fix the request
			 System.out.println("HTTP Status: " + e.getStatus());
			 System.out.println("Error Code: " + e.getErrorCode());
			 System.out.println("Error Message: " + e.getErrorMessage());
			 msg="Request error";
		 }
		 return msg;
     }
	 
//	 @GET
//     @Path("/addFriend/{uid1}/{uid2}/{remark}")
//	 @Produces("text/html")
//     public String addFriend(@PathParam("uid1") int uid1, @PathParam("uid2") int uid2, 
//    		 @PathParam("remark")String remark){
//		 System.out.println("addfriend");
//		 System.out.println(uid1);
//		 System.out.println(uid2);
//		 return contacterService.addfriend(uid1, uid2, remark);
//	 }
	 
	 @GET
     @Path("/addFriend/{uid1}/{uid2}/{remark1}/{remark2}")
	 @Produces("text/html")
     public String addFriend(@PathParam("uid1") int uid1, @PathParam("uid2") int uid2, 
    		 @PathParam("remark1")String remark1, @PathParam("remark2")String remark2){
		 System.out.println("addfriend");
		 System.out.println(uid1);
		 System.out.println(uid2);
		 
		 String msg="failed";
		 JPushClient jpushClient = new JPushClient("8f67430666436fc867d53b6d",
				 "8a89bc898d108d3e7aacdd6c", null, ClientConfig.getInstance());
		 String alias = String.valueOf(uid1);
		 Friend friend = new Friend(uid2, remark1);
		 Gson gson = new Gson();
		 String msgContent = gson.toJson(friend);
		 try {
			 PushResult result = jpushClient.sendAndroidMessageWithAlias("ADDFRIEND", msgContent, alias);
			 System.out.println("Got result - " + result);
			 msg = "success";
		 } catch (APIConnectionException e) {
			 // Connection error, should retry later
			 System.out.println("Connection error, should retry later: "+e.getMessage());
			 msg="Connection error";
		 } catch (APIRequestException e) {
			 // Should review the error, and fix the request
			 System.out.println("HTTP Status: " + e.getStatus());
			 System.out.println("Error Code: " + e.getErrorCode());
			 System.out.println("Error Message: " + e.getErrorMessage());
			 msg="Request error";
		 }
		 return contacterService.addfriend(uid1, uid2, remark1, remark2);
	 }
	 
	 
	 @GET
     @Path("/deleteFriend/{uid1}/{uid2}")
	 @Produces("text/html")
     public String deleteFriend(@PathParam("uid1") int uid1, @PathParam("uid2") int uid2){
		 System.out.println("deleteFriend");
		 System.out.println(uid1);
		 System.out.println(uid2);
		 contacterService.delete(uid1, uid2);
		 return "success";
	 }
	 
	 @POST
     @Path("/changeRemark")
	 @Consumes("application/x-www-form-urlencoded")
	 @Produces("text/html")
     public String changeRemark(@FormParam("uid1")int uid1, @FormParam("uid2")int uid2,@FormParam("remark") String newRemark){
		 System.out.println("changeRemark");
		 System.out.println(uid1);
		 System.out.println(uid2);
		 Contacter contacter = contacterService.getContacterByIds(uid1, uid2);
		 contacter.setRemark(newRemark);
		 contacterService.update(contacter);
		 return "success";
	 }
	 
	 @GET
	 @Path("/search/{idname}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<User> search(@PathParam("idname") String idname){
		 //int uid = 1;
		 System.out.println("search");
		 System.out.println(idname);

		 List<User> users = userService.searchUser(idname);
		 return users;
	 }
	 
	 @GET
     @Path("/check/{uid1}/{uid2}")
	 @Produces("text/html")
     public String check(@PathParam("uid1") int uid1, @PathParam("uid2") int uid2){
		 System.out.println("check");
		 System.out.println(uid1);
		 System.out.println(uid2);
		 return contacterService.check(uid1, uid2);
	 }
	 
	 
	 public static PushPayload buildPushObject_android_tag_alertWithTitle(
			 ApplyInfo applyInfo) {
		 String alias = String.valueOf(applyInfo.getUidb());
		 String usera = String.valueOf(applyInfo.getUida());
		 return PushPayload.newBuilder()
				 .setPlatform(Platform.android())
				 .setAudience(Audience.alias(alias))
				 .setNotification(Notification.newBuilder()
						 .addPlatformNotification( 
							 AndroidNotification.newBuilder()
							 .setAlert(applyInfo.getUnamea()+"申请添加你为好友")
                			 .setTitle("好友申请")
                			 .addExtra("uida", applyInfo.getUida())
                			 .addExtra("unamea", applyInfo.getUnamea())
                			 .addExtra("uidb", applyInfo.getUidb())
                			 .addExtra("remarka", applyInfo.getRemarka())
                			 .addExtra("info", applyInfo.getInfo())
                			 .build())
                		 .build())
                 .build();
	 }
}