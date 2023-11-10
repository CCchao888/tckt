package me.thinkchao.tckt.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.thinkchao.tckt.model.user.UserInfo;
import me.thinkchao.tckt.user.mapper.UserInfoMapper;
import me.thinkchao.tckt.user.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-10
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
