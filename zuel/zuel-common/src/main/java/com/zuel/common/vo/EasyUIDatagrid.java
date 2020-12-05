package com.zuel.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/*
 * @author:汪思超
 * @class:EasyUI的datagrid组件提供的返回结果类型
 * @date:2020.12.5
 * */

public class EasyUIDatagrid<E> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long total;
	
    private List<E> rows = new ArrayList<>(0);

    public EasyUIDatagrid(long total, List<E> rows) {
        this.total = total;
        this.rows = rows;
    }

    public EasyUIDatagrid() {
    }

    @Override
    public String toString() {
        return "EasyUIDatagrid{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EasyUIDatagrid<?> that = (EasyUIDatagrid<?>) o;
        return total == that.total &&
                Objects.equals(rows, that.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(total, rows);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }
}
