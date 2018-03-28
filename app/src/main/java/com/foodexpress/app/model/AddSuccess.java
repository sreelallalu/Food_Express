package com.foodexpress.app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sreelal on 27/3/18.
 */


public class AddSuccess {

@SerializedName("success")
@Expose
private Integer success;
@SerializedName("data")
@Expose
private String data;
@SerializedName("message")
@Expose
private String message;

public Integer getSuccess() {
return success;
}

public void setSuccess(Integer success) {
this.success = success;
}

public String getData() {
return data;
}

public void setData(String data) {
this.data = data;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}
