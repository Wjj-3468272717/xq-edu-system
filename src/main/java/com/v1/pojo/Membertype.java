package com.v1.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author v1
 * @since 2026-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Membertype implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "typeId", type = IdType.AUTO)
    private Integer typeId;

    @TableField("typeName")
    private String typeName;

    @TableField("typeciShu")
    private Integer typeciShu;

    @TableField("typeDay")
    private Integer typeDay;

    private Float typemoney;

    @TableField("typeDel")
    private Integer typeDel;


}
