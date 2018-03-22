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
public class DataTablesPage {

    public int draw;

    public int startIndex;

    public int pageSize;

    public String orderColumn;

    public String orderDir;

    public int pageNm;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderDir() {
        return orderDir;
    }

    public void setOrderDir(String orderDir) {
        this.orderDir = orderDir;
    }

    public int getPageNm() {
        return pageNm;
    }

    public void setPageNm(int pageNm) {
        this.pageNm = pageNm;
    }

    public int countPageNm(){
        return this.getStartIndex()/this.getPageSize();
    }
}
