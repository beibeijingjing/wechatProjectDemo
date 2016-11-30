package core.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import core.common.PageInfo;

/**
 * 
 * create by Liujishuai on 2015年9月22日
 */
public interface IBaseMapper<T> {

	int deleteByPrimaryKey(String id);

	int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);

	List<T> getAllByPage(RowBounds rowBounds);

	List<T> selectByMap(Map<String, Object> condition);

	PageInfo<T> selectPageByMap(Map<String, Object> condition);

}
