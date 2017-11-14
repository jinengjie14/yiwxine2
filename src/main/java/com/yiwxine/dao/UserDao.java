package com.yiwxine.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yiwxine.dao.BaseDao;
import com.yiwxine.domain.User;
import com.yiwxine.util.UserFrom;


@Component
@Transactional
public class UserDao extends BaseDao{

	public void save(User user){
		log.debug("saving User instance");
		try {
			getSession().save(user);
			log.debug("save successful");
		} catch (RuntimeException re) {
		    log.error("save failed",re);	
		  throw re;
		
		}
	}
	
	public User findById(String id){
		
		try {
			User user = getSession().get(User.class, id);
			return user;
		} catch (RuntimeException re) {
			throw re;
		}
		
	}
	
	public void merge(User user){
		log.debug("merge User user");
		try {
			getSession().merge(user);
			log.debug("merge successful");
		} catch (RuntimeException re) {
			log.error("merge failed",re);
			throw re;
		}
	}
	
	public List<User> findByUser(UserFrom userFrom){
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Property.forName("account").eq(userFrom.getAccount()));
		dc.add(Property.forName("password").eq(userFrom.getPasswd()));
		Criteria criteria = dc.getExecutableCriteria(getSession());
		return criteria.list();
	}
	
	public User findByAccount(String account) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Property.forName("account").eq(account));
		Criteria c = dc.getExecutableCriteria(getSession());
		List list = c.list();
		if (null != list && list.size() > 0) {
			return (User)list.get(0);
		} else {
			return null;
		}
	}
	
}
