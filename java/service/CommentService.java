package service;

import java.util.List;

import model.Comment;

public interface CommentService {

	public void save(Comment comment);

	public void delete(int cid);

	public List<Comment> getCommentBySid(String sid);
	
}
