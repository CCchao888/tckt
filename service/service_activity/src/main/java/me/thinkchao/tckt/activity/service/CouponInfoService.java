package me.thinkchao.tckt.activity.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.thinkchao.tckt.model.activity.CouponInfo;
import me.thinkchao.tckt.model.activity.CouponUse;
import me.thinkchao.tckt.vo.activity.CouponUseQueryVo;

/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-10
 */
public interface CouponInfoService extends IService<CouponInfo> {

    // 分页查询已经使用的优惠券
    IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo);

    /**
     * 更新优惠券使用状态
     * @param couponUseId
     * @param orderId
     */
    void updateCouponInfoUseStatus(Long couponUseId, Long orderId);
}
