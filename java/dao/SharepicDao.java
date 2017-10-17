package dao;

import model.SendPicture;

public interface SharepicDao {

	public void save(SendPicture sendPicture);

	public void delete(SendPicture sendPicture);

	public void update(SendPicture sendPicture);

	public SendPicture getSharepic(String sid, int index);

}
