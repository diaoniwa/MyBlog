package hstc.edu.cn.controller.admin;

import hstc.edu.cn.po.Blog;
import hstc.edu.cn.po.BlogType;
import hstc.edu.cn.po.Blogger;
import hstc.edu.cn.po.Link;
import hstc.edu.cn.service.BlogService;
import hstc.edu.cn.service.BlogTypeService;
import hstc.edu.cn.service.BloggerService;
import hstc.edu.cn.service.LinkService;
import hstc.edu.cn.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description 管理员系统controller层
 */
@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {

	@Autowired
	private BloggerService bloggerService;
	@Autowired
	private LinkService linkService;
	@Autowired
	private BlogTypeService blogTypeService;
	@Autowired
	private BlogService blogService;

	// 刷新系统缓存
	@RequestMapping("/refreshSystemCache")
	public String refreshSystemCache(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ServletContext application = RequestContextUtils.getWebApplicationContext(request).getServletContext();
		
		// 获取博主信息
		Blogger blogger = bloggerService.getBloggerData();
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);

		// 获取友情链接信息
		List<Link> linkList = linkService.getLinkData();
		application.setAttribute("linkList", linkList);

		// 获取博客类别信息
		List<BlogType> blogTypeList = blogTypeService.getBlogTypeData();
		application.setAttribute("blogTypeList", blogTypeList);

		// 获取博客信息，按照时间分类的
		List<Blog> blogTimeList = blogService.getBlogData();
		application.setAttribute("blogTimeList", blogTimeList);
		
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
}
