package core.service;

import java.util.List;
import java.util.Map;

import core.mapper.IBaseMapper;

/**
 * 
 * create by Liujishuai on 2015年9月22日
 */
public abstract class BaseService<T> implements IBaseService<T> {
	public abstract IBaseMapper<T> getBaseMapper();

	@Override
	public int deleteByPrimaryKey(String id) {
		return this.getBaseMapper().deleteByPrimaryKey(id);
	}

	@Override
	public int insert(T record) {

		return this.getBaseMapper().insert(record);
	}

	@Override
	public int insertSelective(T record) {
		// TODO Auto-generated method stub
		return this.getBaseMapper().insertSelective(record);
	}

	@Override
	public T selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return this.getBaseMapper().selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		// TODO Auto-generated method stub
		return this.getBaseMapper().updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		// TODO Auto-generated method stub
		return this.getBaseMapper().updateByPrimaryKey(record);
	}

	@Override
	public List<T> selectByMap(Map<String, Object> condition) {
		return this.getBaseMapper().selectByMap(condition);
	}

}
