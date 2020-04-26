package com.monitor.datamonitorsystem.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 系统预警信息
 * @author zxl
 * @time 2020/4/24 9:40
 */
@Getter
@Setter
public class MonitorWarningPo {

    private Integer id;

    @NotBlank(message = "预警app不能为空")
    private String warnApp;

    @NotBlank(message = "预警内容不能为空")
    private String warnContent;

    /** 1: web  2:触屏版 3：android 4：ios */
    @Pattern(regexp = "^[1-4]$",message = "预警级别不对")
    @NotBlank(message = "预警级别不能为空")
    private String warnLevel;

    private String createTime;

    private String localdate;

    private String localtime;

    private int type;
}