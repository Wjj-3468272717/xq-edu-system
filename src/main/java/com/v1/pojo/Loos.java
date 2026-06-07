package com.v1.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2026-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Loos implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "loosId", type = IdType.AUTO)
    private Integer loosId;

    @TableField("loosName")
    private String loosName;

    @TableField("loosImages")
    private String loosImages;

    @TableField("loosAddress")
    private String loosAddress;

    private String loosjdate;

    @TableField("loosStatus")
    private Integer loosStatus;

    private String scavenger;

    @TableField("scavengerPhone")
    private String scavengerPhone;

    @TableField("receiveName")
    private String receiveName;

    @TableField("receivePhone")
    private String receivePhone;

    private String loosldate;

    private String admin;

    private Integer del;

}
