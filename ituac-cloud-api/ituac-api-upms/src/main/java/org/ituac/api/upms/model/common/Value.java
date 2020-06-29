package org.ituac.api.upms.model.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * 返回实体
 * @author Aroli
 *
 */
public class Value<T> implements Serializable{

	private static final String OPERATION_SUCCESS = "操作成功!";
	private static final String OPERATION_FAILURE = "操作失败!";

	/**
	 * 消息id
	 */
	private int code;
	/**
	 * 消息内容
	 */
	private String message;
	/**
	 * 时间戳：Date 类型
	 */
	private Date timestamp;
	/**
	 * 返回数据
	 */
	@JSONField(serialzeFeatures= {SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty})
	private T data;

	private Value(Builder<T> builder) {
		this.code = builder.code;
		this.message = builder.message;
		this.timestamp = builder.timestamp;
		this.data = builder.data;
	}

	public Value() {
	}

	public static Value<String> SUCCESS = getResponse(HttpStatus.OK.value(), OPERATION_SUCCESS, null);


	public static <T> Value<T> getSuccess(T data) {
		return getResponse(HttpStatus.OK.value(), OPERATION_SUCCESS, data);
	}

	public static <T> Value<T> getSuccess(String message, T data) {
		return getResponse(HttpStatus.OK.value(), message, data);
	}

	public static <T> Value<T> getSuccess(String message) {
		return getSuccess(message, null);
	}

	public static Value<String> getSuccess(int code) {
		return getSuccess(code, OPERATION_SUCCESS);
	}

	public static Value<String> getSuccess(int code, String message) {
		return getSuccess(code, message, null);
	}

	public static <T> Value<T> getSuccess(int code, T data) {
		return getResponse(code, OPERATION_SUCCESS, data);
	}

	public static <T> Value<T> getSuccess(int code, String message, T data) {
		return getResponse(code, message, data);
	}

	public static <T> Value<T> getFailure(String message, T data) {
		return getResponse(HttpStatus.BAD_REQUEST.value(), message, data);
	}

	public static <T> Value<T> getFailure(String message) {
		return getFailure(message, null);
	}

	public static Value<?> getFailure(int code, String message) {
		return getResponse(code, message, null);
	}

	public static <T> Value<T> getFailure(int code, String message, T data) {
		return getResponse(code, message, data);
	}

	public static <T> Value<T> getResponse(Boolean flag) {
		return flag ? getSuccess(OPERATION_SUCCESS) : getFailure(OPERATION_FAILURE);
	}

	public static <T> Value<T> getResponse(Boolean flag, String message) {
		return flag ? getSuccess(message) : getFailure(message);
	}

	public static <T> Value<T> getResponse(int code, String message, T data) {
		return new Builder<T>(code, new Date()).message(message).data(data).build();
	}

	public static class Builder<T> implements Serializable{
		private int code;
		private Date timestamp;
		private String message;
		private T data;

		public Builder(int code, Date timestamp) {
			this.code = code;
			this.timestamp = timestamp;
		}

		public Builder<T> message(String message) {
			this.message = message;
			return this;
		}

		@SuppressWarnings("unchecked")
		public Builder<T> data(T data) {
			this.data = data == null ? (T) new JSONObject() : data;
			return this;
		}

		public Value<T> build() {
			return new Value<>(this);
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

