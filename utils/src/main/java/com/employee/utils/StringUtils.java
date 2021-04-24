package com.employee.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.web.util.HtmlUtils;

public class StringUtils {

	public static boolean isNotEmpty(String str) {
		return Objects.nonNull(str) && str.trim().length() > 0;
	}

	public static boolean nonNull(Object boj) {
		return Objects.nonNull(boj);
	}

	public static boolean isNotEmpty(Integer integer) {
		return Objects.nonNull(integer) && integer > 0;
	}

	public static boolean isEmpty(String str) {
		return !isNotEmpty(str);
	}

	public static boolean isEmpty(Integer integer) {
		return !isNotEmpty(integer);
	}

	public static boolean isEmptyArr(Set<?> strArr) {
		return strArr.size() == 0;
	}

	public static boolean isNumericString(String code) {
		return code.matches("[0-9]+");
	}

	public static boolean isAnyEmpty(String... strings) {
		return Arrays.stream(strings).anyMatch(StringUtils::isEmpty);
	}

	public static boolean isAllNotEmpty(String... strings) {
		return Arrays.stream(strings).noneMatch(StringUtils::isEmpty);
	}

	public static String joinWithDelimiter(String delimiter, String... values) {

		List<String> elements = Arrays.asList(values);

		StringBuilder sb = new StringBuilder("");
		elements.forEach(s -> {
			String str = StringUtils.isNotEmpty(s) ? s.trim() : "";

			if (sb.length() > 0) {
				sb.append(delimiter);
			}
			sb.append(str);
		});
		return sb.toString();
	}

	public static String booleanToStr(Boolean bol) {
		return String.valueOf(bol);
	}

	public static boolean isNotEmpty(Object obj) {
		return Objects.nonNull(obj);
	}

	public static String trim(String str) {
		return str.trim();
	}

	public static boolean isHtml(String input) {
		boolean isHtml = false;
		if (isNotEmpty(input)) {
			if (!input.equals(HtmlUtils.htmlEscape(input))) {
				isHtml = true;
			}
		}
		return isHtml;
	}

}
