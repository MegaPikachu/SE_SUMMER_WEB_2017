package dao;

import model.Photo;
import model.UploadInfoStr;

public interface NewRouteDao {

	public void save(UploadInfoStr uploadInfoStr);

	public void delete(UploadInfoStr uploadInfoStr);

	public UploadInfoStr getUploadInfoStr(String sid);
	
	public Photo getPicPosition(String sid, int index);

}
