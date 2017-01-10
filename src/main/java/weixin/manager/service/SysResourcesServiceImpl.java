package weixin.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import weixin.manager.bean.SysResources;
import weixin.manager.bean.SysRoleResourcesRef;
import weixin.manager.mapper.SysResourcesMapper;
import weixin.manager.mapper.SysRoleResourcesRefMapper;
import weixin.manager.vo.SysRoleResourcesVo;
import core.mapper.IBaseMapper;
import core.service.BaseService;

@Repository
public class SysResourcesServiceImpl extends BaseService<SysResources>
		implements SysResourcesService {
	@Autowired
	private SysResourcesMapper sysResourcesMapper;
	@Autowired
	private SysRoleResourcesRefMapper sysRoleResourcesRefMapper;

	@Override
	public IBaseMapper<SysResources> getBaseMapper() {
		return sysResourcesMapper;
	}

	@Override
	@Transactional
	public void addRoleResourcesRef(String roleId, String resourceIds) {
		// 删除该角色资源
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("role_id", roleId);
		sysRoleResourcesRefMapper.deleteRoleResourcesRefByMap(condition);
		// 绑定新资源
		if (StringUtils.isNotEmpty(resourceIds)
				&& StringUtils.isNotEmpty(roleId)) {
			String resourcesIdArr[] = resourceIds.split(",");
			if (resourcesIdArr != null) {
				SysRoleResourcesRef roleResRef = null;
				for (String resourceId : resourcesIdArr) {
					roleResRef = new SysRoleResourcesRef();
					roleResRef.setRole_id(roleId);
					roleResRef.setResource_id(resourceId);
					sysRoleResourcesRefMapper.insert(roleResRef);
				}

			}
		}

	}

	@Override
	public List<SysRoleResourcesVo> getRoleResourcesVoList(String roleId) {
		return sysResourcesMapper.getRoleResourcesVoList(roleId);
	}

}
