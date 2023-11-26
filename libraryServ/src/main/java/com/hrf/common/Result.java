/**
 * 
 */
package com.hrf.common;

import java.io.Serializable;

import com.alibaba.fastjson2.JSONObject;

import lombok.Data;

/**
 * @author Administrator
 *
 */
@Data
public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int STATUS_OK = 202;
	public static final int STATUS_NOT_FOUND = 404;
	public static final int STATUS_SERVER_ERROR = 500;
	// result of response
	int code; 
	// ok or error message	;
	String msg="OK"; 
	 // JSON string of Object data;s
	Object data;  
	public static Result success(Object data) {
		Result result = new Result();
		result.data = data;
		result.msg = "OK";
		result.code = 200;
		return result;
	}
	
	public static Result error(Object data) {
		Result result = new Result();
		result.data = data;
		result.code= 500;
		result.msg = "failed to response in server";
		return result;		
	}
	
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
