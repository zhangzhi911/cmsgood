/**
 * 
 */
package com.zhangzhi.cms.vo;

import com.zhangzhi.cms.domain.User;

/**
 * @author zhangzhi
 *2019年9月18日
 */
public class UserVo extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5651154525196375750L;
	private String repassword;

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((repassword == null) ? 0 : repassword.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVo other = (UserVo) obj;
		if (repassword == null) {
			if (other.repassword != null)
				return false;
		} else if (!repassword.equals(other.repassword))
			return false;
		return true;
	}
	
	
}
