package com.goblin.code.api;

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
     * 根据id获取数据
     * @param id id
     * @return
     */
    List<TestDTO> listTestData(String id);

}
