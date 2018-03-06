package com.pingxun.biz.home.domain.dao;

import com.pingxun.biz.home.app.dto.HomeDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 首页数据表
 */
@Repository
public class HomeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询每日新增情况
     * @param homeDto
     * @return
     */
    public HomeDto dayDevelopmentData(HomeDto homeDto) {

        String sql = " select sum(borrowUserNum) as borrowUserNum," +
                "             sum(loanUserNum) as loanUserNum," +
                "             sum(noRoleNum) as noRoleNum, " +
                "             sum(borrowDemandNum) as borrowDemandNum, " +
                "             sum(loanDemandNum) as loanDemandNum, " +
                "             sum(borrowCallNum) as borrowCallNum, " +
                "             sum(loanCallNum) as loanCallNum, " +
                "             sum(borrowSaleNum) as borrowSaleNum, " +
                "             sum(loanSaleNum) as loanSaleNum, " +
                "             sum(borrowPayFee) as borrowPayFee, " +
                "             sum(loanPayFee) as loanPayFee " +
                "        from ( " +
                "          select count(1) as borrowUserNum,0 as loanUserNum,0 as noRoleNum,0 as borrowDemandNum,0 as loanDemandNum," +
                "                 0 as borrowCallNum," +
                "                 0 as loanCallNum," +
                "                 0 as borrowSaleNum," +
                "                 0 as loanSaleNum,0 as borrowPayFee,0 as loanPayFee  " +
                "            from pf_se_user a inner JOIN loan_user_info b on a.id = b.user_id " +
                "   where 1=1 and b.user_type=1 ";
        if (StringUtils.isNotEmpty(homeDto.getApplyDate())) {
            sql += " and date_format(a.register_date,'%Y-%m-%d') = '" + homeDto.getApplyDate() + "' ";
        } else {
            sql += " and date_format(a.register_date,'%y-%m-%d') = date_format(now(),'%y-%m-%d') ";
        }
        sql += "   union all " +
                "   select 0 borrowUserNum,count(1) as loanUserNum,0 as noRoleNum,0 as borrowDemandNum,0 as loanDemandNum,0 as borrowCallNum,0 as loanCallNum," +
                "          0 as borrowSaleNum,0 as loanSaleNum,0 as borrowPayFee,0 as loanPayFee  " +
                "     from pf_se_user a inner JOIN loan_user_info b on a.id = b.user_id " +
                "    where b.user_type=2  ";
        if (StringUtils.isNotEmpty(homeDto.getApplyDate())) {
            sql += " and date_format(a.register_date,'%Y-%m-%d') = '" + homeDto.getApplyDate() + "' ";
        } else {
            sql += " and date_format(a.register_date,'%y-%m-%d') = date_format(now(),'%y-%m-%d') ";
        }
        sql += "   union all " +
                "   select 0 borrowUserNum,0 as loanUserNum,count(1) as noRoleNum,0 as borrowDemandNum,0 as loanDemandNum,0 as borrowCallNum,0 as loanCallNum," +
                "          0 as borrowSaleNum,0 as loanSaleNum,0 as borrowPayFee,0 as loanPayFee  " +
                "     from pf_se_user a inner JOIN loan_user_info b on a.id = b.user_id " +
                "    where b.user_type=0  ";
        if (StringUtils.isNotEmpty(homeDto.getApplyDate())) {
            sql += " and date_format(a.register_date,'%Y-%m-%d') = '" + homeDto.getApplyDate() + "' ";
        } else {
            sql += " and date_format(a.register_date,'%y-%m-%d') = date_format(now(),'%y-%m-%d') ";
        }
        //需求数据统计
        sql +=" union all " +
              " select 0 borrowUserNum,0 as loanUserNum,0 as noRoleNum,count(1) as borrowDemandNum,0 as loanDemandNum,0 as borrowCallNum,0 as loanCallNum," +
                "      0 as borrowSaleNum,0 as loanSaleNum,0 as borrowPayFee,0 as loanPayFee " +
              "   from loan_borrow_demand where 1=1 ";
        if (StringUtils.isNotEmpty(homeDto.getApplyDate())) {
            sql += " and date_format(publish_date,'%Y-%m-%d') = '" + homeDto.getApplyDate() + "' ";
        } else {
            sql += " and date_format(publish_date,'%y-%m-%d') = date_format(now(),'%y-%m-%d') ";
        }
          sql+= " union all " +
                " select 0 borrowUserNum,0 as loanUserNum,0 as noRoleNum,0 as borrowDemandNum,count(1) as loanDemandNum,0 as borrowCallNum,0 as loanCallNum," +
                "      0 as borrowSaleNum,0 as loanSaleNum,0 as borrowPayFee,0 as loanPayFee " +
                "   from loan_loan_demand where 1=1 ";
        if (StringUtils.isNotEmpty(homeDto.getApplyDate())) {
            sql += " and date_format(publish_date,'%Y-%m-%d') = '" + homeDto.getApplyDate() + "' ";
        } else {
            sql += " and date_format(publish_date,'%y-%m-%d') = date_format(now(),'%y-%m-%d') ";
        }
        //拨打次数统计
        sql  +=" union all " +
                " select 0 borrowUserNum,0 as loanUserNum,0 as noRoleNum,0 as borrowDemandNum,0 as loanDemandNum,count(1) as borrowCallNum,0 as loanCallNum," +
                "        0 as borrowSaleNum,0 as loanSaleNum,0 as borrowPayFee,0 as loanPayFee\n" +
                "  from loan_call_record a INNER JOIN loan_user_info b on a.user_id = b.user_id\n" +
                " where 1=1 and b.user_type=1\n" ;
        if (StringUtils.isNotEmpty(homeDto.getApplyDate())) {
            sql += " and date_format(a.called_time,'%Y-%m-%d') = '" + homeDto.getApplyDate() + "' ";
        } else {
            sql += " and date_format(a.called_time,'%y-%m-%d') = date_format(now(),'%y-%m-%d') ";
        }
           sql += "  union all "+
                "select 0 borrowUserNum,0 as loanUserNum,0 as noRoleNum,0 as borrowDemandNum,0 as loanDemandNum,0 as borrowCallNum,count(1) as loanCallNum," +
                "       0 as borrowSaleNum,0 as loanSaleNum,0 as borrowPayFee,0 as loanPayFee \n" +
                "  from loan_call_record a INNER JOIN loan_user_info b on a.user_id = b.user_id\n" +
                " where 1=1 and b.user_type=2\n";
        if (StringUtils.isNotEmpty(homeDto.getApplyDate())) {
            sql += " and date_format(a.called_time,'%Y-%m-%d') = '" + homeDto.getApplyDate() + "' ";
        } else {
            sql += " and date_format(a.called_time,'%y-%m-%d') = date_format(now(),'%y-%m-%d') ";
        }
        //成交量
        sql +=" union all " +
              " select 0 borrowUserNum,0 as loanUserNum,0 as noRoleNum,0 as borrowDemandNum,0 as loanDemandNum,0 as borrowCallNum,0 as loanCallNum," +
                "      count(1) as borrowSaleNum,0 as loanSaleNum,0 as borrowPayFee,0 as loanPayFee from loan_mail_list a\n" +
              "  where apply_type=1 and a.is_agree=1 ";
        if (StringUtils.isNotEmpty(homeDto.getApplyDate())) {
            sql += " and date_format(a.apply_date,'%Y-%m-%d') = '" + homeDto.getApplyDate() + "' ";
        } else {
            sql += " and date_format(a.apply_date,'%y-%m-%d') = date_format(now(),'%y-%m-%d') ";
        }

        sql +=" union all " +
              " select 0 borrowUserNum,0 as loanUserNum,0 as noRoleNum,0 as borrowDemandNum,0 as loanDemandNum,0 as borrowCallNum,0 as loanCallNum," +
                "      0 as borrowSaleNum,count(1) as loanSaleNum,0 as borrowPayFee,0 as loanPayFee from loan_mail_list a\n" +
              "  where apply_type=2 and a.is_agree=1 ";
        if (StringUtils.isNotEmpty(homeDto.getApplyDate())) {
            sql += " and date_format(a.apply_date,'%Y-%m-%d') = '" + homeDto.getApplyDate() + "' ";
        } else {
            sql += " and date_format(a.apply_date,'%y-%m-%d') = date_format(now(),'%y-%m-%d') ";
        }
        //收入统计
        sql += " union all" +
                " select 0 borrowUserNum,0 as loanUserNum,0 as noRoleNum,0 as borrowDemandNum,0 as loanDemandNum,0 as borrowCallNum,0 as loanCallNum," +
                "        0 as borrowSaleNum,0 as loanSaleNum,sum(a.pay_fee) as borrowPayFee,0 as loanPayFee \n" +
                "   from loan_order_pay a INNER join loan_user_info b on a.user_id = b.user_id\n" +
                "  where b.user_type=1 and a.is_pay=1\n" ;
        if (StringUtils.isNotEmpty(homeDto.getApplyDate())) {
            sql += " and date_format(a.pay_date,'%Y-%m-%d') = '" + homeDto.getApplyDate() + "' ";
        } else {
            sql += " and date_format(a.pay_date,'%y-%m-%d') = date_format(now(),'%y-%m-%d') ";
        }
                sql += "  union all\n" +
                " select 0 borrowUserNum,0 as loanUserNum,0 as noRoleNum,0 as borrowDemandNum,0 as loanDemandNum,0 as borrowCallNum,0 as loanCallNum," +
                "        0 as borrowSaleNum,0 as loanSaleNum,0 as borrowPayFee,sum(a.pay_fee) as loanPayFee \n" +
                "  from loan_order_pay a INNER join loan_user_info b on a.user_id = b.user_id\n" +
                " where b.user_type=2 and a.is_pay=1 ";
        if (StringUtils.isNotEmpty(homeDto.getApplyDate())) {
            sql += " and date_format(a.pay_date,'%Y-%m-%d') = '" + homeDto.getApplyDate() + "' ";
        } else {
            sql += " and date_format(a.pay_date,'%y-%m-%d') = date_format(now(),'%y-%m-%d') ";
        }

        sql += " ) aa ";

        List<HomeDto> homeDtoList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(HomeDto.class));
        return homeDtoList.get(0);
    }

}
