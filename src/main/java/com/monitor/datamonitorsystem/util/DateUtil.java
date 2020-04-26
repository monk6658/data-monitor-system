package com.monitor.datamonitorsystem.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @Date 2020/4/26 14:28
 * @author zxl
 */
public class DateUtil {
    private final String SAVE_TIME_TYPE = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取时间格式 yyyy-MM-dd HH:mm:ss 的时间
     * @author zxl
     * @time 2020/3/17 13:23
     */
    public String getSaveTime() {
        return new SimpleDateFormat(SAVE_TIME_TYPE).format(new Date());
    }


    /**
     * ******************************************************** .<br>
     * [方法] convertMoney <br>
     * [描述] 将12位的字符串转换为BigDecimal型，带有2位小数 <br>
     * [参数] 金额 <br>
     * [返回] BigDecimal <br>
     * [时间] 2015-12-7 下午4:29:34 <br>
     * ******************************************************** .<br>
     */
    public BigDecimal convertMoney(String money) throws Exception {
        String moneyStr1 = money.substring(0, money.length() - 2);
        String moneyStr2 = money.substring(money.length() - 2);
        String moneyStr = DateUtil.appendField(moneyStr1, ".", moneyStr2);
        return new BigDecimal(moneyStr).setScale(2, BigDecimal.ROUND_DOWN);
    }


    /**
     * ******************************************************** .<br>
     * [方法] appendField <br>
     * [描述] 拼接字符串 <br>
     * [参数] 将所有参数拼接 <br>
     * [返回] String <br>
     * [时间] 2016-3-10 上午10:43:53 <br>
     * ******************************************************** .<br>
     */
    public static String appendField(Object... params) {
        StringBuffer sbf = new StringBuffer();
        for (Object str : params) {
            sbf.append(str);
        }
        return sbf.toString();
    }

}
