package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by LXX on 2018/11/16.
 */
@Data
public class CutPart implements Serializable{

    private Long id;

    //板件UPI
    private String UPI;

    //批次号
    private String planNo;

    //订单号
    private String orderId;

    //包装号
    private String packageCode;

    //包装顺序
    private String sequence;

    //切割长度
    private double cLength;

    //切割宽度
    private double cWidth;

    //切割厚度
    private double thk;

    //材料编码
    private String matOrderId;

    //基准边
    private String baseLine;

    //工艺路线
    private String routing;
}
