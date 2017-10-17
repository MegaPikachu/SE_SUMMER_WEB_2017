package dao;

import java.util.List;

import model.Comment;

public interface CommentDao {
	public void save(Comment comment);

	public void delete(Comment comment);

	public Comment getCommentByCid(int cid);
	
	public List<Comment> getCommentBySid(String sid);

}
