package com.weibo.message.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.weibo.message.utils.Base.LookDto;

public class LookUtils {
	public static List<LookDto> getLookUtls() {
		String res = "";
		JsonArray object = null;
		List<LookDto> list = null;
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL("https://api.weibo.com/2/emotions.json?source=209678993");
			HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
			if (200 == urlCon.getResponseCode()) {
				InputStream is = urlCon.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, "utf-8");
				BufferedReader br = new BufferedReader(isr);

				String str = null;
				while ((str = br.readLine()) != null) {
					buffer.append(str);
				}
				br.close();
				isr.close();
				is.close();
				res = buffer.toString();
				JsonParser parse = new JsonParser();
				object = (JsonArray) parse.parse(res);
				list = JsonUtils.jsonToList(object.toString(), LookDto.class);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
