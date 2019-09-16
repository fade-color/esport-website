package xyz.qinian.esport.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.qinian.esport.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(@Param("userId") Integer userId, @Param("tel") String tel);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(@Param("userId") Integer userId, @Param("tel") String tel);

    User selectByTelAndPassword(@Param("tel") String tel, @Param("password") String password);

    int updateByPrimaryKeySelective(User record);

    int updateByTelSelective(User record);

    int updateByPrimaryKey(User record);
}