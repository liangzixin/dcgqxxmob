package com.xiangmu.lzx.Bean;

public class BaseResponse<T> {
    public int  resultCode;
    public String resultMessage;
    public T data;
    public BaseResponse(int resultCode, String resultMessage, T data) {
        super();
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}