package me.thinkchao.tckt.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.thinkchao.tckt.model.order.OrderDetail;
import me.thinkchao.tckt.order.mapper.OrderDetailMapper;
import me.thinkchao.tckt.order.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单明细 订单明细 服务实现类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-10
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

}
