package service.impl;

import java.io.File;

import dao.UserpicDao;
import model.Userpic;
import service.UserpicService;
import util.ByteUtil;

public class UserpicServiceImpl implements UserpicService {

	private UserpicDao userpicDao;

	public void setUserpicDao(UserpicDao userpicDao) {
		this.userpicDao = userpicDao;
	}

	@Override
	public void save(int uid, File file) {
		byte[] pic = ByteUtil.FileToString(file);
		Userpic userpic = new Userpic(uid, pic);
		userpicDao.save(userpic);
	}

	@Override
	public byte[] getPicById(int uid) {
		Userpic userpic = userpicDao.getUserpicById(uid);
		return userpic.getPic();
	}
	
	public Userpic getUserPicById(int uid){   
		return userpicDao.getUserpicById(uid);
	}

	
	@Override
	public void usersave(byte[] userpic, int uid) {
		Userpic pic = new Userpic(uid, userpic);
		userpicDao.save(pic);
	}
	
	@Override
	public void userupdate(byte[] userpic, int uid) {
		Userpic pic = new Userpic(uid, userpic);
		if (userpicDao.getUserpicById(uid) == null) {
			userpicDao.save(pic);
		}
		else {
			userpicDao.update(pic);
		}
		
	}

}
