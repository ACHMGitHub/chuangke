package cn.ck.service.impl;

import cn.ck.entity.Resource;
import cn.ck.entity.bean.ResCol;
import cn.ck.entity.bean.ResColNum;
import cn.ck.mapper.ResourceMapper;
import cn.ck.service.ResourceService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2018-09-21
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Override
    public List<Resource> getMostLikeResPage() {
        return baseMapper.getMostLike();
    }

    @Override
    public List<Resource> getLatestResPage() {
        return baseMapper.selectList(new EntityWrapper<Resource>().orderBy("res_uploadtime", false));
    }

    @Override
    public List<Resource> getSuggestPage(String keyword) {
        return  baseMapper.selectList(
                new EntityWrapper<Resource>()
                .like("res_name", keyword)
                .or().like("res_intro", keyword)
                .orderBy("res_uploadtime", false));
    }

    @Override
    public List<ResColNum> getResColNum() {
        return baseMapper.getResColNum();
    }

    @Override
    public List<Resource> getResByTag(String tag) {
        return  baseMapper.selectList(
                new EntityWrapper<Resource>()
                        .like("res_tag", tag)
                        .orderBy("res_uploadtime", false));
    }

    @Override
    public List<ResCol> selectDesc(String id) {
        return baseMapper.selectDesc(id);
    }


}
