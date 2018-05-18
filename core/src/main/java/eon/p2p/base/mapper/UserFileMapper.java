package eon.p2p.base.mapper;

import eon.p2p.base.domain.UserFile;
import eon.p2p.base.query.UserFileQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserFileMapper {

	int insert(UserFile record);

	UserFile selectByPrimaryKey(Long id);

	int updateByPrimaryKey(UserFile record);

	List<UserFile> listUnSetTypeFiles(@Param("applierId") Long id,
                                      @Param("unselected") boolean unselected);

	Long queryForCount(UserFileQueryObject qo);

	List<UserFile> query(UserFileQueryObject qo);
}