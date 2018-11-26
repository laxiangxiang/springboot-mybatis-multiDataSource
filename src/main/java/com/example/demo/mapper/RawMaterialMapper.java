package com.example.demo.mapper;

import com.example.demo.dto.RawMaterialDTO;
import com.example.demo.entity.RawMaterial;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RawMaterialMapper {

    int insert(RawMaterial record);

    RawMaterial selectByPrimaryKey(Long id);

    int updateByPrimaryKey(RawMaterial record);

    int batchInsert(@Param("rawMaterialList") List<RawMaterial> rawMaterialList);

    int countByRawMaterialNo(@Param("rawMaterialNo") String rawMaterialNo);

    List<RawMaterial> findByRawMaterialNo(@Param("rawMaterialNo") String rawMaterial);

    int batchDelete(@Param("rawMaterialList") List<RawMaterial> rawMaterialList);

    /**
     * 原材料基础数据查询
     * @param rawMaterialNo 原材料号
     * @param rawMaterialDesc 原材料描述
     * @return
     */
    List<RawMaterialDTO> findAllRawMaterialData(@Param("rawMaterialNo") String rawMaterialNo,
                                                @Param("rawMaterialDesc") String rawMaterialDesc,
                                                @Param("page") int page,
                                                @Param("size") int size);

    int findTotalCount(@Param("rawMaterialNo") String rawMaterialNo,
                       @Param("rawMaterialDesc") String rawMaterialDesc);
}