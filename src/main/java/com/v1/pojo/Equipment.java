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
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "eqId", type = IdType.AUTO)
    private Integer eqId;

    @TableField("eqName")
    private String eqName;

    @TableField("eqText")
    private String eqText;

    private Integer del;

    public Equipment(Integer eqId, Integer del){
        this.eqId = eqId;
        this.del = del;
    }

}
