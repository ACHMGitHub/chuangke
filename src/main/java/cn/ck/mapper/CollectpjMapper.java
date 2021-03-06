package cn.ck.mapper;

import cn.ck.entity.Collectpj;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2018-09-26
 */
public interface CollectpjMapper extends BaseMapper<Collectpj> {

    //按时间倒序获取用户收藏列表
    List<Collectpj> selectColpj(String id);
}
