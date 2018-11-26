package com.example.demo.mapper;

import com.example.demo.entity.CutPart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CutPartMapper {

    int insert(CutPart record);

    CutPart selectByPrimaryKey(Long id);

    CutPart selectById(@Param("id") Long id);

    int updateByPrimaryKey(CutPart record);

    int batchInsert(@Param("cutPartList") List<CutPart> cutParts);

}