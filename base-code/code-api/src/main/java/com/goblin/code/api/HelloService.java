package com.goblin.code.api;

import com.goblin.code.bo.TestBO;
import com.goblin.code.dto.TestDTO;

import java.util.List;

/**
 * @program: base-code
 * @description:
 * @author: Guojs
 * @create: 2019-10-04 01:02
 **/
public interface HelloService {

    /**
     * 根据dto获取数据
     * @param testDTO testDTO
     * @return
     */
    List<TestBO> listTestData(TestDTO testDTO);

}
