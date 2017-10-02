package com.krushweb.api.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.krushweb.api.exception.SchedulerNotFoundException;

public class BaseController {

	@ExceptionHandler(SchedulerNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody Map<String, Object> handleSchedulerNotFoundException(SchedulerNotFoundException snfex,
			HttpServletRequest request, HttpServletResponse resp) {
		LinkedHashMap<String, Object> result = new LinkedHashMap<>();

		result.put("error", true);

		result.put("message", snfex.getMessage());

/*
		if (null != tnfex.getNcm()) {

			result.put("errorType", "ncm");
			result.put("ncm", tnfex.getNcm());
			result.put("origem", tnfex.getOrigem());
			result.put("destino", tnfex.getDestino());

		}
*/

		return result;
	}

}
