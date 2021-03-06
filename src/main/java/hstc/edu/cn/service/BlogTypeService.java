package hstc.edu.cn.service;

import hstc.edu.cn.po.BlogType;

import java.util.List;
import java.util.Map;

/**
 * Created by win8 on 2017/3/15.
 */
public interface BlogTypeService {
    public List<BlogType> getBlogTypeData();

    // 分页查询博客类别信息
    public List<BlogType> listBlogType(Map<String, Object> map);

    // 获取总记录数
    public Long getTotal(Map<String, Object> map);

    // 添加博客类别
    public Integer addBlogType(BlogType blogType);

    // 更新博客类别
    public Integer updateBlogType(BlogType blogType);

    // 删除博客类别
    public Integer deleteBlogType(Integer id);
}
