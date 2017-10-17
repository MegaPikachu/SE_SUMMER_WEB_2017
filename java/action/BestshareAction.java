package action;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;
import model.ApplyInfo;
import model.ShareItem;
import net.sf.json.JSONArray;
import restful.UserRestful.SendMsg;
import service.ShareItemService;
import util.ByteUtil;

public class BestshareAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private ShareItemService shareItemService;
	private String sid;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public ShareItemService getShareItemService() {
		return shareItemService;
	}

	public void setShareItemService(ShareItemService shareItemService) {
		this.shareItemService = shareItemService;
	}

	
	public void getTopTen(){    
		List<ShareItem> shareItems = shareItemService.getTopNumber(3);
		try {
			JSONArray jsonArray = JSONArray.fromObject(shareItems);
			response().setCharacterEncoding("utf-8");
			response().getWriter().println(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getBest(){    
		ShareItem shareItem = shareItemService.getBest();
		request().setAttribute("bestShareItem", shareItem);
		return "success";
	}
	
	public String changeBest(){  
		shareItemService.changeBest(sid);
		System.out.println("saveUserPicture");
		 
		String msg="failed";
		JPushClient jpushClient = new JPushClient("8f67430666436fc867d53b6d",
				 "8a89bc898d108d3e7aacdd6c", null, ClientConfig.getInstance());

		PushPayload payload = buildPushObject_android_tag_alertWithTitle(sid);
		try {
			PushResult result = jpushClient.sendPush(payload);
			System.out.println("Got result - " + result);
			msg = "success";
		} catch (APIConnectionException e) {
			System.out.println("Connection error, should retry later: "+e.getMessage());
			msg="Connection error";
		} catch (APIRequestException e) {
			System.out.println("HTTP Status: " + e.getStatus());
			System.out.println("Error Code: " + e.getErrorCode());
			System.out.println("Error Message: " + e.getErrorMessage());
			msg="Request error";
		}
		return "success";
	}
	
	public static PushPayload buildPushObject_android_tag_alertWithTitle(String sid) {
		 return PushPayload.newBuilder()
				 .setPlatform(Platform.android())
				 .setAudience(Audience.all())
				 .setNotification(Notification.newBuilder()
						 .addPlatformNotification(
							 AndroidNotification.newBuilder()
							 .setAlert("点击查看")
               			 .setTitle("最佳分享")
               			 .addExtra("sid",sid)
               			 .build())
               		 .build())
                .build();
	 }

}
