package dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dao.CommentDao;
import model.Comment;

public class CommentDaoImpl extends HibernateDaoSupport implements CommentDao {

	@Override
	public void save(Comment comment) {
		getHibernateTemplate().save(comment);
	}

	@Override
	public void delete(Comment comment) {
		getHibernateTemplate().delete(comment);
	}
	
	@Override
	public Comment getCommentByCid(int cid) {
		@SuppressWarnings("unchecked")
		List<Comment> comments = (List<Comment>)getHibernateTemplate()
				.find("from Comment as c where c.cid=?",cid);
		return (comments.size()>0)? comments.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentBySid(String sid) {
		return (List<Comment>)getHibernateTemplate().find("from Comment as c where c.sid=?",sid);
	}

}
