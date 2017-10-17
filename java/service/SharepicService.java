package service;

import java.io.File;

import model.SendPicture;

public interface SharepicService {

	public void save(byte[] pic, String sid, int idx);
	
	public void update(byte[] pic, String sid, int idx);
	
	public byte[] getSharepic(String sid, int index);
	

}
