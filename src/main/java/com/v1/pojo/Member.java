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
 * @since 2026-06-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "memberId", type = IdType.AUTO)
    private Integer memberId;

    @TableField("memberName")
    private String memberName;

    @TableField("memberPhone")
    private String memberPhone;

    @TableField("memberSex")
    private Integer memberSex;

    @TableField("memberAge")
    private Integer memberAge;

    @TableField("memberTypes")
    private Integer memberTypes;

    @TableField("nenberDate")
    private String nenberDate;

    private String birthday;

    @TableField("memberStatic")
    private Integer memberStatic;

    private Float memberbalance;

    private String memberxufei;

    private Integer del;

//    @TableField("memberStatic")
    private Membertype membertype;

    public Member(Integer memberId, Integer del){
        this.memberId = memberId;
        this.del = del;
    }

}
