package com.v1.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
 * @since 2026-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Coach implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "coachId", type = IdType.AUTO)
    private Integer coachId;

    @TableField("coachName")
    private String coachName;

    @TableField("coachPhone")
    private String coachPhone;

    @TableField("coachSex")
    private Integer coachSex;

    @TableField("coachAge")
    private Integer coachAge;

    @TableField("coachDate")
    private String coachDate;

    private Integer teach;

    @TableField("coachWages")
    private Double coachWages;

    @TableField("coachAddress")
    private String coachAddress;

    @TableField("coachStatic")
    private Integer coachStatic;

    private Integer del;

    public Coach(Integer coachId, Integer del){
        this.coachId = coachId;
        this.del = del;
    }

}
