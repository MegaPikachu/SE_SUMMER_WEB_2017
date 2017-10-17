package dao.impl;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import dao.SharepicDao;
import model.SendPicture;

public class SharepicDaoImpl implements SharepicDao {

	private MongoTemplate mongoTemplate;

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void save(SendPicture sendPicture) {
		mongoTemplate.save(sendPicture);
	}

	@Override
	public void delete(SendPicture sendPicture) {
		mongoTemplate.remove(sendPicture);
	}

	@Override
	public void update(SendPicture sendPicture) {
		Query query = new Query();
		query.addCriteria(Criteria.where("sid").is(sendPicture.getSid()));
		query.addCriteria(Criteria.where("idx").is(sendPicture.getIdx()));
		Update update = Update.update("pic", sendPicture.getPic());
		mongoTemplate.updateFirst(query, update, SendPicture.class);
	}

	@Override
	public SendPicture getSharepic(String sid, int index) {
		Query query = new Query();
		query.addCriteria(Criteria.where("sid").is(sid));
		query.addCriteria(Criteria.where("idx").is(index));
		SendPicture picClass = mongoTemplate.findOne(query, SendPicture.class);
		return picClass;
	}

}
