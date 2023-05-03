package com.tictok.grace.result;

public class TICTOKJSONResult {

    private Integer status;

    private String msg;

    private Object data;
    
    private String ok;

    public static TICTOKJSONResult build(Integer status, String msg, Object data) {
        return new TICTOKJSONResult(status, msg, data);
    }

    public static TICTOKJSONResult build(Integer status, String msg, Object data, String ok) {
        return new TICTOKJSONResult(status, msg, data, ok);
    }
    
    public static TICTOKJSONResult ok(Object data) {
        return new TICTOKJSONResult(data);
    }

    public static TICTOKJSONResult ok() {
        return new TICTOKJSONResult(null);
    }
    
    public static TICTOKJSONResult errorMsg(String msg) {
        return new TICTOKJSONResult(500, msg, null);
    }

    public static TICTOKJSONResult errorUserTicket(String msg) {
        return new TICTOKJSONResult(557, msg, null);
    }
    
    public static TICTOKJSONResult errorMap(Object data) {
        return new TICTOKJSONResult(501, "error", data);
    }
    
    public static TICTOKJSONResult errorTokenMsg(String msg) {
        return new TICTOKJSONResult(502, msg, null);
    }
    
    public static TICTOKJSONResult errorException(String msg) {
        return new TICTOKJSONResult(555, msg, null);
    }
    
    public static TICTOKJSONResult errorUserQQ(String msg) {
        return new TICTOKJSONResult(556, msg, null);
    }

    public TICTOKJSONResult() {

    }

    public TICTOKJSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    
    public TICTOKJSONResult(Integer status, String msg, Object data, String ok) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.ok = ok;
    }

    public TICTOKJSONResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

}
