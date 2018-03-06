
package com.pingxun.biz.common.dto;

import com.pingxun.core.common.util.ObjectProperUtil;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class BaseDto implements Serializable {

    /**创建时间**/
    protected Date rawAddTime = null;

    /**修改时间**/
    protected Date rawUpdateTime;

    /**创建人**/
    protected String rawCreator = "1";

    /**修改人**/
    protected String rawModifier = "1";

    protected Short rawVersion;

    /**
     * @Author: Away
     * @Description: 转换为相应的域对象
     * @Param: tClass
     * @Return T
     * @Date 2018/1/11 11:35
     * @Copyright 重庆平讯数据
     */
    public <T> T toEntity(Class<T> tClass) throws Exception {
        T returnData = tClass.newInstance();
        returnData = (T) ObjectProperUtil.compareAndValue(this, returnData, true, null);
        return returnData;
    }

    public Date getRawAddTime() {
        return rawAddTime;
    }

    public void setRawAddTime(Date rawAddTime) {
        this.rawAddTime = rawAddTime;
    }

    public Date getRawUpdateTime() {
        return rawUpdateTime;
    }

    public void setRawUpdateTime(Date rawUpdateTime) {
        this.rawUpdateTime = rawUpdateTime;
    }

    public String getRawCreator() {
        return rawCreator;
    }

    public void setRawCreator(String rawCreator) {
        this.rawCreator = rawCreator;
    }

    public String getRawModifier() {
        return rawModifier;
    }

    public void setRawModifier(String rawModifier) {
        this.rawModifier = rawModifier;
    }

    public Short getRawVersion() {
        return rawVersion;
    }

    public void setRawVersion(Short rawVersion) {
        this.rawVersion = rawVersion;
    }
}