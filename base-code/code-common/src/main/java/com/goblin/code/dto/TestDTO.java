package com.goblin.code.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: base-code
 * @description: 测试dto
 * @author: Guojs
 * @create: 2019-10-05 10:26
 **/
@Setter
@Getter
@ToString
@Builder
public class TestDTO implements Serializable {

    private static final long serialVersionUID = 4796825355432970888L;

    private String id;

    private String name;

    private Byte sex;

    private Integer age;
}
