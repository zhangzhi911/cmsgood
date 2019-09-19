package com.zhangzhi.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
/**
	 * 用户的类
	 */
public class User  implements Serializable{
    public User() {
		super();
	}

	/**
	 * 用户的类
	 */
	private static final long serialVersionUID = 5314401986115665971L;

	private Integer id;
	
	@Size(max = 10,min = 2,message = "长度必须在10-2之间")
	private String username;

	@Size(max = 10,min = 6,message = "长度必须在10-6之间")
    private String password;

    private String nickname;

    private Date birthday;

    private Integer gender;

    private Integer locked;

    private Date createTime;

    private Date updateTime;

    private String role;

    public User(Integer id, String username, String password, String nickname, Date birthday, Integer gender,
			Integer locked, Date createTime, Date updateTime, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.birthday = birthday;
		this.gender = gender;
		this.locked = locked;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.role = role;
	}

	public User(String username, String password, String nickname, Date birthday, Integer gender, Integer locked,
			Date createTime, Date updateTime, String role) {
		super();
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.birthday = birthday;
		this.gender = gender;
		this.locked = locked;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", birthday=" + birthday + ", gender=" + gender + ", locked=" + locked + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", role=" + role + "]";
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}