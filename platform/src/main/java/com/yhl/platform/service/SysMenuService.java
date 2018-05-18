package com.yhl.platform.service;

import com.yhl.platform.util.json.JsonUtil;
import java.util.Map;

/**
 * Created by Administrator on 2017-12-20.
 */
public interface SysMenuService {
    /**
     * 获取菜单
     * @param map
     * @return
     */
    JsonUtil getMenu(Map map, JsonUtil jsonUtil);
}
