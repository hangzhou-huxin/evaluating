package cn.itcast.application.study.evaluation.utils;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	public static String objectToJson(Object object) {

		JsonGenerator jsonGenerator = null;
		ObjectMapper objectMapper = new ObjectMapper();
		ByteArrayOutputStream out = null;
		String json = null;
		try {
			try {

				String jsonStr = "";
				out = new ByteArrayOutputStream();
				jsonGenerator = objectMapper.getFactory().createGenerator(out,
						JsonEncoding.UTF8);
				jsonGenerator.writeObject(object);
				json = new String( out.toByteArray(),Charset.forName("UTF-8")) ;out.toString();
				System.out.println(json);
			} finally {
				if (jsonGenerator != null) {
					jsonGenerator.flush();
				}
				if (!jsonGenerator.isClosed()) {
					jsonGenerator.close();
				}
				if (out != null) {
					out.close();
				}
				jsonGenerator = null;
				objectMapper = null;

			}
		} catch (Exception e) {

		}
		return json;
	}

}
