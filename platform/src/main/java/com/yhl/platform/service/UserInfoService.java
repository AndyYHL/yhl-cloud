package com.yhl.platform.service;

import com.yhl.platform.util.json.JsonUtil;

import java.util.Map;

/**
 * Created by Administrator on 2017-12-20.
 */
public interface UserInfoService {
    /**
     * 获取用户
     * @param map
     * @return
     */
    JsonUtil findLimit(Map map, JsonUtil jsonUtil);
}
