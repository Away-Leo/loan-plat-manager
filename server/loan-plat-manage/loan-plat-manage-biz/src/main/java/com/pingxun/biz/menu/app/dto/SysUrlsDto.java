package com.pingxun.biz.menu.app.dto;

import com.pingxun.biz.common.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * 认证费用价格表
 * Created by dujy on 2017/7/31.
 */
@Getter
@Setter
public class SysUrlsDto extends BaseDto{

    private Long id;//id

    /**链接地址**/
    private String url;

    /**连接描述**/
    private String urlName;

    public SysUrlsDto(String url, String urlName) {
        this.url = url;
        this.urlName = urlName;
    }
    public SysUrlsDto() {
    }
}
