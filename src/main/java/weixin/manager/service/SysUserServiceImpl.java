package weixin.manager.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import weixin.manager.bean.SysUser;
import weixin.manager.bean.SysUserRoleRef;
import weixin.manager.mapper.SysUserMapper;
import weixin.manager.mapper.SysUserRoleRefMapper;
import core.mapper.IBaseMapper;
import core.service.BaseService;

@Repository
public class SysUserServiceImpl extends BaseService<SysUser> implements
		SysUserService {
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleRefMapper sysUserRoleRefMapper;

	@Override
	public IBaseMapper<SysUser> getBaseMapper() {
		return sysUserMapper;
	}

	@Override
	public void batchBindingUserRole(String userId, String roleIds) {
		// 删除该用户原有角色关联
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("user_id", userId);
		sysUserRoleRefMapper.deleteUserRoleRefByMap(condition);
		// 重新绑定角色
		SysUserRoleRef userRoleRef = new SysUserRoleRef();
		if (StringUtils.isNotEmpty(roleIds)) {
			String roleIdArr[] = roleIds.split(",");
			if (roleIdArr != null) {
				for (String roleId : roleIdArr) {
					userRoleRef = new SysUserRoleRef();
					userRoleRef.setUser_id(userId);
					userRoleRef.setRole_id(roleId);
					sysUserRoleRefMapper.insert(userRoleRef);
				}
			}
		}

	}

}
