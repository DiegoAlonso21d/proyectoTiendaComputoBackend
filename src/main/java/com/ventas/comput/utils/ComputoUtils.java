package com.ventas.comput.utils;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class ComputoUtils {

	private ComputoUtils() {
		
	}
	
	
	
	public static ResponseEntity<String> getResponseEntity  (String reponseMessage, HttpStatus httpStatus){
		return new ResponseEntity<String>("{\"messag\":\""+reponseMessage+"\"}", httpStatus);
	}
	
	
	public static String getUUID() {
		
		Date date=new Date();
		
		long time= date.getTime();
		
		return "FACTURA - "+time;
				
	}
	
	
	
	public static JSONArray getJsonArrayFromString(String data)throws JSONException{
		
		JSONArray jsonArray = new JSONArray(data);
		
		return jsonArray;
		
	}
	
	
	public static Map<String, Object> getMapFromJson(String data){
		// Verifica si "data" no es nulo o vacío
		if (!Strings.isNullOrEmpty(data)) {
		    // Utiliza Gson para convertir la cadena JSON en un mapa
		    Map<String, Object> resultMap = new Gson().fromJson(data, new TypeToken<Map<String, Object>>() {}.getType());
		    return resultMap;
		} else {
		    // Manejar el caso en que "data" es nulo o vacío, por ejemplo, devolviendo un mapa vacío
		    return new HashMap<>();
		}
		
			
	}
	
	
	public static Boolean isFileExist(String path) {
		try {
			
			File file = new File(path);
			
			return (file != null && file.exists()) ? Boolean.TRUE : Boolean.FALSE;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
}
