package com.goblin.code.api.impl;

import com.goblin.code.api.HelloService;
import com.goblin.code.dao.TestMapperEx;
import com.goblin.code.entity.TestDO;
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
    private TestMapperEx testMapperEx;

    /**
     * 根据id获取数据
     *
     * @param id id
     * @return
     */
    @Override
    public List<TestDTO> listTestData(String id) {
        Example example = new Example(TestDO.class);
        example.createCriteria().andEqualTo("id",id);
        List<TestDO> testDOList = testMapperEx.selectByExample(example);
        List<TestDTO> testDTOS = Lists.newArrayList();
        testDOList.forEach(testDO -> testDTOS.add(transDto(testDO)));
        return testDTOS;
    }

    private TestDTO transDto(TestDO testDO){
        if (testDO == null){
            return null;
        }
        return TestDTO.builder().id(testDO.getId()).age(testDO.getAge()).name(testDO.getName()).sex(testDO.getSex()).build();
    }
}
