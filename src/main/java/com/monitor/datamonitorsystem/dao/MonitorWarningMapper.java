package com.monitor.datamonitorsystem.dao;

import com.monitor.datamonitorsystem.pojo.MonitorWarningPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统应用监控预警
 * @author zxl
 * @time 2020/4/24 9:42
 */
@Mapper
public interface MonitorWarningMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(MonitorWarningPo record);

    int insertSelective(MonitorWarningPo record);

    MonitorWarningPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MonitorWarningPo record);

    int updateByPrimaryKey(MonitorWarningPo record);

}