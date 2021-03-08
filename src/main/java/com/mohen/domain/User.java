package com.mohen.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author MH19
 */
@Data
public class User {

    /**
     * 主键id
     * 生成报表时忽略，不生成次字段
     */
    @ExcelIgnore
    private Integer id;

    /**
     * 定义表头名称和位置,0代表第一列
     */
    @ExcelProperty(value = "姓名", index = 0)
    private String name;

    @ExcelProperty(value = "年龄", index = 1)
    private Integer age;

    /**
     * 定义列宽
     */
    @ColumnWidth(20)
    @DateTimeFormat(value = "yyyy/MM/dd")
    @ExcelProperty(value = "出生日期", index = 2)
    private Date birthday;

    @ExcelProperty(value = "分数", index = 3)
    private BigDecimal score;
}
