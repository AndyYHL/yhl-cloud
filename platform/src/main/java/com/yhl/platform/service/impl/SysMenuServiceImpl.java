package com.yhl.platform.service.impl;

import com.google.common.collect.Maps;
import com.yhl.platform.dao.read.SysMenuReadDao;
import com.yhl.platform.service.SysMenuService;
import com.yhl.platform.util.json.JsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-12-20.
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
    @Resource
    SysMenuReadDao sysMenuReadDao;

    @Override
    public JsonUtil getMenu(Map map, JsonUtil jsonUtil) {
        jsonUtil.setData(pMenu(map));
        return jsonUtil;
    }

    private List<Map> pMenu(Map map){
        List<Map> menuList = sysMenuReadDao.findEntity(map);
        for (Map lmap : menuList) {
            Map erMap = Maps.newHashMap();
            erMap.put("parentid",lmap.get("id"));
            lmap.put("submenu",this.pMenu(erMap));
        }
        return menuList;
    }
}
