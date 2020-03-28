package com.goblin.code.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @program: base-code
 * @description:
 * @author: Guojs
 * @create: 2020-03-28 10:16
 **/
@Data
@Builder
public class TestVO {

    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * 年龄
     */
    private Integer age;

}
