package com.biggirlo.gw.util;

import com.biggirlo.gw.model.ColumnTable;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * 缓存
 */
public class OfficialWebsiteTemp {

    Logger logg= Logger.getLogger(OfficialWebsiteTemp.class);

    private static OfficialWebsiteTemp officialWebsiteTemp = new OfficialWebsiteTemp();

    private OfficialWebsiteTemp(){

    }

    public static synchronized OfficialWebsiteTemp getInstance(){
        return  officialWebsiteTemp ;
    }

    private Map<String,ColumnTable> columnMap;

    /**
     * 栏目列表
     */
    private List<ColumnTable> columnList;

    public Map<String, ColumnTable> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(Map<String, ColumnTable> columnMap) {
        this.columnMap = columnMap;
    }

    public List<ColumnTable> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<ColumnTable> columnList) {
        this.columnList = columnList;
    }
}
