package dao;

import java.io.File;
import java.util.List;

import com.mongodb.gridfs.GridFSDBFile;

import model.Admin;
import model.UploadInfoStr;
import net.sf.json.JSONObject;

public interface ShareRouteDao {

	public void saveRoute(String id, String routedetail);
	
	public void updateRoute(String id, String routedetail);
	
	public GridFSDBFile getRoute(String id);
	
	public void deleteRoute(String id);

	public void saveRoutePic(String id, File pic);
	
	public void updateRoutePic(String id, File pic);
	
	public GridFSDBFile getRoutePic(String id);
	
	public void deleteRoutePic(String id);
	
	public void saveTrace(UploadInfoStr str);
	
	public UploadInfoStr getTrace(String id);
}
