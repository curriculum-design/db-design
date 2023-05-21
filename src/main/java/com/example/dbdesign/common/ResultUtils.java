package com.example.dbdesign.common;

import java.io.Serializable;

/**
 * @author zzs
 * @date 2023/5/21 15:08
 */
public class ResultUtils {
    private ResultUtils() {
    }

    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(0, data, "ok");
    }

    public static <T extends Serializable> BaseResponse<T> success(T data, String description){
        return new BaseResponse<>(0, data, "ok", description);
    }

    public static <T extends Serializable> BaseResponse<T> error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }

    public static <T extends Serializable> BaseResponse<T> error(int code, String message, String description){
        return new BaseResponse<>(code, null, message, description);
    }

    public static <T extends Serializable> BaseResponse<T> error(ErrorCode errorCode, String message, String description){
        return new BaseResponse<>(errorCode.getCode(), null, message, description);
    }

    public static <T extends Serializable> BaseResponse<T> error(ErrorCode errorCode, String description){
        return new BaseResponse<>(errorCode.getCode(), null, description);
    }
}
