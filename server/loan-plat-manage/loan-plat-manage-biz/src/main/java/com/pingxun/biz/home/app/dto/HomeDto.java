package com.pingxun.biz.home.app.dto;

import com.pingxun.biz.common.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class HomeDto extends BaseDto{

    private Long id;

    private String applyDate;

    private String startDate;

    private String endDate;

    private Integer devUser;

    private Integer borrowUserNum;

    private Integer loanUserNum;

    private Integer noRoleNum;//注册未选择角色用户数

    private Integer borrowDemandNum;//借款人发布需求数

    private Integer loanDemandNum;//出借人发布需求数

    private Integer borrowCallNum;//借款人拨打

    private Integer loanCallNum;//出借人拨打

    private Integer borrowSaleNum;//借款人成交量

    private Integer loanSaleNum;//出借人成交量

    private BigDecimal borrowPayFee=BigDecimal.ZERO;

    private BigDecimal loanPayFee=BigDecimal.ZERO;
}
