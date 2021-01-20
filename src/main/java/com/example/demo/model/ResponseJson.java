package com.example.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ResponseJson<T> {

    @ApiModelProperty("是否请求成功")
    private int status;
    private String message;
    private T data;

    public ResponseJson() {}

    public ResponseJson(int status, String message, T data) {
        this.status=status;
        this.message = message;
        this.data = data;
    }

    /**
     * 失败
     *
     * @return
     */
    public ResponseJson fail() {
        this.status=400;
        this.message = "fail";
        return this;
    }

    /**
     * 成功
     *
     * @return
     */
    public ResponseJson success() {
        this.status=200;
        this.message = "success";
        return this;
    }

    /**
     * 描述
     *
     * @param message
     * @return
     */
    public ResponseJson message(String message) {
        this.message = message;
        return this;
    }

    /**
     * 数据
     *
     * @param data
     * @return
     */
    public ResponseJson data(T data) {
        this.data = data;
        return this;
    }


}