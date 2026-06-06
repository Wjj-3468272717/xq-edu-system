package com.v1.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @author v1
 * @since 2026-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "goodsId", type = IdType.AUTO)
    private Integer goodsId;

    @TableField("goodsName")
    private String goodsName;

    private String unit;

    @TableField("unitPrice")
    private Double unitPrice;

    @TableField("sellPrice")
    private Double sellPrice;

    private Integer inventory;

    private String remark;

    private Integer del;

    public Goods(Integer goodsId, Integer del){
        this.del = del;
        this.goodsId = goodsId;
    }

}
