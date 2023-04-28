package com.exam.manage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class Info implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 学号
     */
    private String idSchool;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 民族
     */
    private String nation;

    /**
     * 政治面貌
     */
    private String political;

    /**
     * 籍贯
     */
    private String nativer;

    /**
     * 培养层次
     */
    private String degree;

    /**
     * 入学时间
     */
    private LocalDate sTime;

    /**
     * 在校专业
     */
    private String sMajor;

    /**
     * 在校班级
     */
    private String sClass;

    /**
     * 在校导师（班主任）
     */
    private String sTutor;

    /**
     * 毕业时间
     */
    private LocalDate gTime;

    /**
     * 毕业去向
     */
    private String gDestination;

    /**
     * 现工作单位（学校）
     */
    private String cCompany;

    /**
     * 现工作职务
     */
    private String cJob;

    /**
     * 现所在城市
     */
    private String cCity;

    /**
     * 联系电话
     */
    private String telephone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 现通讯地址和邮编
     */
    private String postalAddress;

    /**
     * 备注
     */
    private String remark;

//    private Integer delete;
}
