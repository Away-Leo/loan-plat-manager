package com.pingxun.biz.user.app.dto;

import com.pingxun.biz.common.dto.BaseDto;
import com.pingxun.biz.common.dto.PageDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户信息拓展
 * Created by dujy on 2017/7/31.
 */
@Getter
@Setter
public class CwUserInfoExpandDto extends BaseDto{

    private Long id;

    /**用户ID**/
    private Long userId;

    /**渠道号**/
    private String chanelNo;

    /**分享链接**/
    private String shareUrl;
}
