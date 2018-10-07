package com.biggirlo.gw.service;

import com.biggirlo.base.util.Code;
import com.biggirlo.base.util.Restult;
import com.biggirlo.gw.ChangeHost;
import com.biggirlo.gw.mapper.ContentTableMapper;
import com.biggirlo.gw.model.ClassifyTable;
import com.biggirlo.gw.model.ColumnTable;
import com.biggirlo.gw.model.ContentTable;
import com.biggirlo.gw.util.GwCode;
import com.biggirlo.gw.util.OfficialWebsiteTemp;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OfficialWebsitePageService {

    @Autowired
    private ColumnTableService columnTableService;

    @Resource(name = "classifyTableService")
    private ClassifyTableService classifyTableService;

    @Autowired
    private ContentTableService contentTableService;

    @Autowired
    private ContentTableMapper contentTableMapper;

    //底部栏目
    private final static String GW_CONTACT = "GW_CONTACT";
    /**
     *  得到初始化信息
     * @return
     */
    public Object getInitInfo() {
        Map<String,Object> map = new HashMap<>();
        if( OfficialWebsiteTemp.getInstance().getColumnList() == null ||  OfficialWebsiteTemp.getInstance().getColumnList().size() == 0){
            this.initTemp();
        }
        map.put("columnTableList",OfficialWebsiteTemp.getInstance().getColumnList());
        return map;
    }

    /**
     * 根据栏目信息得到
     * @param search
     * @return
     */
    public Restult getColumnInfo(ColumnTable search) {
        if(search.getCode() == null)
            return new Restult(GwCode.VOID_COLUMN_CODE.value(), GwCode.VOID_COLUMN_CODE.msg());
        if( OfficialWebsiteTemp.getInstance().getColumnMap() == null ||  OfficialWebsiteTemp.getInstance().getColumnMap().isEmpty())
            this.initTemp();

        ColumnTable columnTable = OfficialWebsiteTemp.getInstance().getColumnMap().get(search.getCode());
        if(columnTable == null)
            return new Restult(GwCode.UNEXIT_COLUMN_CODE.value(), GwCode.UNEXIT_COLUMN_CODE.msg());
        /*
        //栏目
        ColumnTable columnTable = columnTableService.selectOne(search);
        if(columnTable == null)
            return new Restult(GwCode.UNEXIT_COLUMN_CODE.value(), GwCode.UNEXIT_COLUMN_CODE.msg());

        //分类
        ClassifyTable classifyTableRearch = new ClassifyTable();
        classifyTableRearch.setColumnId(columnTable.getId());
        List<ClassifyTable> classifyTableList = classifyTableService.select(classifyTableRearch);
        //所有内容
        ContentTable contentTableSearch = new ContentTable();
        contentTableSearch.setIsShow(1);
        List<ContentTable> contentTables = contentTableMapper.select(contentTableSearch);

        for(ClassifyTable classifyTable : classifyTableList) {
            //一级菜单的父级id为0
            if (classifyTable.getParentId() == 0) {
                if (classifyTable.getColumnId() == columnTable.getId())
                    columnTable.getClassifyTables().add(classifyTable);
                addChileClassifyTable(classifyTable, classifyTableList);
            }
            //设置内容
            for(ContentTable contentTable : contentTables){
                if(contentTable.getClassifyId() == classifyTable.getId())
                    classifyTable.getContents().add(contentTable);
            }
        }*/

        return new Restult(Code.SUCCESS,columnTable);
    }



    /**
     * 获取分类
     * @param search
     * @return
     */
    public Restult getClassifyInfo(ClassifyTable search) {
        if(search.getCode() == null)
            return new Restult(GwCode.VOID_CLASSIFY_CODE.value(), GwCode.VOID_CLASSIFY_CODE.msg());
        ClassifyTable classifyTable = classifyTableService.selectOne(search);
        if(classifyTable == null)
            return new Restult(GwCode.UNEXIT_CLASSIFY_CODE.value(), GwCode.UNEXIT_CLASSIFY_CODE.msg());

        //所有内容
        ContentTable contentTableSearch = new ContentTable();
        contentTableSearch.setIsShow(1);
        contentTableSearch.setClassifyId(classifyTable.getId());
        List<ContentTable> contentTables = contentTableMapper.selectLess(contentTableSearch);

        classifyTable.setContents(contentTables);

        return new Restult(Code.SUCCESS,classifyTable);
    }

    public Restult getontentCInfo(ContentTable search) {
        if(search.getCode() == null)
            return new Restult(GwCode.VOID_CONTENT_CODE.value(), GwCode.VOID_CONTENT_CODE.msg());
        ContentTable contentTable = contentTableService.selectOne(search);
        if(contentTable == null)
            return new Restult(GwCode.UNEXIT_CONTENT_CODE.value(), GwCode.UNEXIT_CONTENT_CODE.msg());

        return new Restult(Code.SUCCESS,contentTable);
    }



    public void initTemp(){
        Map<String ,ColumnTable> columnMap = new HashMap<>();

        //所有栏目
        List<ColumnTable> columnTableList = columnTableService.selectAll();
        //所有分类
        List<ClassifyTable> classifyTableList = classifyTableService.selectAll();
        //所有内容
        ContentTable contentTableSearch = new ContentTable();
        contentTableSearch.setIsShow(1);
        List<ContentTable> contentTables = contentTableMapper.selectLess(contentTableSearch);

        for(ClassifyTable classifyTable : classifyTableList) {
            classifyTable.setPresent(classifyTable.getPresent().replaceAll("\\r\\n","<br>"));
            //一级菜单的父级id为0
            if (classifyTable.getParentId() == 0) {
                for (ColumnTable columnTable : columnTableList){
                    if (classifyTable.getColumnId().longValue() == columnTable.getId()){
                        columnTable.getClassifyTables().add(classifyTable);
                    }
                }

                addChileClassifyTable(classifyTable, classifyTableList);
            }
            //设置内容
            for(ContentTable contentTable :contentTables){
                contentTable.setLessPresent(contentTable.getLessPresent().replaceAll("\\r\\n","<br>"));
                if(contentTable.getClassifyId().longValue() == classifyTable.getId()){
                    classifyTable.getContents().add(contentTable);
                }
            }
        }
        for(ColumnTable columnTable : columnTableList)
            columnMap.put(columnTable.getCode(),columnTable);

        OfficialWebsiteTemp.getInstance().setColumnList(columnTableList);
        OfficialWebsiteTemp.getInstance().setColumnMap(columnMap);
    }

    /**
     * 获取所有栏目
     *
     * @return
     */
    public Object getColums() {
        List<ColumnTable> columnTables = columnTableService.selectAll();
        for(ColumnTable columnTable : columnTables){
            if(columnTable.getCode().equals(this.GW_CONTACT)){
                ClassifyTable classifySearch = new ClassifyTable();
                classifySearch.setColumnId(columnTable.getId());
                List<ClassifyTable> classifyTables = classifyTableService.select(classifySearch);
                columnTable.setClassifyTables(classifyTables);
                for(ClassifyTable classifyTable : classifyTables){
                    ContentTable contentSearch = new ContentTable();
                    contentSearch.setClassifyId(classifyTable.getId());
                    classifyTable.setContents(contentTableService.select(contentSearch));
                }
            }
        }
        return columnTables;
    }


    /**
     * 递归获取树结构
     * @param parentClassifyTable
     * @param allClassifyTable
     * @return
     */
    public ClassifyTable addChileClassifyTable(ClassifyTable parentClassifyTable,List<ClassifyTable> allClassifyTable){
        for(ClassifyTable classifyTable: allClassifyTable){
            if(classifyTable.getParentId() == parentClassifyTable.getId()) {
                classifyTable.getClassifese().add(addChileClassifyTable(classifyTable,allClassifyTable));
            }
        }
        return parentClassifyTable;
    }


    public void changeHost(ChangeHost changeHost) {
        List<ContentTable> list = contentTableService.selectAll();
        for(ContentTable contentTable : list){
            if(contentTable.getLogo()!= null){
                contentTable.setLogo(contentTable.getLogo().replaceAll(changeHost.getOldHost(),changeHost.getNewHost()));
            }
            if(contentTable.getCoversImg() != null){
                contentTable.setCoversImg(contentTable.getCoversImg().replaceAll(changeHost.getOldHost(),changeHost.getNewHost()));
            }
            contentTableService.update(contentTable);
        }
        List<ClassifyTable> classifyTables = classifyTableService.selectAll();
        for(ClassifyTable classifyTable : classifyTables){
            if(classifyTable.getLogo()!= null){
                classifyTable.setLogo(classifyTable.getLogo().replaceAll(changeHost.getOldHost(),changeHost.getNewHost()));
            }
            if(classifyTable.getCoversImg() != null){
                classifyTable.setCoversImg(classifyTable.getCoversImg().replaceAll(changeHost.getOldHost(),changeHost.getNewHost()));
            }
            classifyTableService.update(classifyTable);
        }

    }
}
