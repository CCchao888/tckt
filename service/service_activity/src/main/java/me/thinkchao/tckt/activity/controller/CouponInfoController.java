package me.thinkchao.tckt.activity.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import me.thinkchao.tckt.activity.service.CouponInfoService;
import me.thinkchao.tckt.model.activity.CouponInfo;
import me.thinkchao.tckt.model.activity.CouponUse;
import me.thinkchao.tckt.result.Result;
import me.thinkchao.tckt.vo.activity.CouponUseQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-10
 */
@RestController
@RequestMapping("admin/activity/couponInfo")
public class CouponInfoController {

    @Autowired
    private CouponInfoService couponInfoService;



    @ApiOperation(value = "获取优惠券")
    @GetMapping("get/{id}")
    public Result get(@PathVariable String id) {
        CouponInfo couponInfo = couponInfoService.getById(id);
        return Result.success(couponInfo);
    }

    @ApiOperation(value = "新增优惠券")
    @PostMapping("save")
    public Result save(@RequestBody CouponInfo couponInfo) {
        couponInfoService.save(couponInfo);
        return Result.success(null);
    }

    @ApiOperation(value = "修改优惠券")
    @PutMapping("update")
    public Result updateById(@RequestBody CouponInfo couponInfo) {
        couponInfoService.updateById(couponInfo);
        return Result.success(null);
    }

    @ApiOperation(value = "删除优惠券")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        couponInfoService.removeById(id);
        return Result.success(null);
    }

    @ApiOperation(value="根据id列表删除优惠券")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> idList){
        couponInfoService.removeByIds(idList);
        return Result.success(null);
    }

    //分页查询优惠券列表
    @ApiOperation(value="分页查询优惠券")
    @GetMapping("{page}/{limit}")
    public Result listCoupon(@PathVariable("page") Long page,
                             @PathVariable("limit") Long limit) {
        Page<CouponInfo> pageParam = new Page<>(page, limit);
        IPage<CouponInfo> pageModel = couponInfoService.page(pageParam);
        return Result.success(pageModel);

    }

    //获取已经使用的优惠券列表（条件查询分页）
    @ApiOperation(value="获取已经使用的优惠券列表")
    @GetMapping("couponUse/{page}/{limit}")
    public Result getCouponUseList(@PathVariable("page") Long page,
                                   @PathVariable("limit") Long limit,
                                   CouponUseQueryVo couponUseQueryVo) {
        Page<CouponUse> pageParam = new Page<>(page, limit);
        IPage<CouponUse> pageModel = couponInfoService.selectCouponUsePage(pageParam, couponUseQueryVo);
        return Result.success(pageModel);
    }

}

