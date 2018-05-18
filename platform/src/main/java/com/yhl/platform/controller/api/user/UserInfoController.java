package com.yhl.platform.controller.api.user;

import com.alibaba.fastjson.JSON;
import com.yhl.platform.service.UserInfoService;
import com.yhl.platform.state.ClientApiFinal;
import com.yhl.platform.util.json.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = ClientApiFinal.version+"user/")
@ResponseBody
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @RequestMapping(value="findLimit",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public @ResponseBody JsonUtil findLimit(@RequestBody JsonUtil jsonUtil) throws Exception {

        Map<String,Object> mapUtil = JSON.parseObject(JSON.toJSONString(jsonUtil),Map.class);
        Map map = (Map) jsonUtil.getData();
        return userInfoService.findLimit(mapUtil,jsonUtil);
    }
}
