package com.exam.manage.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author zx
 * @since 2023-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("g_info")
@AllArgsConstructor
@NoArgsConstructor
@ColumnWidth(45)//注释在具体属性上,设置单独列。注释在类上,统一设置列宽
@HeadRowHeight(50)//设置表头行高
public class Info implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ExcelProperty(value = "序号", index = 0)
    private Integer id;

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名", index = 1)
    private String name;

    /**
     * 性别
     */
    @ExcelProperty(value = "性别", index = 2)
    private String sex;

    /**
     * 校友卡号
     */
    @ExcelProperty(value = "校园卡号", index = 3)
    private String idSchool;

    /**
     * 身份证号
     */
    @ExcelProperty(value = "身份证号码", index = 4)
    private String idCard;

    /**
     * 民族
     */
    @ExcelProperty(value = "名族", index = 5)
    private String nation;

    /**
     * 政治面貌
     */
    @ExcelProperty(value = "政治面貌", index = 6)
    private String political;

    /**
     * 籍贯
     */
    @ExcelProperty(value = "籍贯", index = 7)
    private String nativer;

    /**
     * 培养层次
     */
    @ExcelProperty(value = "培养层次", index = 8)
    private String degree;

    /**
     * 入学时间
     */
    @ExcelProperty(value = "入学时间", index = 9)
    private LocalDate sTime;

    /**
     * 在校专业
     */
    @ExcelProperty(value = "专业", index = 10)
    private String sMajor;

    /**
     * 在校班级
     */
    @ExcelProperty(value = "在校班级", index = 11)
    private String sClass;

    /**
     * 在校导师（班主任）
     */
    @ExcelProperty(value = "导师", index = 12)
    private String sTutor;

    /**
     * 毕业时间
     */
    @ExcelProperty(value = "毕业时间", index = 13)
    private LocalDate gTime;

    /**
     * 毕业去向
     */
    @ExcelProperty(value = "毕业去向城市", index = 14)
    private String gDestination;

    /**
     * 现工作单位（学校）
     */
    @ExcelProperty(value = "现工作单位", index = 15)
    private String cCompany;

    /**
     * 现工作职务
     */
    @ExcelProperty(value = "工作职务", index = 16)
    private String cJob;

    /**
     * 现所在城市
     */
    @ExcelProperty(value = "现所在城市", index = 17)
    private String cCity;

    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话", index = 18)
    private String telephone;

    /**
     * 电子邮箱
     */
    @ExcelProperty(value = "电子邮箱", index = 19)
    private String email;

    /**
     * 现通讯地址和邮编
     */
    @ExcelProperty(value = "通讯地址", index = 20)
    private String postalAddress;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注", index = 21)
    private String remark;

//    private Integer delete;
}
