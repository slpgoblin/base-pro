package com.goblin.code.basic.dataobject;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "test")
public class TestDO {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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