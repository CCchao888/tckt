package me.thinkchao.tckt.vo.live;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.thinkchao.tckt.model.live.LiveCourseConfig;
import me.thinkchao.tckt.model.live.LiveCourseGoods;

import java.util.List;

@Data
@ApiModel(description = "LiveCourseConfig")
public class LiveCourseConfigVo extends LiveCourseConfig {

	@ApiModelProperty(value = "商品列表")
	private List<LiveCourseGoods> liveCourseGoodsList;
}