package core.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * create by Liujishuai on 2015年9月22日
 */
public interface IBaseService<T> {
	int deleteByPrimaryKey(String id);

	int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);

	List<T> selectByMap(Map<String, Object> condition);

}
