package eon.p2p.base.mapper;

import eon.p2p.base.domain.Logininfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogininfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Logininfo record);

    Logininfo selectByPrimaryKey(Long id);

    List<Logininfo> selectAll();

    int updateByPrimaryKey(Logininfo record);

    Long selectCountByUsername(String username);

    Logininfo login(@Param("username") String username, @Param("password") String password, @Param("userType") int userType);

    Long countByUserType(int usertypeSystem);

    List<Logininfo> listByKeyword(String keyword);
}