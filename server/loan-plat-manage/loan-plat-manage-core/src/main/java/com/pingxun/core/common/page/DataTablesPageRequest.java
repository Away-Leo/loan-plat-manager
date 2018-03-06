package com.pingxun.core.common.page;

import com.pingxun.core.common.dtos.DataTablesPage;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author Away
 * @version V1.0
 * @Title: DataTablesPageRequest.java
 * @Description:
 * @date 2018/2/27 15:54
 * @copyright 重庆平讯数据
 */
public class DataTablesPageRequest extends AbstractPageRequest {


    private final Sort sort;

    public DataTablesPageRequest(DataTablesPage dataTablesPage){
        this(dataTablesPage.countPageNm(),dataTablesPage.getPageSize());
    }

    public DataTablesPageRequest(int page, int size) {
        this(page, size, null);
    }

    public DataTablesPageRequest(int page, int size, Sort.Direction direction, String... properties) {
        this(page, size, new Sort(direction, properties));
    }

    public DataTablesPageRequest(int page, int size, Sort sort) {
        super(page, size);
        this.sort = sort;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#getSort()
     */
    public Sort getSort() {
        return sort;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#next()
     */
    public Pageable next() {
        return new PageRequest(getPageNumber() + 1, getPageSize(), getSort());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.AbstractPageRequest#previous()
     */
    public DataTablesPageRequest previous() {
        return getPageNumber() == 0 ? this : new DataTablesPageRequest(getPageNumber() - 1, getPageSize(), getSort());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Pageable#first()
     */
    public Pageable first() {
        return new PageRequest(0, getPageSize(), getSort());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PageRequest)) {
            return false;
        }

        DataTablesPageRequest that = (DataTablesPageRequest) obj;

        boolean sortEqual = this.sort == null ? that.sort == null : this.sort.equals(that.sort);

        return super.equals(that) && sortEqual;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return 31 * super.hashCode() + (null == sort ? 0 : sort.hashCode());
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return String.format("Page request [number: %d, size %d, sort: %s]", getPageNumber(), getPageSize(),
                sort == null ? null : sort.toString());
    }
}
