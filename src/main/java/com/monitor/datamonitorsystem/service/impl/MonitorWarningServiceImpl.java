package com.monitor.datamonitorsystem.service.impl;

import com.alibaba.fastjson.JSON;
import com.monitor.datamonitorsystem.dao.MonitorWarningMapper;
import com.monitor.datamonitorsystem.pojo.BaseResponse;
import com.monitor.datamonitorsystem.pojo.MonitorWarningPo;
import com.monitor.datamonitorsystem.pojo.ResultDataVo;
import com.monitor.datamonitorsystem.service.MonitorWarningService;
import com.monitor.datamonitorsystem.util.DateUtil;
import com.monitor.datamonitorsystem.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 预警信息业务处理类
 * @Date 2020/4/24 9:46
 * @author zxl
 */
@Service
public class MonitorWarningServiceImpl implements MonitorWarningService {

    @Autowired
    private MonitorWarningMapper monitorWarningMapper;

    private DateUtil dateUtil = new DateUtil();

    private static BigDecimal everyMoney = new BigDecimal(0);

    @Override
    public BaseResponse saveWarnInfo(MonitorWarningPo monitorWarningPo) {
        monitorWarningPo.setCreateTime(dateUtil.getSaveTime());
        monitorWarningPo.setType(1);
        WebSocketServer.sendInfo(JSON.toJSONString(monitorWarningPo));
        return monitorWarningMapper.insertSelective(monitorWarningPo) > 0 ? new BaseResponse() : BaseResponse.error("入库失败");
    }


    @Override
    public BaseResponse changeEveryDayMoney(String amount) {
        try {
            ResultDataVo resultDataVo = new ResultDataVo();
            resultDataVo.setType("2");
            everyMoney.add(dateUtil.convertMoney(amount));
            WebSocketServer.sendInfo(JSON.toJSONString(resultDataVo));
            return new BaseResponse();
        }catch (Exception e){
            return BaseResponse.error("叠加失败");
        }
    }


}
