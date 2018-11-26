package com.example.demo.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 原材料信息
 */
@Data
public class RawMaterial implements Serializable{

    private Long id;

    //原材料号
    @NotBlank(message = "原材料号不能为空")
    private String rawMaterialNo;

    //原材料描述
    @NotBlank(message = "原材料描述不能为空")
    @Pattern(message = "原材料描述需包含\";数字*数字\"(矩形管;30*10)",regexp = "[\\s\\S]*;\\d+\\*\\d+[\\s\\S]*")
    private String rawMaterialDesc;

    //大小/量纲
    @NotBlank(message = "大小/量纲不能为空")
//    @Pattern(message = "",regexp = "[0-9]+([.]{1}[0-9]+){0,1}KG/M；*[\\s\\S]*")
    private String rawMaterialSize;

    /*
    todo:原財料長度必須大於0，不能等於0 2018-07-09
     */
    //原材料长度
    @NotBlank(message = "原材料长度不能为空")
//    @Pattern(message = "原材料长度需为:整数或小数",regexp = "[0-9]+([.]{1}[0-9]+){0,1}\\s*")
    private String length;

    //原材料密度
    @NotBlank(message = "原材料密度不能为空")
    @Pattern(message = "原材料密度需为:整数或小数",regexp = "[0-9]+([.]{1}[0-9]+){0,1}\\s*")
    private String density;

    //单根重量
    @NotBlank(message = "单根重量不能为空")
    @Pattern(message = "单根重量需为:整数或小数",regexp = "[0-9]+([.]{1}[0-9]+){0,1}\\s*")
    private String weight;

    //磷化框数量
    private Integer frameQuantity;

    //配送框数量
    private Integer distributeFrameQuantity;

    //截面长
    private Float sectionLength;

    //截面宽
    private Float sectionWidth;

    //料框平排根数
    private Integer frameRowNumber;

    //料框最大层数
    private Integer frameMaxLayer;

    //料框最大数量(一框能装的数量，应该是配送框)
    private Integer frameMaxQuantity;

    //托盘平排根数
    private Integer unitRowNumber;

    //托盘最大层数
    private Integer unitMaxLayer;

    //托盘最大数量
    private Integer unitMaxQuantity;

    //获取时间
    private String getTime;

    public RawMaterial() {
    }
}
