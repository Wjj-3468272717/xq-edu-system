package com.v1.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2026-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Adminuser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "adminId", type = IdType.AUTO)
    private Integer adminId;

    @TableField("adminName")
    private String adminName;

    @TableField("adminPassword")
    private String adminPassword;

    private String phone;

    private String birthday;

    private String idcard;

    @TableField("createTime")
    private String createTime;

    private Integer gender;

    private Integer del;

    private Adminuser(Integer adminId, Integer del){
        this.del = del;
        this.adminId = adminId;
    }

}
