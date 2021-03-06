package cn.ntshare.Blog.dao;

import cn.ntshare.Blog.pojo.ImgRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created By Seven.wk
 * Description: 图片上传记录接口
 * Created At 2019/01/07
 */
@Mapper
@Repository
public interface ImgRecordMapper {

    int insert(String img);

    int updateIdByImg(ImgRecord imgRecord);

    int checkImgChangeById(ImgRecord imgRecord);

    int deleteId(ImgRecord imgRecord);

    List<ImgRecord> queryDiscardImg();

    int deleteRecord(@Param("ids") List<Integer> ids);

}
