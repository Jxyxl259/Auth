package com.yaic.common;

/**
 * @ClassName LoggerEnum
 * @Description 业务日志枚举
 * @Author jiangxy
 * @Date 2018\7\30 0030 15:35
 * @Version 1.0.0
 */
public enum LogFileNameEnum {

    QUARTZ_TASK("quartzTask");

    private String logFileName;

    LogFileNameEnum(String fileName) {
        this.logFileName = fileName;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

}
