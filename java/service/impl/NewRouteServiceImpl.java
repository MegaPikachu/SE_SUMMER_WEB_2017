package service.impl;

import java.io.File;

import dao.NewRouteDao;
import dao.UserpicDao;
import model.Photo;
import model.UploadInfoStr;
import model.Userpic;
import service.NewRouteService;
import service.UserpicService;
import util.ByteUtil;

public class NewRouteServiceImpl implements NewRouteService {

	private NewRouteDao newRouteDao;

	public NewRouteDao getNewRouteDao() {
		return newRouteDao;
	}

	public void setNewRouteDao(NewRouteDao newRouteDao) {
		this.newRouteDao = newRouteDao;
	}

	@Override
	public void save(UploadInfoStr uploadInfoStr) {
		newRouteDao.save(uploadInfoStr);
		
	}

	@Override
	public String getRouteById(String sid) {
		UploadInfoStr result = newRouteDao.getUploadInfoStr(sid);
		return result.getPolylines();
	}

	public Photo getPicPosition(String sid, int index) {
		return newRouteDao.getPicPosition(sid, index);
	}

	

}
