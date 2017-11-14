package com.yiwxine.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Note {

	@Id
	private String id;                     //笔记ID
	private String title;                  //笔记标题
	private String content;                //笔记内容
	private Timestamp pubtime;             //发布时间
	
	public Note(){
		
	}
	
	public Note(String id,String title,String content,Timestamp pubtime){
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.pubtime = pubtime;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getPubtime() {
		return pubtime;
	}
	public void setPubtime(Timestamp pubtime) {
		this.pubtime = pubtime;
	}
}

