package com.employee.helper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CommonDataHelper {

	public void getCommonData(Integer page, Integer size, Map<String, ?> searchResult, Map<String, Object> response,
			List<?> lists) {

		response.put("lists", lists);
		response.put("currentPage", searchResult.get("currentPage"));
		response.put("nextPage", searchResult.get("nextPage"));
		response.put("previousPage", searchResult.get("previousPage"));
		response.put("size", searchResult.get("size"));
		response.put("total", searchResult.get("total"));
	}
	
	public void setPageSize(Integer page, Integer size) {
		if (page < 0 || size < 0) {
			page = 0;
			size = 5;
		} else if (size >= 20) {
			size = 20;
		}
	}

}
