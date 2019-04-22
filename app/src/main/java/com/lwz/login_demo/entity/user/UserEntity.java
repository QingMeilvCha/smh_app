/**
 * @Title UserEntity.java
 * @Package com.user.model
 * @Description 
 * @author zhouyuhang
 * @date 2018-12-12 16:28:51
 * @version : V1.0
 */

package com.lwz.login_demo.entity.user;

import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;

import lombok.Data;

/**
 * @ClassName UserEntity
 * @Description Service
 * @author zhouyuhang
 * @date 2018-12-12 16:28:51
 */
@Data
public class UserEntity implements Serializable, Entity{

	/**
	 * @Fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer userId;

	/**
	 * 
	 */
	private String userName;

	/**
	 * 
	 */
	private String sex;

	/**
	 * 
	 */
	private Integer age;

	private String password;

	private String confirmPassword;
	/**
	 * 
	 */
	private String email;

	/**
	 * 
	 */
	private String phoneNumber;

	private Integer mark;

	//	public void CheckUserEntityParam() throws Exception {
//		if(userName.trim()==null){
//			throw new Exception("用户名不能为空！");
//		}
//		if(password.trim()==null){
//			throw new Exception("密码不能为空！");
//		}
//		if(!ValidationUtil.checkPhone(phoneNumber.trim())){
//			throw new Exception("手机号码格式不正确！");
//		}
//		if(!ValidationUtil.checkEmail(email.trim())){
//			throw new Exception("邮箱格式不正确！");
//		}
//		if(age<=0){
//			throw new Exception("年龄不科学！");
//		}
//		if(!confirmPassword.trim().equals(password.trim())){
//			throw new Exception("前后密码不一致！");
//		}
//	}
}