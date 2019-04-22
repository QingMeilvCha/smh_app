/**
 * @Title UserTaskEntity.java
 * @Package com.usertask.model
 * @Description 
 * @author zhouyuhang
 * @date 2018-12-12 16:37:05
 * @version : V1.0
 */

package com.lwz.login_demo.entity.user;


import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @ClassName UserTaskEntity
 * @Description Service
 * @author zhouyuhang
 * @date 2018-12-12 16:37:05
 */
@Data
public class UserTaskEntity implements Serializable, Entity{

	/**
	 * @Fields serialVersionUID
	 */

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	private String userTaskId;

	/**
	 *
	 */
	private String userId;


	/**
	 *
	 */
	private String pointDataId;

	/**
	 *
	 */
	private String lineDataId;

	/**
	 *
	 */
	private String resultId;

	/**
	 *
	 */
	private String taskName;

	/**
	 *
	 */
	private String taskType;

	/**
	 *
	 */
	private Date creatTime;

	private Integer mark;

}