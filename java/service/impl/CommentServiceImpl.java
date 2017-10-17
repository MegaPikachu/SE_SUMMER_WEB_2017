package service.impl;

import java.util.List;

import dao.CommentDao;
import model.Comment;
import service.CommentService;

public class CommentServiceImpl implements CommentService {
	
	private CommentDao commentDao;
	
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Override
	public void save(Comment comment) {
		commentDao.save(comment);
	}

	@Override
	public void delete(int cid) {
		Comment comment = commentDao.getCommentByCid(cid);
		commentDao.delete(comment);
	}

	@Override
	public List<Comment> getCommentBySid(String sid) {
		return commentDao.getCommentBySid(sid);
	}

}
