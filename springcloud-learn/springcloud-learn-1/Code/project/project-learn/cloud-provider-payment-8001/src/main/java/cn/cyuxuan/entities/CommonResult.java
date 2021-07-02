package cn.cyuxuan.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @auther zzyy
 * @create 2020-02-18 17:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>
{
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String  message;

    /**
     * 返回结果集
     */
    private T       data;

    /**
     * 构造返回信息
     * @param code
     * @param message
     */
    public CommonResult(Integer code,String message)
    {
        this(code,message,null);
    }
}
