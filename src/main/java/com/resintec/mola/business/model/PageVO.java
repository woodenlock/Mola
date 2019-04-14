package com.resintec.mola.business.model;

import java.io.Serializable;
import java.util.List;

/**
 * page result for response
 * @author woodenlock
 *
 * @param <T>
 */
public class PageVO<T> implements Serializable{
    private static final long serialVersionUID = 4916282748296022943L;

    /**
     * the count for each page
     */
    private Integer pageSize;

    /**
     * current page number
     */
    private Integer pageNum;

    /**
     * total count
     */
    private Integer total;

    /**
     * total page number
     */
    private Integer pages;

    /**
     * orders
     */
    private String orders;

    /**
     * query params
     */
    private T search;

    /**
     * result list
     */
    List<T> list;

    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the pageNum
     */
    public Integer getPageNum() {
        return pageNum;
    }

    /**
     * @param pageNum
     */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    /**
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return the pages
     */
    public Integer getPages() {
        return pages;
    }

    /**
     * @param pages
     */
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     * @return the orders
     */
    public String getOrders() {
        return orders;
    }

    /**
     * @param orders
     */
    public void setOrders(String orders) {
        this.orders = orders;
    }

    /**
     * @return the search
     */
    public T getSearch() {
        return search;
    }

    /**
     * @param search
     */
    public void setSearch(T search) {
        this.search = search;
    }

    /**
     * @return the list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * @param list
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PageVO [pageSize=");
        builder.append(pageSize);
        builder.append(", pageNum=");
        builder.append(pageNum);
        builder.append(", total=");
        builder.append(total);
        builder.append(", pages=");
        builder.append(pages);
        builder.append(", orders=");
        builder.append(orders);
        builder.append(", search=");
        builder.append(search);
        builder.append(", list=");
        builder.append(list);
        builder.append("]");
        return builder.toString();
    }
}