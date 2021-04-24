package com.employee.response;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class ResponseBuilder implements Response {

	private Object data;

	private Object error;

	private ResponseType type;

	public ResponseBuilder(ResponseType type) {
		this.type = type;
	}

	public static ResponseBuilder success(Object data) {
		ResponseBuilder res = new ResponseBuilder(ResponseType.DATA);
		res.data = data;
		return res;
	}

	public static ResponseBuilder error(Object error) {
		ResponseBuilder res = new ResponseBuilder(ResponseType.ERROR);
		res.error = error;
		return res;
	}

	@Override
	public JSONObject getJson() {

		Map<String, Object> maps = new HashMap<String, Object>();

		switch (this.type) {
		case DATA -> maps.put("data", data);
		case ERROR -> maps.put("error", error);
		}
		return new JSONObject(maps);
	}

}
