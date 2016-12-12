package weixin.manager.mapper;

import java.util.List;

import weixin.manager.bean.WxMenu;
import core.mapper.IBaseMapper;

public interface WxMenuNewMapper extends IBaseMapper<WxMenu> {

	public void batchDeleteMenuById(List<String> list);

	public void batchDeleteMenuByParentId(List<String> list);

}