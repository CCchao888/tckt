package me.thinkchao.tckt.activity.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.thinkchao.tckt.activity.mapper.CouponInfoMapper;
import me.thinkchao.tckt.activity.service.CouponInfoService;
import me.thinkchao.tckt.model.activity.CouponInfo;
import me.thinkchao.tckt.model.activity.CouponUse;
import me.thinkchao.tckt.vo.activity.CouponUseQueryVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券信息 服务实现类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-10
 */
@Service
public class CouponInfoServiceImpl extends ServiceImpl<CouponInfoMapper, CouponInfo> implements CouponInfoService {

    //分页查询已经使用的优惠劵
    @Override
    public IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo) {
        return null;
    }
}
