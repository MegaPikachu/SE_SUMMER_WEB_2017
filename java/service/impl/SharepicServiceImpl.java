package service.impl;

import java.io.File;

import dao.SharepicDao;
import dao.UserpicDao;
import model.SendPicture;
import model.Userpic;
import service.SharepicService;
import service.UserpicService;
import util.ByteUtil;

public class SharepicServiceImpl implements SharepicService {

	private SharepicDao sharepicDao;

	public SharepicDao getSharepicDao() {
		return sharepicDao;
	}

	public void setSharepicDao(SharepicDao sharepicDao) {
		this.sharepicDao = sharepicDao;
	}

	@Override
	public void save(byte[] pic, String sid, int idx) {
		SendPicture sendPicture = new SendPicture(sid, idx, pic);
		sharepicDao.save(sendPicture);
		
	}

	@Override
	public void update(byte[] pic, String sid, int idx) {
		SendPicture sendPicture = new SendPicture(sid, idx, pic);
		sharepicDao.update(sendPicture);		
	}

	@Override
	public byte[] getSharepic(String sid, int index) {
		SendPicture sendPicture = sharepicDao.getSharepic(sid, index);
		return sendPicture.getPic();
	}

}
