package com.goblin.code.api.impl;

import cn.hutool.core.bean.BeanUtil;
import com.goblin.code.api.HelloService;
import com.goblin.code.basic.dao.TestMapper;
import com.goblin.code.basic.dataobject.TestDO;
import com.goblin.code.bo.TestBO;
import com.goblin.code.dto.TestDTO;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: base-code
 * @description:
 * @author: Guojs
 * @create: 2019-10-04 01:03
 **/
@Service
public class HelloServiceImpl implements HelloService {

    @Resource
    private TestMapper testMapper;
    /**
     * 根据id或name获取数据
     *
     * @param testDTO testDTO
     * @return
     */
    @Override
    public List<TestBO> listTestData(TestDTO testDTO) {
        Example example = new Example(TestDO.class);
        example.createCriteria().andEqualTo("id",testDTO.getId()).orEqualTo("name",testDTO.getName());
        List<TestDO> testDOList = testMapper.selectByExample(example);
        List<TestBO> testBOList = Lists.newArrayList();
        testDOList.forEach(testDO -> testBOList.add(transBo(testDO)));
        return testBOList;
    }

    private TestBO transBo(TestDO testDO){
        if (testDO == null){
            return null;
        }
        TestBO testBO = new TestBO();
        BeanUtil.copyProperties(testDO,testBO);
        return testBO;
    }

}
