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
 * @since 2026-06-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "subId", type = IdType.AUTO)
    private Integer subId;

    private String subname;

    @TableField("sellingPrice")
    private Double sellingPrice;

    private Integer del;

    public Subject(Integer subId, Integer del){
        this.subId = subId;
        this.del = del;
    }

}
