package weixin.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import weixin.manager.bean.SysResources;
import weixin.manager.mapper.SysResourcesMapper;
import core.mapper.IBaseMapper;
import core.service.BaseService;

@Repository
public class SysResourcesServiceImpl extends BaseService<SysResources>
		implements SysResourcesService {
	@Autowired
	private SysResourcesMapper sysResourcesMapper;

	@Override
	public IBaseMapper<SysResources> getBaseMapper() {
		return sysResourcesMapper;
	}

}
