package cn.cyuxuan.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单信息实体类
 * @author cyuxuan
 * @date 2021-06-27
 */

@Data
@AllArgsConstructor // 全参构造
@NoArgsConstructor // 无参构造
public class Payment {
    /**
     * id
     */
    private Long id; // 对应数据库bigint

    /**
     * 订单序列号
     */
    private String serial;
}
