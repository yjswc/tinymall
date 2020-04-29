package com.cskaoyan.utils.Vo;

import com.cskaoyan.bean.BaseRespVo;

/**
 * @Author: Li Qing
 * @Create: 2020/4/27 12:02
 * @Version: 1.0
 */
public class VoHandler<T> {
    /**
     * 处理Vo返回正确数值业务
     *
     * @param object
     * @return
     */
    public  BaseRespVo ok(Object... object) {
        return new BaseRespVo(0, object, "成功");
    }

    public  BaseRespVo fail() {
        return new BaseRespVo(10000, null, "失败");
    }
}
