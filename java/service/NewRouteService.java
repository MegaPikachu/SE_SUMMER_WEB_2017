package service;

import java.io.File;

import model.Photo;
import model.UploadInfoStr;

public interface NewRouteService {

	public void save(UploadInfoStr uploadInfoStr);

	public String getRouteById(String sid);
	
	public Photo getPicPosition(String sid, int index);
}
