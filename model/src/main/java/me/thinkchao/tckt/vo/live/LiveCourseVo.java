package me.thinkchao.tckt.vo.live;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.thinkchao.tckt.model.live.LiveCourse;
import me.thinkchao.tckt.model.vod.Teacher;

@Data
public class LiveCourseVo extends LiveCourse {

	@ApiModelProperty(value = "主播老师")
	private Teacher teacher;

	private Integer liveStatus;

	@ApiModelProperty(value = "直播开始时间")
	private String startTimeString;

	@ApiModelProperty(value = "直播结束时间")
	private String endTimeString;

}

