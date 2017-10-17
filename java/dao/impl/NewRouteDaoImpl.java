package dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import dao.NewRouteDao;
import dao.SharepicDao;
import model.Photo;
import model.SendPicture;
import model.UploadInfoStr;
import model.Userpic;

public class NewRouteDaoImpl implements NewRouteDao {

	private MongoTemplate mongoTemplate;

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void save(UploadInfoStr uploadInfoStr) {
		mongoTemplate.save(uploadInfoStr);
	}

	@Override
	public void delete(UploadInfoStr uploadInfoStr) {
		mongoTemplate.remove(uploadInfoStr);
	}

	
	@Override
	public UploadInfoStr getUploadInfoStr(String sid) {
		return mongoTemplate.findOne(Query.query(Criteria.where("traceid").is(sid)), UploadInfoStr.class);
	}
	
	public Photo getPicPosition(String sid, int index) {
		UploadInfoStr uploadInfoStr = mongoTemplate.findOne(Query.query(Criteria.where("traceid").is(sid)), UploadInfoStr.class);
		List<Photo> photos = uploadInfoStr.getPhotos();
		return photos.get(index);
	}

}
