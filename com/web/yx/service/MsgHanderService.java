package com.web.yx.service;

import com.web.yx.model.RequestBean;
import com.web.yx.model.ResponseBean;

public interface MsgHanderService {
	/**处理微信所有消息
	 * @param responseBean */
	void resolveWechtMessage(RequestBean content, ResponseBean responseBean);

}
