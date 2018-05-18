package com.yhl.platform.controller.api;

import com.yhl.platform.service.SysMenuService;
import com.yhl.platform.state.ClientApiFinal;
import com.yhl.platform.util.json.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = ClientApiFinal.version+"sys/")
@ResponseBody
public class MenuController {
    @Resource
    private SysMenuService sysMenuService;

    @RequestMapping(value="findMenuList",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public @ResponseBody
    JsonUtil findCarList(@RequestBody JsonUtil jsonUtil) throws Exception {
        Map map = (Map) jsonUtil.getData();
        JsonUtil params = sysMenuService.getMenu(map,jsonUtil);
        return params;
    }
}
