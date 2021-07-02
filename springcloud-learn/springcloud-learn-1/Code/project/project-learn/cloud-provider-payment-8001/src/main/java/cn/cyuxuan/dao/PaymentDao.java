package cn.cyuxuan.dao;

import cn.cyuxuan.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper // 尽量避免使用@Repository，插入时会有问题，具体问题待考察
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);

}
