package com.example.demo.dto;

import lombok.Data;

/**
 * Created by: NanLeiLei
 * Company: MJ
 * Date: 2018/5/2
 * Time: 20:08
 * 原材料信息
 */
@Data
public class RawMaterialDTO {

    //原材料号
    private String rawMaterialNo;

    //原材料描述
    private String rawMaterialDesc;

    //大小/量纲
    private String rawMaterialSize;

    //原材料长度
    private String length;

    //原材料密度
    private String density;

    //单根重量
    private String weight;

    //磷化框数量
    private String frameQuantity;

    //配送框数量
    private String distributeFrameQuantity;

    //截面长
    private Float sectionLength;

    //截面宽
    private Float sectionWidth;
    //料框最大数量(一框能装的数量，应该是配送框)
    private Integer frameMaxQuantity;

    //托盘最大数量
    private Integer unitMaxQuantity;


}
