package com.example.demo.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 获取Excel文件中的所有内容 (核心方法)
 * @throws IOException
 */
public class ExcelUtils {


    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    /**
     * 获取Excel文件中的所有内容 (核心方法)
     * @throws IOException
     */
    private static List<List<Object>> getExcelData(String path) throws IOException {
        File file = new File(path);
        // 实例化一个输入流
        InputStream is = new FileInputStream(file);
        // 获取workbook
        Workbook wb;
        wb = getWorkbok(is, file);
        // 获取工作表的数量
        int numberOfSheets = wb.getNumberOfSheets();
        // 获取第一个工作表
        Sheet sheet = wb.getSheetAt(0);
        //实例化一个二维list集合
        List<List<Object>> listData = new ArrayList<List<Object>>();
        Row row = null;// 行对象
        Iterator<Cell> cols = null;// 列对象
        List<Object> list = null;// 用来存放数据
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {// 循环每一行的数据
            row = sheet.getRow(i);
            if (row != null) {
                cols = row.cellIterator();
                list = new ArrayList<Object>();
                while (cols.hasNext()) {
                    list.add(cols.next());//添加每一行的数据到list中
                }
                listData.add(list);//将每一行的list放到另一个list中
            }
        }
        return listData;
    }

    /**
     * @author : andy
     * @date : 2019年12月23日
     * @time : 下午5:12:09
     * @param in
     * @param file
     * @return
     * @throws IOException 判断Excel 文件的版本
     */
    private static Workbook getWorkbok(InputStream in, File file) throws IOException {
        Workbook wb = null;
        if (file.getName().endsWith(EXCEL_XLS)) {
            wb = new HSSFWorkbook(in);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    /**
     *
     * @param num  想要获取那一列的数据,
     * @param path 文件路径,
     * @return
     * @throws IOException
     */
    public static Set<Object> getExcelDataByColumn(int num, String path) throws IOException {
        // 实例化一个集合,用来存放数据,为了保持数据和存入的顺序一致使用LinkedHashSet;
        Set<Object> sets = new LinkedHashSet<Object>();
        // 调用封装好获取数据的方法
        List<List<Object>> list = ExcelUtils.getExcelData(path);
        // 调用封装好获取数据的方法
        for (List<Object> list2 : list) {
            // 想办法获取你需要的哪一行或者那一列数据
            // 然后获取每一行中的第几个数据
            Object object = list2.get(num);
            // 将数据存放到set中
            sets.add(object);
        }
        return sets;
    }

}

