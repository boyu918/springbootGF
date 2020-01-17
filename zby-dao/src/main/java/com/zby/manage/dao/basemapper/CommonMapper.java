package com.zby.manage.dao.basemapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author zby
 * @time 2019/5/23 14:33
 */
public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //这个接口不能被扫描到
}
