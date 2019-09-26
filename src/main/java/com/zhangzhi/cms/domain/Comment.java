/**
 * 
 */
package com.zhangzhi.cms.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangzhi
 *2019年9月21日
 */
public class Comment implements Serializable{
	private int id;
	private User userid;
	private Article article;
	
	private String content;
	private Date created;
	private int hits;
	public Comment() {
		super();
	}
	public Comment(int id, User userid, Article article, String content, Date created, int hits) {
		super();
		this.id = id;
		this.userid = userid;
		this.article = article;
		this.content = content;
		this.created = created;
		this.hits = hits;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUserid() {
		return userid;
	}
	public void setUserid(User userid) {
		this.userid = userid;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", userid=" + userid + ", article=" + article + ", content=" + content
				+ ", created=" + created + ", hits=" + hits + "]";
	}
	
}
