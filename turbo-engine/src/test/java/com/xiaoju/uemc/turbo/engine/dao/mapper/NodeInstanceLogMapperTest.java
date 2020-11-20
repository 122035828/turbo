package com.xiaoju.uemc.turbo.engine.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoju.uemc.turbo.engine.entity.NodeInstanceLogPO;
import com.xiaoju.uemc.turbo.engine.runner.BaseTest;
import com.xiaoju.uemc.turbo.engine.util.EntityBuilder;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefanie on 2019/12/1.
 */
public class NodeInstanceLogMapperTest extends BaseTest {

    @Resource
    private NodeInstanceLogMapper nodeInstanceLogMapper;

    @Test
    public void insert() {
        NodeInstanceLogPO nodeInstanceLogPO = EntityBuilder.buildNodeInstanceLogPO();
        int result = nodeInstanceLogMapper.insert(nodeInstanceLogPO);
        Assert.assertTrue(result == 1);
    }

    @Test
    public void batchInsert() {
        NodeInstanceLogPO nodeInstanceLogPO = EntityBuilder.buildNodeInstanceLogPO();
        List<NodeInstanceLogPO> nodeInstanceLogPOList = new ArrayList<>();
        nodeInstanceLogPOList.add(nodeInstanceLogPO);
        nodeInstanceLogPOList.add(EntityBuilder.buildNodeInstanceLogPO());
        nodeInstanceLogPOList.add(EntityBuilder.buildNodeInstanceLogPO());
        nodeInstanceLogMapper.batchInsert(nodeInstanceLogPO.getFlowInstanceId(), nodeInstanceLogPOList);

        QueryWrapper<NodeInstanceLogPO> entityWrapper = new QueryWrapper<>();
        entityWrapper.in("flow_instance_id", nodeInstanceLogPO.getFlowInstanceId());
        nodeInstanceLogPOList = nodeInstanceLogMapper.selectList(entityWrapper);
        Assert.assertTrue(nodeInstanceLogPOList.size() == 3);
    }
}
