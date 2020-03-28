package com.goblin.code.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: base-code
 * @description:
 * @author: Guojs
 * @create: 2020-03-28 10:18
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestBO {

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
