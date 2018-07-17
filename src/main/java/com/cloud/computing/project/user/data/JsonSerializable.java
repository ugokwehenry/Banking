package com.cloud.computing.project.user.data;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public interface JsonSerializable {
	public JSONObject toJson() throws JSONException;
}
