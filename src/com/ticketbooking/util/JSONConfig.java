package com.ticketbooking.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.ticketbooking.business.core.constant.Constant;

public class JSONConfig {
	
	public static JsonConfig jsonConfig = new JsonConfig();
	
	public static JsonConfig getInstance() {
		jsonConfig.registerJsonValueProcessor(Date.class,
			new JsonValueProcessor() {
				private SimpleDateFormat sd = new SimpleDateFormat(Constant.DATE_FORMAT);
				public Object processObjectValue(String key,
						Object value, JsonConfig jsonConfig) {
					return value == null ? "" : sd.format(value);
				}
				public Object processArrayValue(Object value,
						JsonConfig jsonConfig) {
					return null;
				}
		});
		return jsonConfig;
	}
}
