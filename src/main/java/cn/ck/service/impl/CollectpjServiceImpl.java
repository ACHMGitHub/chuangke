package cn.ck.service.impl;

import cn.ck.entity.Collectpj;
import cn.ck.mapper.CollectpjMapper;
import cn.ck.service.CollectpjService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2018-09-26
 */
@Service
public class CollectpjServiceImpl extends ServiceImpl<CollectpjMapper, Collectpj> implements CollectpjService {
    @Autowired
    CollectpjMapper collectpjMapper;


    public List<Collectpj> selectColpj(String id){
        List<Collectpj> collectpjList=collectpjMapper.selectColpj(id);
        return  collectpjList;
    }
}
