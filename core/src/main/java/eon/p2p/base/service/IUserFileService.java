package eon.p2p.base.service;

import eon.p2p.base.domain.UserFile;
import eon.p2p.base.query.UserFileQueryObject;
import eon.p2p.base.query.page.PageResult;

import java.util.List;

public interface IUserFileService {

	/**
	 * 查询没有设置类型的文件
	 * @param id
	 * @return
	 */
	List<UserFile> listUnSetTypeFiles(Long id, boolean unselected);

	/**
	 * 用上传的文件创建一个UserFile对象
	 * @param path
	 */
	void applyFile(String path);

	/**
	 * 用户选择风控材料类型
	 * @param id
	 * @param fileType
	 */
	void applyTypes(Long[] id, Long[] fileType);

	/**
	 * 分页查询
	 * @param qo
	 * @return
	 */
	PageResult<UserFile> query(UserFileQueryObject qo);
	
	List<UserFile> queryList(UserFileQueryObject qo);

	/**
	 * 审核风控材料
	 * @param id
	 * @param remark
	 * @param score
	 * @param state
	 */
	void audit(Long id, String remark, int score, int state);

}
