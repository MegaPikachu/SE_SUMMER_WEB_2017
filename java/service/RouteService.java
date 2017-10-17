package service;

import java.io.File;

import model.UploadInfoStr;
import net.sf.json.JSONArray;

/**
 * 
 * 
 */
public interface RouteService {

	public String getShareRoute(String id);
	
	public void addShareRoute(String id, String routedetail);
	
	public File getShareRoutePic(String id);
	
	public void addShareRoutePic(String id, File pic);
	
	public String addTrace(UploadInfoStr str);
	
	public String getTrace(String sid);

}
