package me.thinkchao.tckt.client.user;

import me.thinkchao.tckt.model.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Author:chao
 * Date:2023-11-10
 * Description: 远程调用
 */


@FeignClient(name = "service-user")
public interface UserInfoFeignClient {

    @GetMapping("/admin/user/userInfo/inner/getById/{id}")
    public UserInfo getById(@PathVariable("id") Long id);

}
