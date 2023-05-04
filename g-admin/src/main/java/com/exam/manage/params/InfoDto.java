package com.exam.manage.params;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author: ZhangX
 * @createDate: 2023/5/1
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ColumnWidth(45)//注释在具体属性上,设置单独列。注释在类上,统一设置列宽
@HeadRowHeight(50)//设置表头行高
@ExcelIgnoreUnannotated
public class InfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "序号")
    private Integer id;

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名")
    private String name;

    /**
     * 性别
     */
    @ExcelProperty(value = "性别")
    private String sex;

    /**
     * 校友卡号
     */
    @ExcelProperty(value = "学号")
    private String idSchool;

    /**
     * 身份证号
     */
    @ExcelProperty(value = "身份证号")
    private String idCard;

    /**
     * 民族
     */
    @ExcelProperty(value = "民族")
    private String nation;

    /**
     * 政治面貌
     */
    @ExcelProperty(value = "政治面貌")
    private String political;

    /**
     * 籍贯
     */
    @ExcelProperty(value = "籍贯")
    private String nativer;

    /**
     * 培养层次
     */
    @ExcelProperty(value = "培养层次")
    private String degree;

    /**
     * 入学时间
     */
    @ExcelProperty(value = "入学时间")
    private String stime;

    /**
     * 在校专业
     */
    @ExcelProperty(value = "在校专业")
    private String smajor;

    /**
     * 在校班级
     */
    @ExcelProperty(value = "在校班级")
    private String sclass;

    /**
     * 在校导师（班主任）
     */
    @ExcelProperty(value = "导师")
    private String stutor;

    /**
     * 毕业时间
     */
    @ExcelProperty(value = "毕业时间")
    private String gtime;

    /**
     * 毕业去向
     */
    @ExcelProperty(value = "毕业去向")
    private String gdestination;

    /**
     * 现工作单位（学校）
     */
    @ExcelProperty(value = "现工作单位")
    private String ccompany;

    /**
     * 现工作职务
     */
    @ExcelProperty(value = "现工作职务")
    private String cjob;

    /**
     * 现所在城市
     */
    @ExcelProperty(value = "现所在城市")
    private String ccity;

    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话")
    private String telephone;

    /**
     * 电子邮箱
     */
    @ExcelProperty(value = "电子邮箱")
    private String email;

    /**
     * 现通讯地址和邮编
     */
    @ExcelProperty(value = "通讯地址")
    private String postalAddress;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

//    private Integer delete;
}
