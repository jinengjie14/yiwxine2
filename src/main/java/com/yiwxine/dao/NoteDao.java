package com.yiwxine.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yiwxine.domain.Note;
import com.yiwxine.util.MyPage;

@Component
@Transactional
public class NoteDao extends BaseDao{
	
	public void save(Note note){
		try {
		 log.debug("saving Notes intance");
			getSession().save(note);
		 log.debug("save successful");
		} catch (RuntimeException re) {
		 log.error("save failed",re);
			 throw re;
		}
	}

   public void delete(Note note){
	   try {
		log.debug("delete Notes intance");
		getSession().delete(note);
		log.debug("delete successful");
	} catch (RuntimeException re) {
	    log.error("save failed",re);
		throw re;
	}
   }
   
   public void merage(Note note){
	   try {
		log.debug("merage Ntes intance");
		getSession().merge(note);
		log.debug("merage successful");
	} catch (RuntimeException re) {
		log.error("meage failed",re);
		throw re;
	}
   }

   public Note findById(String id){
	Note note = (Note) getSession().get("com.yiwxine.domain.Note", id);
	return note;
  }
   
   public MyPage<Note> findAll(int page, int pagesize){
	   DetachedCriteria dc = DetachedCriteria.forClass(Note.class);
	   Criteria c = dc.getExecutableCriteria(getSession());
	   c.addOrder(Order.desc("pubtime"));    //按时间字段排序
	   List list = c.list();
	   try {
			if(pagesize <=0){
				pagesize = 20;
			}
			} catch (Exception e) {
		}
		return findPageByCriteria(dc, pagesize, page);
    }
}



