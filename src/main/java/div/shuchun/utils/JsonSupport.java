package div.shuchun.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import div.shuchun.pojo.ImportObj;

public class JsonSupport {

	private JSONArray jsonArray;

	public List<ImportObj>jsonArrayToPartObjList(String jsonArrayString) {
		
		jsonArray = JSONObject.parseArray(jsonArrayString);
		
		// Json Array transfer to List<ImportObj>
		List<ImportObj> ObjList = new ArrayList<>();
		JSONObject jObj;
		ImportObj iObj;
		for (int i=0; i<jsonArray.size(); i++) {
			jObj = (JSONObject) jsonArray.get(i);
			iObj = new ImportObj(jObj.getInteger("statusId"), 
					jObj.getString("partsCode"), 
					jObj.getInteger("quantity"), 
					jObj.getString("position")
			);
			ObjList.add(iObj);
		}
		
		return ObjList;
	}
	
}