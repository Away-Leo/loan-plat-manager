package com.pingxun.core.common.dtos;

import lombok.Getter;
import lombok.Setter;

/**
* @Title: DataTablesPage.java
* @Description:  dataTables分页载体
* @author Away
* @date 2018/2/27 14:48
* @copyright 重庆平讯数据
* @version V1.0
*/
@Getter
@Setter
public class DataTablesPage {

    public int draw;

    public int startIndex;

    public int pageSize;

    public String orderColumn;

    public String orderDir;

    public int pageNm;

    public int countPageNm(){
        return this.getStartIndex()/this.getPageSize();
    }
}
